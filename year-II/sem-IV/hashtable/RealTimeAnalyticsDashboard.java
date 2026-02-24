import java.util.*;
import java.util.concurrent.*;

/**
 * Problem 5: Real-Time Analytics Dashboard for Website Traffic
 * 
 * Scenario: A news website gets 1 million page views per hour. 
 * Marketing team needs real-time analytics showing top pages, 
 * traffic sources, and user locations.
 * 
 * Features:
 * - Process incoming page view events in real-time
 * - Maintain top 10 most visited pages
 * - Track unique visitors per page
 * - Count visits by traffic source
 * - Update dashboard every 5 seconds with zero lag
 * 
 * Concepts: Frequency counting, multiple hash tables, 
 * load factor and resizing, time/space complexity optimization
 */
public class RealTimeAnalyticsDashboard {
    
    // Page View Event class
    static class PageViewEvent {
        String url;
        String userId;
        String source;
        long timestamp;
        String location;
        
        PageViewEvent(String url, String userId, String source, String location) {
            this.url = url;
            this.userId = userId;
            this.source = source;
            this.location = location;
            this.timestamp = System.currentTimeMillis();
        }
        
        @Override
        public String toString() {
            return String.format("{url: %s, user: %s, source: %s, location: %s}",
                url, userId, source, location);
        }
    }
    
    // Analytics data structures
    private ConcurrentHashMap<String, AtomicLong> pageViewCounts;
    private ConcurrentHashMap<String, Set<String>> uniqueVisitors;
    private ConcurrentHashMap<String, AtomicLong> trafficSources;
    private ConcurrentHashMap<String, AtomicLong> locationCounts;
    
    // Event queue for batch processing
    private BlockingQueue<PageViewEvent> eventQueue;
    
    // Statistics
    private AtomicLong totalEvents;
    private AtomicLong eventsProcessed;
    
    // Background processor
    private ScheduledExecutorService dashboardUpdater;
    private ExecutorService eventProcessor;
    private volatile boolean running;
    
    public RealTimeAnalyticsDashboard() {
        this.pageViewCounts = new ConcurrentHashMap<>();
        this.uniqueVisitors = new ConcurrentHashMap<>();
        this.trafficSources = new ConcurrentHashMap<>();
        this.locationCounts = new ConcurrentHashMap<>();
        this.eventQueue = new LinkedBlockingQueue<>();
        this.totalEvents = new AtomicLong(0);
        this.eventsProcessed = new AtomicLong(0);
        this.running = true;
        
        // Start background processors
        startEventProcessor();
        startDashboardUpdater();
    }
    
