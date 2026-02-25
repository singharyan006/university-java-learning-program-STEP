import java.util.*;

/**
 * Problem 10: Multi-Level Cache System with Hash Tables
 * 
 * Scenario: Design a cache hierarchy for a video streaming service (like Netflix)
 * with L1 (memory), L2 (SSD), and L3 (database) levels. 
 * Optimize for 10M concurrent users.
 * 
 * Features:
 * - L1 Cache: 10,000 most popular videos (in-memory HashMap)
 * - L2 Cache: 100,000 frequently accessed videos (SSD-backed)
 * - L3: Database (slow, all videos)
 * - LRU eviction at each level
 * - Promote videos between levels based on access patterns
 * - Track cache hit ratios for each level
 * - Handle cache invalidation when content updates
 * 
 * Concepts: Multiple hash tables with different purposes, resizing/rehashing,
 * performance benchmarking, load factor optimization
 */
public class MultiLevelCacheSystem {
    
    // Video data class
    static class VideoData {
        String videoId;
        String title;
        String url;
        long size;
        int accessCount;
        long lastAccessed;
        
        VideoData(String videoId, String title, String url, long size) {
            this.videoId = videoId;
            this.title = title;
            this.url = url;
            this.size = size;
            this.accessCount = 0;
            this.lastAccessed = System.currentTimeMillis();
        }
        
        void incrementAccess() {
            this.accessCount++;
            this.lastAccessed = System.currentTimeMillis();
        }
        
        @Override
        public String toString() {
            return String.format("%s (%s) - %d accesses", videoId, title, accessCount);
        }
    }
    
    // Cache level enum
    enum CacheLevel {
        L1, L2, L3
    }
    
    // Cache result class
    static class CacheResult {
        boolean success;
        CacheLevel level;
        VideoData data;
        double retrievalTime;
        
        CacheResult(boolean success, CacheLevel level, VideoData data, double retrievalTime) {
            this.success = success;
            this.level = level;
            this.data = data;
            this.retrievalTime = retrievalTime;
        }
        
        @Override
        public String toString() {
            if (success) {
                return String.format("%s Cache HIT (%.2fms) → %s",
                    level, retrievalTime, data);
            } else {
                return "Cache MISS";
            }
        }
    }
    
    // L1 Cache: In-memory, fastest, smallest (10K videos)
    private LinkedHashMap<String, VideoData> l1Cache;
    private final int L1_CAPACITY = 10000;
    private final double L1_LATENCY = 0.5; // ms
    
    // L2 Cache: SSD-backed, medium speed, medium size (100K videos)
    private LinkedHashMap<String, String> l2Cache; // videoId → file path
    private final int L2_CAPACITY = 100000;
    private final double L2_LATENCY = 5.0; // ms
    
    // L3: Database (simulated with HashMap)
    private HashMap<String, VideoData> l3Database;
    private final double L3_LATENCY = 150.0; // ms
    
    // Access count tracking for promotion decisions
    private HashMap<String, Integer> accessCounts;
    
    // Statistics
    private long l1Hits, l2Hits, l3Hits;
    private long totalRequests;
    private double totalRetrievalTime;
    
    // Promotion thresholds
    private final int L2_TO_L1_THRESHOLD = 5;  // Access count to promote to L1
    private final int L3_TO_L2_THRESHOLD = 3;  // Access count to promote to L2
    
    public MultiLevelCacheSystem() {
        // L1: LRU cache with access-order
        this.l1Cache = new LinkedHashMap<String, VideoData>(L1_CAPACITY, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, VideoData> eldest) {
                if (size() > L1_CAPACITY) {
                    // Demote to L2 when evicted from L1
                    demoteToL2(eldest.getKey(), eldest.getValue());
                    return true;
                }
                return false;
            }
        };
        
