import java.util.*;
import java.util.concurrent.*;

/**
 * Problem 6: Distributed Rate Limiter for API Gateway
 * 
 * Scenario: API gateway handles requests from 100,000 clients. 
 * Each client is allowed 1000 requests per hour. 
 * Enforce this limit efficiently.
 * 
 * Features:
 * - Track request counts per client (by API key or IP)
 * - Allow burst traffic up to limit
 * - Reset counters every hour
 * - Respond within 1ms for rate limit checks
 * - Handle distributed deployment
 * - Provide clear error messages when limit exceeded
 * 
 * Concepts: Hash table for client tracking, time-based operations,
 * collision handling, performance under concurrent access
 */
public class DistributedRateLimiter {
    
    // Token Bucket implementation for rate limiting
    static class TokenBucket {
        String clientId;
        int tokens;                // Current available tokens
        long lastRefillTime;       // Last time tokens were refilled
        final int maxTokens;       // Maximum tokens (capacity)
        final int refillRate;      // Tokens added per second
        final long windowSize;     // Time window in milliseconds
        
        TokenBucket(String clientId, int maxTokens, long windowSize) {
            this.clientId = clientId;
            this.maxTokens = maxTokens;
            this.tokens = maxTokens;
            this.lastRefillTime = System.currentTimeMillis();
            this.windowSize = windowSize;
            this.refillRate = maxTokens; // Refill all tokens when window resets
        }
        
        /**
         * Try to consume a token
         * @return true if request allowed, false if rate limited
         */
        synchronized boolean tryConsume() {
            refillTokens();
            
            if (tokens > 0) {
                tokens--;
                return true;
            }
            return false;
        }
        
        /**
         * Refill tokens based on time elapsed
         */
        private void refillTokens() {
            long now = System.currentTimeMillis();
            long timeSinceLastRefill = now - lastRefillTime;
            
            // If window has passed, reset tokens
            if (timeSinceLastRefill >= windowSize) {
                tokens = maxTokens;
                lastRefillTime = now;
            }
        }
        
        /**
         * Get time until next token refill (in seconds)
         */
        long getResetTime() {
            long now = System.currentTimeMillis();
            long timeUntilReset = windowSize - (now - lastRefillTime);
            return Math.max(0, timeUntilReset / 1000);
        }
        
        RateLimitStatus getStatus() {
            refillTokens();
            return new RateLimitStatus(
                maxTokens - tokens,  // used
                maxTokens,           // limit
                tokens,              // remaining
                lastRefillTime + windowSize  // reset time
            );
        }
    }
    
    // Rate limit status response
    static class RateLimitStatus {
        int used;
        int limit;
        int remaining;
        long resetTime;
        
        RateLimitStatus(int used, int limit, int remaining, long resetTime) {
            this.used = used;
            this.limit = limit;
            this.remaining = remaining;
            this.resetTime = resetTime;
        }
        
        @Override
        public String toString() {
            return String.format("{used: %d, limit: %d, remaining: %d, reset: %d}",
                used, limit, remaining, resetTime);
        }
    }
    
    // Rate limit result
    static class RateLimitResult {
        boolean allowed;
        String message;
        RateLimitStatus status;
        
        RateLimitResult(boolean allowed, String message, RateLimitStatus status) {
            this.allowed = allowed;
            this.message = message;
            this.status = status;
        }
        
        @Override
        public String toString() {
            return (allowed ? "✓ Allowed" : "✗ Denied") + " - " + message + 
                   " " + status;
        }
    }
    
    // Client buckets - ConcurrentHashMap for thread safety
    private ConcurrentHashMap<String, TokenBucket> clientBuckets;
    
    // Configuration
    private final int requestsPerHour;
    private final long windowSizeMs;
    
    // Statistics
    private AtomicLong totalRequests;
    private AtomicLong allowedRequests;
    private AtomicLong deniedRequests;
    
    // Cleanup scheduler
    private ScheduledExecutorService cleanupScheduler;
    
    public DistributedRateLimiter(int requestsPerHour) {
        this.requestsPerHour = requestsPerHour;
        this.windowSizeMs = 60 * 60 * 1000; // 1 hour in milliseconds
        this.clientBuckets = new ConcurrentHashMap<>();
        this.totalRequests = new AtomicLong(0);
        this.allowedRequests = new AtomicLong(0);
        this.deniedRequests = new AtomicLong(0);
        
        // Start cleanup thread
        startCleanupScheduler();
    }
    
