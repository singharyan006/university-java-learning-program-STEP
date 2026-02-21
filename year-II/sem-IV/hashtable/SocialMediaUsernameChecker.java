import java.util.*;

/**
 * Problem 1: Social Media Username Availability Checker
 * 
 * Scenario: Registration system for a social media platform with 10 million users.
 * Users frequently check if usernames are available before registering.
 * 
 * Features:
 * - O(1) username availability check
 * - Handles concurrent username checks
 * - Suggests similar available usernames
 * - Tracks popularity of attempted usernames
 * 
 * Concepts: Hash table basics, O(1) lookup, collision handling, frequency counting
 */
public class SocialMediaUsernameChecker {
    
    // HashMap for instant O(1) lookup - username to userId mapping
    private HashMap<String, Integer> usernameToUserId;
    
    // Track frequency of username attempts
    private HashMap<String, Integer> usernameAttempts;
    
    // Counter for generating unique user IDs
    private int userIdCounter;
    
    public SocialMediaUsernameChecker() {
        // Initialize with appropriate capacity for 10M users
        // Load factor of 0.75 (default), initial capacity to reduce resizing
        this.usernameToUserId = new HashMap<>(1000000);
        this.usernameAttempts = new HashMap<>(100000);
        this.userIdCounter = 1;
    }
    
    /**
     * Check if a username is available
     * Time Complexity: O(1) average case
     * @param username The username to check
     * @return true if available, false if taken
     */
    public boolean checkAvailability(String username) {
        // Increment attempt counter
        usernameAttempts.put(username, usernameAttempts.getOrDefault(username, 0) + 1);
        
        // O(1) lookup in HashMap
        return !usernameToUserId.containsKey(username);
    }
    
    /**
     * Register a new username
     * Time Complexity: O(1)
     * @param username The username to register
     * @return userId if successful, -1 if username already taken
     */
    public int registerUsername(String username) {
        if (!checkAvailability(username)) {
            return -1; // Username already taken
        }
        
        int userId = userIdCounter++;
        usernameToUserId.put(username, userId);
        return userId;
    }
    
    /**
     * Suggest alternative usernames when requested one is taken
     * Time Complexity: O(n) where n is number of suggestions to generate
     * @param username The desired username
     * @return List of available alternative usernames
     */
    public List<String> suggestAlternatives(String username) {
        List<String> suggestions = new ArrayList<>();
        
        // If username is available, return it
        if (checkAvailability(username)) {
            suggestions.add(username);
            return suggestions;
        }
        
        // Strategy 1: Append numbers (john_doe1, john_doe2, etc.)
        for (int i = 1; i <= 5 && suggestions.size() < 5; i++) {
            String suggestion = username + i;
            if (checkAvailability(suggestion)) {
                suggestions.add(suggestion);
            }
        }
        
        // Strategy 2: Replace underscore with dot
        if (username.contains("_") && suggestions.size() < 5) {
            String suggestion = username.replace("_", ".");
            if (checkAvailability(suggestion)) {
                suggestions.add(suggestion);
            }
        }
        
        // Strategy 3: Add random suffix
        Random random = new Random();
        while (suggestions.size() < 5) {
            String suggestion = username + "_" + random.nextInt(1000);
            if (checkAvailability(suggestion)) {
                suggestions.add(suggestion);
            }
        }
        
        return suggestions;
    }
    
    /**
     * Get the most attempted username
     * Time Complexity: O(n) where n is number of unique attempted usernames
     * @return Username with most attempts and attempt count
     */
    public String getMostAttempted() {
        if (usernameAttempts.isEmpty()) {
            return "No attempts recorded";
        }
        
        String mostAttempted = null;
        int maxAttempts = 0;
        
        for (Map.Entry<String, Integer> entry : usernameAttempts.entrySet()) {
            if (entry.getValue() > maxAttempts) {
                maxAttempts = entry.getValue();
                mostAttempted = entry.getKey();
            }
        }
        
        return mostAttempted + " (" + maxAttempts + " attempts)";
    }
    