        // L2: LRU cache with access-order
        this.l2Cache = new LinkedHashMap<String, String>(L2_CAPACITY, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, String> eldest) {
                if (size() > L2_CAPACITY) {
                    // Just remove from L2 (still in L3)
                    return true;
                }
                return false;
            }
        };
        
        // L3: Full database
        this.l3Database = new HashMap<>();
        
        // Access tracking
        this.accessCounts = new HashMap<>();
        
        // Statistics
        this.l1Hits = 0;
        this.l2Hits = 0;
        this.l3Hits = 0;
        this.totalRequests = 0;
        this.totalRetrievalTime = 0;
    }
    
    /**
     * Add a video to the database (L3)
     * Time Complexity: O(1)
     */
    public void addVideo(VideoData video) {
        l3Database.put(video.videoId, video);
        accessCounts.put(video.videoId, 0);
    }
    
    /**
     * Get a video (checks L1 → L2 → L3)
     * Time Complexity: O(1) average for each level
     */
    public CacheResult getVideo(String videoId) {
        totalRequests++;
        long startTime = System.nanoTime();
        
        // Check L1 Cache
        VideoData video = l1Cache.get(videoId);
        if (video != null) {
            video.incrementAccess();
            l1Hits++;
            
            double retrievalTime = L1_LATENCY;
            totalRetrievalTime += retrievalTime;
            
            return new CacheResult(true, CacheLevel.L1, video, retrievalTime);
        }
        
        // Check L2 Cache
        String l2Path = l2Cache.get(videoId);
        if (l2Path != null) {
            // Retrieve from L2 (simulated)
            video = l3Database.get(videoId); // Get actual data from L3
            if (video != null) {
                video.incrementAccess();
                l2Hits++;
                
                double retrievalTime = L2_LATENCY;
                totalRetrievalTime += retrievalTime;
                
                // Track access count for potential promotion
                int count = accessCounts.getOrDefault(videoId, 0) + 1;
                accessCounts.put(videoId, count);
                
                // Promote to L1 if accessed frequently
                if (count >= L2_TO_L1_THRESHOLD) {
                    promoteToL1(videoId, video);
                }
                
                return new CacheResult(true, CacheLevel.L2, video, retrievalTime);
            }
        }
        
        // Check L3 Database
        video = l3Database.get(videoId);
        if (video != null) {
            video.incrementAccess();
            l3Hits++;
            
            double retrievalTime = L3_LATENCY;
            totalRetrievalTime += retrievalTime;
            
            // Track access count
            int count = accessCounts.getOrDefault(videoId, 0) + 1;
            accessCounts.put(videoId, count);
            
            // Add to L2 if accessed multiple times
            if (count >= L3_TO_L2_THRESHOLD) {
                addToL2(videoId);
            }
            
            return new CacheResult(true, CacheLevel.L3, video, retrievalTime);
        }
        
        // Not found
        return new CacheResult(false, null, null, 0);
    }
    
    /**
     * Promote video to L1 cache
     */
    private void promoteToL1(String videoId, VideoData video) {
        l1Cache.put(videoId, video);
        System.out.println("  [Promotion] " + videoId + " promoted to L1");
    }
    
    /**
     * Demote video from L1 to L2
     */
    private void demoteToL2(String videoId, VideoData video) {
        // Add to L2 as file path (simulated)
        l2Cache.put(videoId, "/ssd/videos/" + videoId);
    }
    
    /**
     * Add video to L2 cache
     */
    private void addToL2(String videoId) {
        l2Cache.put(videoId, "/ssd/videos/" + videoId);
    }
    
    /**
     * Invalidate cache entry (e.g., when content updates)
     * Time Complexity: O(1)
     */
    public void invalidateVideo(String videoId) {
        l1Cache.remove(videoId);
        l2Cache.remove(videoId);
        accessCounts.put(videoId, 0);
        System.out.println("Invalidated cache for: " + videoId);
    }
    
    /**
     * Pre-warm cache with popular videos
     */
    public void preWarmCache(List<String> popularVideoIds) {
        System.out.println("\nPre-warming cache with " + popularVideoIds.size() + " popular videos...");
        
        for (String videoId : popularVideoIds) {
            VideoData video = l3Database.get(videoId);
            if (video != null) {
                l1Cache.put(videoId, video);
            }
        }
        
        System.out.println("Cache pre-warmed. L1 size: " + l1Cache.size());
    }
    
    /**
     * Get cache statistics
     */
    public void getStatistics() {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("              MULTI-LEVEL CACHE STATISTICS");
        System.out.println("=".repeat(70));
        
        System.out.println("\n📊 Cache Hit Statistics:");
        System.out.printf("   Total Requests: %,d\n", totalRequests);
        
        if (totalRequests > 0) {
            double l1HitRate = (l1Hits * 100.0) / totalRequests;
            double l2HitRate = (l2Hits * 100.0) / totalRequests;
            double l3HitRate = (l3Hits * 100.0) / totalRequests;
            double overallHitRate = ((l1Hits + l2Hits + l3Hits) * 100.0) / totalRequests;
            double avgTime = totalRetrievalTime / totalRequests;
            
            System.out.println("\n   L1 Cache (In-Memory):");
            System.out.printf("     Hit Rate: %.1f%% (%,d hits)\n", l1HitRate, l1Hits);
            System.out.printf("     Avg Time: %.2f ms\n", L1_LATENCY);
            System.out.printf("     Size: %,d / %,d videos\n", l1Cache.size(), L1_CAPACITY);
            System.out.printf("     Load Factor: %.2f\n", l1Cache.size() / (double) L1_CAPACITY);
            
            System.out.println("\n   L2 Cache (SSD):");
            System.out.printf("     Hit Rate: %.1f%% (%,d hits)\n", l2HitRate, l2Hits);
            System.out.printf("     Avg Time: %.2f ms\n", L2_LATENCY);
            System.out.printf("     Size: %,d / %,d videos\n", l2Cache.size(), L2_CAPACITY);
            System.out.printf("     Load Factor: %.2f\n", l2Cache.size() / (double) L2_CAPACITY);
            
            System.out.println("\n   L3 Database:");
            System.out.printf("     Hit Rate: %.1f%% (%,d hits)\n", l3HitRate, l3Hits);
            System.out.printf("     Avg Time: %.2f ms\n", L3_LATENCY);
            System.out.printf("     Size: %,d videos\n", l3Database.size());
            
            System.out.println("\n📈 Overall Performance:");
            System.out.printf("   Overall Hit Rate: %.1f%%\n", overallHitRate);
            System.out.printf("   Average Retrieval Time: %.2f ms\n", avgTime);
            
            // Performance improvement
            double improvementVsL3 = ((L3_LATENCY - avgTime) / L3_LATENCY) * 100;
            System.out.printf("   Performance Improvement: %.1f%% faster than L3-only\n", 
                improvementVsL3);
        }
        
        System.out.println("\n" + "=".repeat(70));
    }
    
    /**
     * Get top accessed videos
     */
    public List<VideoData> getTopVideos(int n) {
        List<VideoData> allVideos = new ArrayList<>(l3Database.values());
        allVideos.sort((a, b) -> Integer.compare(b.accessCount, a.accessCount));
        return allVideos.subList(0, Math.min(n, allVideos.size()));
    }
    
    /**
     * Generate sample video database
     */
    public void generateSampleDatabase(int count) {
        String[] categories = {"Action", "Comedy", "Drama", "SciFi", "Documentary"};
        Random random = new Random();
        
        for (int i = 1; i <= count; i++) {
            String videoId = "video_" + String.format("%06d", i);
            String category = categories[random.nextInt(categories.length)];
            String title = category + " Movie " + i;
            String url = "https://cdn.example.com/" + videoId;
            long size = 1024 * 1024 * (100 + random.nextInt(400)); // 100-500 MB
            
            addVideo(new VideoData(videoId, title, url, size));
        }
    }
    
    /**
     * Simulate realistic access pattern (Zipf distribution - popular videos accessed more)
     */
    public void simulateRealisticAccess(int numAccesses) {
        Random random = new Random();
        int totalVideos = l3Database.size();
        
        for (int i = 0; i < numAccesses; i++) {
            // Zipf distribution: 20% of videos get 80% of views
            double rand = random.nextDouble();
            int videoIndex;
            
            if (rand < 0.8) {
                // 80% of accesses go to top 20% of videos
                videoIndex = random.nextInt(totalVideos / 5);
            } else {
                // 20% of accesses go to remaining 80% of videos
                videoIndex = totalVideos / 5 + random.nextInt(totalVideos * 4 / 5);
            }
            
            String videoId = "video_" + String.format("%06d", videoIndex + 1);
            getVideo(videoId);
        }
    }
    
    // Main method with test cases
    public static void main(String[] args) {
        MultiLevelCacheSystem cache = new MultiLevelCacheSystem();
        
        System.out.println("=== Multi-Level Cache System for Video Streaming ===\n");
        
        // Generate sample database
        System.out.println("Generating video database...");
        cache.generateSampleDatabase(1000);
        System.out.println("Database initialized with " + cache.l3Database.size() + " videos.\n");
        
        // Test 1: Cold cache access
        System.out.println("--- Test 1: Cold Cache Access ---");
        CacheResult result1 = cache.getVideo("video_000001");
        System.out.println(result1);
        
        CacheResult result2 = cache.getVideo("video_000001");
        System.out.println(result2);
        System.out.println();
        
        // Test 2: Promotion from L3 → L2 → L1
        System.out.println("--- Test 2: Cache Promotion ---");
        System.out.println("Accessing video_000100 multiple times:");
        
        for (int i = 1; i <= 6; i++) {
            CacheResult result = cache.getVideo("video_000100");
            System.out.println("  Access " + i + ": " + result.level + " Cache (" + 
                String.format("%.2f", result.retrievalTime) + "ms)");
        }
        System.out.println();
        
        // Test 3: Simulate realistic access pattern
        System.out.println("--- Test 3: Realistic Access Simulation ---");
        System.out.println("Simulating 10,000 video accesses with Zipf distribution...");
        cache.simulateRealisticAccess(10000);
        System.out.println("Simulation complete.\n");
        
        // Test 4: Cache invalidation
        System.out.println("--- Test 4: Cache Invalidation ---");
        cache.invalidateVideo("video_000001");
        CacheResult result3 = cache.getVideo("video_000001");
        System.out.println("After invalidation: " + result3);
        System.out.println();
        
        // Test 5: Pre-warming cache
        System.out.println("--- Test 5: Cache Pre-warming ---");
        List<VideoData> topVideos = cache.getTopVideos(100);
        List<String> topVideoIds = new ArrayList<>();
        for (VideoData video : topVideos) {
            topVideoIds.add(video.videoId);
        }
        cache.preWarmCache(topVideoIds);
        
        // Display statistics
        cache.getStatistics();
        
        // Show top videos
        System.out.println("\n📺 Top 10 Most Watched Videos:");
        List<VideoData> top10 = cache.getTopVideos(10);
        for (int i = 0; i < top10.size(); i++) {
            System.out.println("   " + (i + 1) + ". " + top10.get(i));
        }
        
        // Performance benchmark
        System.out.println("\n--- Performance Benchmark ---");
        
        // Benchmark L1 access
        long startTime = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            cache.l1Cache.get("video_000001");
        }
        long endTime = System.nanoTime();
        
        double avgL1Time = (endTime - startTime) / 10000.0 / 1000.0; // microseconds
        System.out.println("L1 Cache lookup time: " + String.format("%.3f", avgL1Time) + " μs");
        
        // Benchmark full getVideo with cache hits
        cache.getVideo("video_000050"); // Warm up
        
        startTime = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            cache.getVideo("video_000050");
        }
        endTime = System.nanoTime();
        
        double avgGetTime = (endTime - startTime) / 1000.0 / 1000.0; // microseconds
        System.out.println("getVideo() time (L1 hit): " + 
            String.format("%.3f", avgGetTime) + " μs");
        
        System.out.println("\nTime Complexity: O(1) for each cache level ✓");
        System.out.println("Space Complexity: O(n) where n = total videos ✓");
    }
}