    /**
     * Process a page view event
     * Time Complexity: O(1)
     */
    public void processEvent(PageViewEvent event) {
        try {
            eventQueue.put(event);
            totalEvents.incrementAndGet();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Convenience method to create and process event
     */
    public void logPageView(String url, String userId, String source, String location) {
        PageViewEvent event = new PageViewEvent(url, userId, source, location);
        processEvent(event);
    }
    
    /**
     * Start background event processor
     */
    private void startEventProcessor() {
        eventProcessor = Executors.newFixedThreadPool(4);
        
        for (int i = 0; i < 4; i++) {
            eventProcessor.submit(() -> {
                while (running || !eventQueue.isEmpty()) {
                    try {
                        PageViewEvent event = eventQueue.poll(100, TimeUnit.MILLISECONDS);
                        if (event != null) {
                            processEventInternal(event);
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
            });
        }
    }
    
    /**
     * Internal event processing
     * Time Complexity: O(1) average
     */
    private void processEventInternal(PageViewEvent event) {
        // Update page view count
        pageViewCounts.putIfAbsent(event.url, new AtomicLong(0));
        pageViewCounts.get(event.url).incrementAndGet();
        
        // Update unique visitors
        uniqueVisitors.putIfAbsent(event.url, ConcurrentHashMap.newKeySet());
        uniqueVisitors.get(event.url).add(event.userId);
        
        // Update traffic sources
        trafficSources.putIfAbsent(event.source, new AtomicLong(0));
        trafficSources.get(event.source).incrementAndGet();
        
        // Update locations
        if (event.location != null) {
            locationCounts.putIfAbsent(event.location, new AtomicLong(0));
            locationCounts.get(event.location).incrementAndGet();
        }
        
        eventsProcessed.incrementAndGet();
    }
    
    /**
     * Start dashboard updater (every 5 seconds)
     */
    private void startDashboardUpdater() {
        dashboardUpdater = Executors.newScheduledThreadPool(1);
        
        dashboardUpdater.scheduleAtFixedRate(() -> {
            // Dashboard update logic (can be used for periodic display)
            // For now, we'll just use getDashboard() when needed
        }, 5, 5, TimeUnit.SECONDS);
    }
    
    /**
     * Get top N most visited pages
     * Time Complexity: O(n log k) where k is topN
     */
    public List<Map.Entry<String, Long>> getTopPages(int topN) {
        // Use min-heap to find top N
        PriorityQueue<Map.Entry<String, Long>> minHeap = new PriorityQueue<>(
            (a, b) -> Long.compare(a.getValue(), b.getValue())
        );
        
        for (Map.Entry<String, AtomicLong> entry : pageViewCounts.entrySet()) {
            long count = entry.getValue().get();
            minHeap.offer(new AbstractMap.SimpleEntry<>(entry.getKey(), count));
            
            if (minHeap.size() > topN) {
                minHeap.poll();
            }
        }
        
        // Convert to sorted list (highest first)
        List<Map.Entry<String, Long>> result = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            result.add(0, minHeap.poll());
        }
        
        return result;
    }
    
    /**
     * Get traffic source distribution
     * Time Complexity: O(n)
     */
    public Map<String, Double> getTrafficSourcePercentages() {
        Map<String, Double> percentages = new HashMap<>();
        
        long total = totalEvents.get();
        if (total == 0) return percentages;
        
        for (Map.Entry<String, AtomicLong> entry : trafficSources.entrySet()) {
            double percentage = (entry.getValue().get() * 100.0) / total;
            percentages.put(entry.getKey(), percentage);
        }
        
        return percentages;
    }
    
    /**
     * Get complete dashboard data
     */
    public void getDashboard() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("         REAL-TIME ANALYTICS DASHBOARD");
        System.out.println("=".repeat(60));
        
        // Top Pages
        System.out.println("\n📊 Top 10 Most Visited Pages:");
        List<Map.Entry<String, Long>> topPages = getTopPages(10);
        
        for (int i = 0; i < topPages.size(); i++) {
            Map.Entry<String, Long> entry = topPages.get(i);
            long uniqueCount = uniqueVisitors.getOrDefault(entry.getKey(), 
                                                          Collections.emptySet()).size();
            
            System.out.printf("   %2d. %-40s - %,8d views (%,6d unique)\n",
                i + 1, entry.getKey(), entry.getValue(), uniqueCount);
        }
        
        // Traffic Sources
        System.out.println("\n🌐 Traffic Sources:");
        Map<String, Double> sourcePercentages = getTrafficSourcePercentages();
        
        // Sort by percentage
        List<Map.Entry<String, Double>> sortedSources = new ArrayList<>(sourcePercentages.entrySet());
        sortedSources.sort((a, b) -> Double.compare(b.getValue(), a.getValue()));
        
        for (Map.Entry<String, Double> entry : sortedSources) {
            long count = trafficSources.get(entry.getKey()).get();
            System.out.printf("   %-15s: %5.1f%% (%,d visits)\n",
                entry.getKey(), entry.getValue(), count);
        }
        
        // Top Locations
        System.out.println("\n📍 Top Locations:");
        List<Map.Entry<String, Long>> topLocations = new ArrayList<>();
        for (Map.Entry<String, AtomicLong> entry : locationCounts.entrySet()) {
            topLocations.add(new AbstractMap.SimpleEntry<>(entry.getKey(), entry.getValue().get()));
        }
        topLocations.sort((a, b) -> Long.compare(b.getValue(), a.getValue()));
        
        for (int i = 0; i < Math.min(5, topLocations.size()); i++) {
            Map.Entry<String, Long> entry = topLocations.get(i);
            double percentage = (entry.getValue() * 100.0) / totalEvents.get();
            System.out.printf("   %d. %-20s: %,8d visits (%.1f%%)\n",
                i + 1, entry.getKey(), entry.getValue(), percentage);
        }
        
        // Overall Statistics
        System.out.println("\n📈 Overall Statistics:");
        System.out.printf("   Total Events Received: %,d\n", totalEvents.get());
        System.out.printf("   Events Processed: %,d\n", eventsProcessed.get());
        System.out.printf("   Events in Queue: %,d\n", eventQueue.size());
        System.out.printf("   Unique Pages: %,d\n", pageViewCounts.size());
        System.out.printf("   Total Unique Visitors: %,d\n", getTotalUniqueVisitors());
        
        System.out.println("=".repeat(60) + "\n");
    }
    
    /**
     * Get total unique visitors across all pages
     */
    private long getTotalUniqueVisitors() {
        Set<String> allVisitors = new HashSet<>();
        for (Set<String> visitors : uniqueVisitors.values()) {
            allVisitors.addAll(visitors);
        }
        return allVisitors.size();
    }
    
    /**
     * Get analytics for specific page
     */
    public void getPageAnalytics(String url) {
        System.out.println("\n=== Analytics for: " + url + " ===");
        
        long views = pageViewCounts.getOrDefault(url, new AtomicLong(0)).get();
        long unique = uniqueVisitors.getOrDefault(url, Collections.emptySet()).size();
        
        System.out.println("Total Views: " + views);
        System.out.println("Unique Visitors: " + unique);
        System.out.println("Avg Views per Visitor: " + 
            (unique > 0 ? String.format("%.2f", views / (double) unique) : "N/A"));
    }
    
    /**
     * Shutdown the dashboard
     */
    public void shutdown() {
        running = false;
        
        if (dashboardUpdater != null) {
            dashboardUpdater.shutdown();
        }
        
        if (eventProcessor != null) {
            eventProcessor.shutdown();
            try {
                eventProcessor.awaitTermination(5, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                eventProcessor.shutdownNow();
            }
        }
    }
    
    // Main method with test cases
    public static void main(String[] args) throws InterruptedException {
        RealTimeAnalyticsDashboard dashboard = new RealTimeAnalyticsDashboard();
        
        System.out.println("=== Real-Time Analytics Dashboard ===\n");
        System.out.println("Simulating website traffic...\n");
        
        // Simulate page views
        String[] pages = {
            "/article/breaking-news",
            "/sports/championship",
            "/tech/ai-breakthrough",
            "/article/economy-update",
            "/entertainment/movie-review"
        };
        
        String[] sources = {"Google", "Facebook", "Direct", "Twitter", "LinkedIn"};
        String[] locations = {"USA", "UK", "Canada", "India", "Australia"};
        
        Random random = new Random();
        
        // Simulate 10,000 page views
        System.out.println("Generating 10,000 page view events...");
        
        for (int i = 0; i < 10000; i++) {
            String page = pages[random.nextInt(pages.length)];
            String userId = "user_" + random.nextInt(5000); // 5000 unique users
            String source = sources[random.nextInt(sources.length)];
            String location = locations[random.nextInt(locations.length)];
            
            dashboard.logPageView(page, userId, source, location);
            
            // Make breaking-news more popular
            if (random.nextDouble() < 0.3) {
                dashboard.logPageView("/article/breaking-news", 
                    "user_" + random.nextInt(5000), 
                    sources[random.nextInt(sources.length)],
                    locations[random.nextInt(locations.length)]);
            }
        }
        
        System.out.println("Events generated. Processing...\n");
        
        // Wait for processing
        Thread.sleep(2000);
        
        // Display dashboard
        dashboard.getDashboard();
        
        // Get specific page analytics
        dashboard.getPageAnalytics("/article/breaking-news");
        
        // Performance benchmark
        System.out.println("\n--- Performance Benchmark ---");
        
        long startTime = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            dashboard.pageViewCounts.get("/article/breaking-news");
        }
        long endTime = System.nanoTime();
        
        double avgTime = (endTime - startTime) / 10000.0 / 1000.0; // microseconds
        System.out.println("Average lookup time (10,000 iterations): " + 
            String.format("%.3f", avgTime) + " μs");
        System.out.println("Time Complexity: O(1) ✓");
        
        // Stress test
        System.out.println("\n--- Stress Test: Processing 100,000 events ---");
        startTime = System.currentTimeMillis();
        
        for (int i = 0; i < 100000; i++) {
            String page = pages[random.nextInt(pages.length)];
            String userId = "user_" + random.nextInt(10000);
            String source = sources[random.nextInt(sources.length)];
            String location = locations[random.nextInt(locations.length)];
            
            dashboard.logPageView(page, userId, source, location);
        }
        
        // Wait for queue to process
        while (dashboard.eventQueue.size() > 0) {
            Thread.sleep(100);
        }
        
        endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        
        System.out.println("Processed 100,000 events in " + duration + "ms");
        System.out.println("Throughput: " + (100000.0 / duration * 1000) + " events/second");
        
        // Final dashboard
        dashboard.getDashboard();
        
        // Shutdown
        dashboard.shutdown();
        System.out.println("Dashboard shutdown complete.");
    }
}