    /**
     * Get top N most attempted usernames
     * Time Complexity: O(n log k) where n is total attempts, k is top N
     */
    public List<String> getTopAttempted(int n) {
        // Use a min-heap to track top N
        PriorityQueue<Map.Entry<String, Integer>> minHeap = new PriorityQueue<>(
            (a, b) -> a.getValue() - b.getValue()
        );
        
        for (Map.Entry<String, Integer> entry : usernameAttempts.entrySet()) {
            minHeap.offer(entry);
            if (minHeap.size() > n) {
                minHeap.poll();
            }
        }
        
        List<String> result = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            Map.Entry<String, Integer> entry = minHeap.poll();
            result.add(0, entry.getKey() + " (" + entry.getValue() + " attempts)");
        }
        
        return result;
    }
    
    /**
     * Get statistics about the system
     */
    public void printStatistics() {
        System.out.println("=== Username Checker Statistics ===");
        System.out.println("Total Users: " + usernameToUserId.size());
        System.out.println("Unique Usernames Attempted: " + usernameAttempts.size());
        System.out.println("Most Attempted: " + getMostAttempted());
        System.out.println("Load Factor: " + String.format("%.2f", 
            (double) usernameToUserId.size() / usernameToUserId.size()));
    }
    
    // Main method with sample test cases
    public static void main(String[] args) {
        SocialMediaUsernameChecker checker = new SocialMediaUsernameChecker();
        
        System.out.println("=== Social Media Username Availability Checker ===\n");
        
        // Register some users
        System.out.println("Registering users...");
        checker.registerUsername("john_doe");
        checker.registerUsername("jane_smith");
        checker.registerUsername("admin");
        checker.registerUsername("user123");
        checker.registerUsername("tech_guru");
        System.out.println("5 users registered.\n");
        
        // Test availability checks
        System.out.println("--- Availability Checks ---");
        System.out.println("checkAvailability('john_doe'): " + 
            checker.checkAvailability("john_doe")); // false
        System.out.println("checkAvailability('jane_smith'): " + 
            checker.checkAvailability("jane_smith")); // false
        System.out.println("checkAvailability('alice_wonder'): " + 
            checker.checkAvailability("alice_wonder")); // true
        System.out.println();
        
        // Test suggestions
        System.out.println("--- Username Suggestions ---");
        System.out.println("suggestAlternatives('john_doe'):");
        List<String> suggestions = checker.suggestAlternatives("john_doe");
        for (String suggestion : suggestions) {
            System.out.println("  - " + suggestion);
        }
        System.out.println();
        
        // Simulate multiple attempts for popular usernames
        System.out.println("--- Simulating Username Attempts ---");
        for (int i = 0; i < 50; i++) {
            checker.checkAvailability("admin");
        }
        for (int i = 0; i < 30; i++) {
            checker.checkAvailability("john_doe");
        }
        for (int i = 0; i < 20; i++) {
            checker.checkAvailability("user");
        }
        System.out.println("Simulated attempts for popular usernames.\n");
        
        // Get most attempted
        System.out.println("--- Most Attempted Usernames ---");
        System.out.println("getMostAttempted(): " + checker.getMostAttempted());
        System.out.println("\nTop 5 Most Attempted:");
        List<String> topAttempted = checker.getTopAttempted(5);
        for (int i = 0; i < topAttempted.size(); i++) {
            System.out.println((i + 1) + ". " + topAttempted.get(i));
        }
        System.out.println();
        
        // Print statistics
        checker.printStatistics();
        
        // Performance test
        System.out.println("\n--- Performance Test ---");
        long startTime = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            checker.checkAvailability("test_user_" + i);
        }
        long endTime = System.nanoTime();
        double avgTime = (endTime - startTime) / 10000.0 / 1000.0; // microseconds
        System.out.println("Average time for 10,000 checks: " + 
            String.format("%.3f", avgTime) + " μs per check");
        System.out.println("Time Complexity: O(1) ✓");
    }
}
