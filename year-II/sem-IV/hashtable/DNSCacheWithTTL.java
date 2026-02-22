import java.util.*;
import java.util.concurrent.*;

/**
 * Problem 3: DNS Cache with TTL (Time To Live)
 * 
 * Scenario: Build a DNS resolver cache that stores domain-to-IP mappings 
 * to reduce lookup times from 100ms to <1ms. Cache entries expire after TTL.
 * 
 * Features:
 * - Domain name → IP address mappings with TTL
 * - Automatic expiration of entries
 * - Cache miss handling via upstream DNS
 * - Cache hit/miss ratio tracking
 * - LRU eviction when cache is full
 * 
 * Concepts: Hash table with custom Entry class, chaining for collision resolution,
 * time-based operations, performance metrics
 */
public class DNSCacheWithTTL {
    
    // DNS Entry class with TTL support
    static class DNSEntry {
        String domain;
        String ipAddress;
        long timestamp;      // When entry was created
        long expiryTime;     // When entry expires (timestamp + TTL)
        int ttl;             // Time to live in seconds
        int accessCount;     // For LRU tracking
        long lastAccessed;   // Last access time
        
        DNSEntry(String domain, String ipAddress, int ttl) {
            this.domain = domain;
            this.ipAddress = ipAddress;
            this.timestamp = System.currentTimeMillis();
            this.ttl = ttl;
            this.expiryTime = timestamp + (ttl * 1000L);
            this.accessCount = 0;
            this.lastAccessed = timestamp;
        }
        
        boolean isExpired() {
            return System.currentTimeMillis() > expiryTime;
        }
        
        void updateAccess() {
            this.accessCount++;
            this.lastAccessed = System.currentTimeMillis();
        }
        
        @Override
        public String toString() {
            long remainingTTL = (expiryTime - System.currentTimeMillis()) / 1000;
            return ipAddress + " (TTL: " + (remainingTTL > 0 ? remainingTTL : 0) + "s)";
        }
    }
    
    // Cache storage
    private LinkedHashMap<String, DNSEntry> cache;
    
    // Maximum cache size
    private final int maxCacheSize;
    
    // Statistics
    private long cacheHits;
    private long cacheMisses;
    private long totalLookupTime; // in nanoseconds
    private long lookupCount;
    
    // Simulated upstream DNS (for demonstration)
    private Map<String, String> upstreamDNS;
    
    // Background cleanup thread
    private ScheduledExecutorService cleanupScheduler;
    
    public DNSCacheWithTTL(int maxCacheSize) {
        this.maxCacheSize = maxCacheSize;
        
        // LinkedHashMap with access-order for LRU
        this.cache = new LinkedHashMap<String, DNSEntry>(maxCacheSize, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, DNSEntry> eldest) {
                // LRU eviction when cache is full
                return size() > maxCacheSize;
            }
        };
        
        this.cacheHits = 0;
        this.cacheMisses = 0;
        this.totalLookupTime = 0;
        this.lookupCount = 0;
        
        // Initialize upstream DNS (simulated)
        initializeUpstreamDNS();
        