    /**
     * Check if request is allowed
     * Time Complexity: O(1)
     */
    public RateLimitResult checkRateLimit(String clientId) {
        totalRequests.incrementAndGet();
        
        // Get or create token bucket for client
        TokenBucket bucket = clientBuckets.computeIfAbsent(clientId, 
            k -> new TokenBucket(k, requestsPerHour, windowSizeMs));
        
        boolean allowed = bucket.tryConsume();
        RateLimitStatus status = bucket.getStatus();
        
        if (allowed) {
            allowedRequests.incrementAndGet();
            return new RateLimitResult(true, 
                "Request allowed", status);
        } else {
            deniedRequests.incrementAndGet();
            long retryAfter = bucket.getResetTime();
            return new RateLimitResult(false, 
                "Rate limit exceeded, retry after " + retryAfter + "s", status);
        }
    }
    
    /**
     * Get current rate limit status for a client
     */
    public RateLimitStatus getRateLimitStatus(String clientId) {
        TokenBucket bucket = clientBuckets.get(clientId);
        
        if (bucket == null) {
            return new RateLimitStatus(0, requestsPerHour, requestsPerHour, 
                                      System.currentTimeMillis() + windowSizeMs);
        }
        
        return bucket.getStatus();
    }
    
    /**
     * Reset rate limit for a client
     */
    public void resetRateLimit(String clientId) {
        clientBuckets.remove(clientId);
        System.out.println("Reset rate limit for client: " + clientId);
    }
    
    /**
     * Start cleanup scheduler to remove inactive clients
     */
    private void startCleanupScheduler() {
        cleanupScheduler = Executors.newScheduledThreadPool(1);
        
        // Clean up every 10 minutes
        cleanupScheduler.scheduleAtFixedRate(() -> {
            cleanupInactiveClients();
        }, 10, 10, TimeUnit.MINUTES);
    }
    
    /**
     * Remove inactive clients (optimization for memory)
     */
    private void cleanupInactiveClients() {
        long now = System.currentTimeMillis();
        int removed = 0;
        
        Iterator<Map.Entry<String, TokenBucket>> iterator = 
            clientBuckets.entrySet().iterator();
        
        while (iterator.hasNext()) {
            Map.Entry<String, TokenBucket> entry = iterator.next();
            TokenBucket bucket = entry.getValue();
            
            // Remove if inactive for more than 2 hours
            if (now - bucket.lastRefillTime > 2 * windowSizeMs) {
                iterator.remove();
                removed++;
            }
        }
        
        if (removed > 0) {
            System.out.println("[Cleanup] Removed " + removed + " inactive clients");
        }
    }
    
    /**
     * Get system statistics
     */
    public void printStatistics() {
        System.out.println("\n=== Rate Limiter Statistics ===");
        System.out.println("Total Requests: " + totalRequests.get());
        System.out.println("Allowed: " + allowedRequests.get());
        System.out.println("Denied: " + deniedRequests.get());
        
        if (totalRequests.get() > 0) {
            double allowRate = (allowedRequests.get() * 100.0) / totalRequests.get();
            System.out.println("Allow Rate: " + String.format("%.2f", allowRate) + "%");
        }
        
        System.out.println("Active Clients: " + clientBuckets.size());
        System.out.println("Limit: " + requestsPerHour + " requests/hour");
    }
    
    /**
     * Get top clients by usage
     */
    public void printTopClients(int n) {
        System.out.println("\n=== Top " + n + " Clients by Usage ===");
        
        List<Map.Entry<String, Integer>> clientUsage = new ArrayList<>();
        for (Map.Entry<String, TokenBucket> entry : clientBuckets.entrySet()) {
            int used = entry.getValue().getStatus().used;
            clientUsage.add(new AbstractMap.SimpleEntry<>(entry.getKey(), used));
        }
        
        // Sort by usage (highest first)
        clientUsage.sort((a, b) -> Integer.compare(b.getValue(), a.getValue()));
        
        for (int i = 0; i < Math.min(n, clientUsage.size()); i++) {
            Map.Entry<String, Integer> entry = clientUsage.get(i);
            System.out.printf("  %d. %s: %d requests used\n", 
                i + 1, entry.getKey(), entry.getValue());
        }
    }
    
    /**
     * Simulate concurrent requests
     */
    public void simulateConcurrentRequests(String clientId, int numRequests) 
            throws InterruptedException {
        System.out.println("\n--- Simulating " + numRequests + 
                         " concurrent requests for " + clientId + " ---");
        
        ExecutorService executor = Executors.newFixedThreadPool(10);
        CountDownLatch latch = new CountDownLatch(numRequests);
        AtomicInteger allowed = new AtomicInteger(0);
        AtomicInteger denied = new AtomicInteger(0);
        
        long startTime = System.currentTimeMillis();
        
        for (int i = 0; i < numRequests; i++) {
            executor.submit(() -> {
                try {
                    RateLimitResult result = checkRateLimit(clientId);
                    if (result.allowed) {
                        allowed.incrementAndGet();
                    } else {
                        denied.incrementAndGet();
                    }
                } finally {
                    latch.countDown();
                }
            });
        }
        
        latch.await();
        executor.shutdown();
        
        long endTime = System.currentTimeMillis();
        
        System.out.println("Completed in " + (endTime - startTime) + "ms");
        System.out.println("Allowed: " + allowed.get());
        System.out.println("Denied: " + denied.get());
    }
    
    /**
     * Shutdown the rate limiter
     */
    public void shutdown() {
        if (cleanupScheduler != null) {
            cleanupScheduler.shutdown();
        }
    }
    
    // Main method with test cases
    public static void main(String[] args) throws InterruptedException {
        // Create rate limiter with 1000 requests per hour limit
        DistributedRateLimiter rateLimiter = new DistributedRateLimiter(1000);
        
        System.out.println("=== Distributed Rate Limiter for API Gateway ===\n");
        System.out.println("Configuration: " + 1000 + " requests per hour per client\n");
        
        // Test 1: Normal usage
        System.out.println("--- Test 1: Normal Usage ---");
        RateLimitResult result1 = rateLimiter.checkRateLimit("client_abc123");
        System.out.println("Request 1: " + result1);
        
        RateLimitResult result2 = rateLimiter.checkRateLimit("client_abc123");
        System.out.println("Request 2: " + result2);
        
        // Test 2: Exhaust limit
        System.out.println("\n--- Test 2: Exhausting Rate Limit ---");
        String testClient = "client_test001";
        
        for (int i = 0; i < 1002; i++) {
            RateLimitResult result = rateLimiter.checkRateLimit(testClient);
            
            if (i < 5 || i >= 998) {
                System.out.println("Request " + (i + 1) + ": " + result);
            } else if (i == 5) {
                System.out.println("... (requests 6-998)");
            }
        }
        
        // Test 3: Multiple clients
        System.out.println("\n--- Test 3: Multiple Clients ---");
        String[] clients = {"client_A", "client_B", "client_C"};
        
        for (String client : clients) {
            for (int i = 0; i < 50; i++) {
                rateLimiter.checkRateLimit(client);
            }
            System.out.println(client + " - Status: " + 
                             rateLimiter.getRateLimitStatus(client));
        }
        
        // Test 4: Concurrent requests
        rateLimiter.simulateConcurrentRequests("client_concurrent", 1100);
        
        // Test 5: Performance benchmark
        System.out.println("\n--- Performance Benchmark ---");
        
        long startTime = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            rateLimiter.checkRateLimit("benchmark_client");
        }
        long endTime = System.nanoTime();
        
        double avgTime = (endTime - startTime) / 10000.0 / 1000.0; // microseconds
        System.out.println("Average rate limit check time (10,000 iterations): " + 
            String.format("%.3f", avgTime) + " μs");
        System.out.println("Response time: " + (avgTime < 1000 ? "< 1ms ✓" : "> 1ms ✗"));
        
        // Print statistics
        rateLimiter.printStatistics();
        
        // Print top clients
        rateLimiter.printTopClients(5);
        
        // Shutdown
        rateLimiter.shutdown();
        System.out.println("\nRate limiter shutdown complete.");
    }
}