        // Start background cleanup thread
        startCleanupThread();
    }
    
    /**
     * Initialize simulated upstream DNS server
     */
    private void initializeUpstreamDNS() {
        upstreamDNS = new HashMap<>();
        upstreamDNS.put("google.com", "172.217.14.206");
        upstreamDNS.put("facebook.com", "157.240.241.35");
        upstreamDNS.put("amazon.com", "205.251.242.103");
        upstreamDNS.put("netflix.com", "54.246.180.136");
        upstreamDNS.put("github.com", "140.82.121.4");
        upstreamDNS.put("stackoverflow.com", "151.101.1.69");
        upstreamDNS.put("reddit.com", "151.101.65.140");
        upstreamDNS.put("twitter.com", "104.244.42.1");
    }
    
    /**
     * Resolve a domain name to IP address
     * Time Complexity: O(1) average case
     */
    public String resolve(String domain) {
        long startTime = System.nanoTime();
        
        // Check if in cache
        DNSEntry entry = cache.get(domain);
        
        if (entry != null && !entry.isExpired()) {
            // Cache HIT
            cacheHits++;
            entry.updateAccess();
            
            long endTime = System.nanoTime();
            long lookupTime = endTime - startTime;
            totalLookupTime += lookupTime;
            lookupCount++;
            
            System.out.println("Cache HIT → " + domain + " → " + entry.ipAddress + 
                             " (retrieved in " + String.format("%.3f", lookupTime / 1000.0) + " μs)");
            return entry.ipAddress;
        }
        
        // Cache MISS or EXPIRED
        if (entry != null && entry.isExpired()) {
            System.out.println("Cache EXPIRED → " + domain);
            cache.remove(domain);
        } else {
            System.out.println("Cache MISS → " + domain);
        }
        
        cacheMisses++;
        
        // Query upstream DNS (simulated with 100ms delay)
        String ipAddress = queryUpstreamDNS(domain);
        
        if (ipAddress != null) {
            // Random TTL between 60-600 seconds
            int ttl = 300; // 5 minutes default
            addToCache(domain, ipAddress, ttl);
            
            long endTime = System.nanoTime();
            long lookupTime = endTime - startTime;
            totalLookupTime += lookupTime;
            lookupCount++;
            
            System.out.println("  → Query upstream → " + ipAddress + 
                             " (TTL: " + ttl + "s) (retrieved in " + 
                             String.format("%.2f", lookupTime / 1000000.0) + " ms)");
            return ipAddress;
        }
        
        return null; // Domain not found
    }
    
    /**
     * Simulate upstream DNS query (with delay)
     */
    private String queryUpstreamDNS(String domain) {
        try {
            // Simulate network delay (100ms)
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        return upstreamDNS.getOrDefault(domain, null);
    }
    
    /**
     * Add entry to cache
     */
    private void addToCache(String domain, String ipAddress, int ttl) {
        DNSEntry entry = new DNSEntry(domain, ipAddress, ttl);
        cache.put(domain, entry);
    }
    
    /**
     * Manually add/update DNS entry
     */
    public void addEntry(String domain, String ipAddress, int ttl) {
        addToCache(domain, ipAddress, ttl);
        System.out.println("Added to cache: " + domain + " → " + ipAddress + 
                         " (TTL: " + ttl + "s)");
    }
    
    /**
     * Start background cleanup thread to remove expired entries
     */
    private void startCleanupThread() {
        cleanupScheduler = Executors.newScheduledThreadPool(1);
        
        // Run cleanup every 10 seconds
        cleanupScheduler.scheduleAtFixedRate(() -> {
            cleanupExpiredEntries();
        }, 10, 10, TimeUnit.SECONDS);
    }
    
    /**
     * Clean up expired entries
     */
    private void cleanupExpiredEntries() {
        Iterator<Map.Entry<String, DNSEntry>> iterator = cache.entrySet().iterator();
        int removedCount = 0;
        
        while (iterator.hasNext()) {
            Map.Entry<String, DNSEntry> entry = iterator.next();
            if (entry.getValue().isExpired()) {
                iterator.remove();
                removedCount++;
            }
        }
        
        if (removedCount > 0) {
            System.out.println("\n[Cleanup] Removed " + removedCount + " expired entries");
        }
    }
    
    /**
     * Get cache statistics
     */
    public void getCacheStats() {
        System.out.println("\n=== DNS Cache Statistics ===");
        System.out.println("Cache Size: " + cache.size() + " / " + maxCacheSize);
        System.out.println("Total Lookups: " + lookupCount);
        System.out.println("Cache Hits: " + cacheHits);
        System.out.println("Cache Misses: " + cacheMisses);
        
        if (lookupCount > 0) {
            double hitRate = (cacheHits * 100.0) / lookupCount;
            double avgLookupTime = (totalLookupTime / (double) lookupCount) / 1000.0; // microseconds
            
            System.out.println("Hit Rate: " + String.format("%.1f", hitRate) + "%");
            System.out.println("Avg Lookup Time: " + String.format("%.2f", avgLookupTime) + " μs");
        }
        
        System.out.println("\nCached Domains:");
        for (Map.Entry<String, DNSEntry> entry : cache.entrySet()) {
            System.out.println("  " + entry.getKey() + " → " + entry.getValue());
        }
    }
    
    /**
     * Clear expired entries on demand
     */
    public int clearExpired() {
        int count = 0;
        Iterator<Map.Entry<String, DNSEntry>> iterator = cache.entrySet().iterator();
        
        while (iterator.hasNext()) {
            if (iterator.next().getValue().isExpired()) {
                iterator.remove();
                count++;
            }
        }
        
        return count;
    }
    
    /**
     * Shutdown cleanup thread
     */
    public void shutdown() {
        if (cleanupScheduler != null) {
            cleanupScheduler.shutdown();
        }
    }
    
    // Main method with test cases
    public static void main(String[] args) throws InterruptedException {
        DNSCacheWithTTL dnsCache = new DNSCacheWithTTL(10);
        
        System.out.println("=== DNS Cache with TTL System ===\n");
        
        // Test 1: First lookup (cache miss)
        System.out.println("--- Test 1: First Lookup ---");
        dnsCache.resolve("google.com");
        System.out.println();
        
        // Test 2: Second lookup (cache hit)
        System.out.println("--- Test 2: Second Lookup (Cache Hit) ---");
        dnsCache.resolve("google.com");
        System.out.println();
        
        // Test 3: Multiple lookups
        System.out.println("--- Test 3: Multiple Domain Lookups ---");
        dnsCache.resolve("facebook.com");
        dnsCache.resolve("amazon.com");
        dnsCache.resolve("netflix.com");
        dnsCache.resolve("github.com");
        System.out.println();
        
        // Test 4: Cache hits
        System.out.println("--- Test 4: Verify Cache Hits ---");
        dnsCache.resolve("google.com");
        dnsCache.resolve("facebook.com");
        dnsCache.resolve("amazon.com");
        System.out.println();
        
        // Test 5: TTL expiration (add entry with short TTL)
        System.out.println("--- Test 5: TTL Expiration Test ---");
        dnsCache.addEntry("test.com", "1.2.3.4", 3); // 3 second TTL
        dnsCache.resolve("test.com"); // Should hit cache
        
        System.out.println("\nWaiting 4 seconds for TTL expiration...");
        Thread.sleep(4000);
        
        dnsCache.resolve("test.com"); // Should be expired (miss)
        System.out.println();
        
        // Test 6: LRU eviction
        System.out.println("--- Test 6: LRU Eviction (Cache Full) ---");
        for (int i = 1; i <= 12; i++) {
            dnsCache.addEntry("domain" + i + ".com", "10.0.0." + i, 600);
        }
        System.out.println("Cache size after adding 12 entries (max 10): " + 
                         dnsCache.cache.size());
        System.out.println();
        
        // Show statistics
        dnsCache.getCacheStats();
        
        // Performance benchmark
        System.out.println("\n--- Performance Benchmark ---");
        System.out.println("Running 1000 cache hit lookups...");
        
        long startTime = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            dnsCache.cache.get("google.com");
        }
        long endTime = System.nanoTime();
        
        double avgTime = (endTime - startTime) / 1000.0 / 1000.0; // microseconds
        System.out.println("Average time for 1000 lookups: " + 
                         String.format("%.3f", avgTime) + " μs per lookup");
        System.out.println("Time Complexity: O(1) ✓");
        
        // Cleanup
        dnsCache.shutdown();
    }
}
