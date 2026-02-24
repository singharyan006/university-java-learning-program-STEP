import java.util.*;

/**
 * Problem 7: Autocomplete System for Search Engine
 * 
 * Scenario: Build a Google-like autocomplete that suggests queries as users type,
 * based on 10 million previous search queries and their popularity.
 * 
 * Features:
 * - Store search queries with frequency counts
 * - Return top 10 suggestions for any prefix in <50ms
 * - Update frequencies based on new searches
 * - Handle typos and suggest corrections (basic)
 * - Optimize for memory (10M queries × avg 30 characters)
 * 
 * Concepts: Hash table for query frequency, string hashing,
 * performance benchmarking, space complexity optimization
 */
public class AutocompleteSystem {
    
    // Query with frequency
    static class Query implements Comparable<Query> {
        String text;
        int frequency;
        
        Query(String text, int frequency) {
            this.text = text;
            this.frequency = frequency;
        }
        
        @Override
        public int compareTo(Query other) {
            // Sort by frequency (descending), then alphabetically
            if (this.frequency != other.frequency) {
                return other.frequency - this.frequency;
            }
            return this.text.compareTo(other.text);
        }
        
        @Override
        public String toString() {
            return String.format("\"%s\" (%,d searches)", text, frequency);
        }
    }
    
    // Trie Node for efficient prefix matching
    static class TrieNode {
        Map<Character, TrieNode> children;
        boolean isEndOfQuery;
        String query;
        
        TrieNode() {
            children = new HashMap<>();
            isEndOfQuery = false;
            query = null;
        }
    }
    
    // Query frequency storage
    private HashMap<String, Integer> queryFrequency;
    
    // Trie for prefix matching
    private TrieNode root;
    
    // Cache for popular prefix results
    private HashMap<String, List<Query>> prefixCache;
    private final int MAX_CACHE_SIZE = 10000;
    
    // Statistics
    private long totalSearches;
    private long cacheHits;
    private long cacheMisses;
    
    public AutocompleteSystem() {
        this.queryFrequency = new HashMap<>();
        this.root = new TrieNode();
        this.prefixCache = new LinkedHashMap<String, List<Query>>(MAX_CACHE_SIZE, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, List<Query>> eldest) {
                return size() > MAX_CACHE_SIZE;
            }
        };
        this.totalSearches = 0;
        this.cacheHits = 0;
        this.cacheMisses = 0;
    }
    
    /**
     * Add a query to the system
     * Time Complexity: O(n) where n is query length
     */
    public void addQuery(String query, int frequency) {
        query = query.toLowerCase().trim();
        
        // Update frequency map
        queryFrequency.put(query, queryFrequency.getOrDefault(query, 0) + frequency);
        
        // Insert into Trie
        insertIntoTrie(query);
        
        // Invalidate cache for affected prefixes
        invalidateCacheForQuery(query);
    }
    
    /**
     * Insert query into Trie
     */
    private void insertIntoTrie(String query) {
        TrieNode current = root;
        
        for (char c : query.toCharArray()) {
            current.children.putIfAbsent(c, new TrieNode());
            current = current.children.get(c);
        }
        
        current.isEndOfQuery = true;
        current.query = query;
    }
    
    /**
     * Update search frequency when user performs a search
     * Time Complexity: O(n)
     */
    public void updateFrequency(String query) {
        addQuery(query, 1);
        totalSearches++;
    }
    
    /**
     * Get autocomplete suggestions for a prefix
     * Time Complexity: O(n + m log k) where n = prefix length, 
     *                  m = matching queries, k = top results
     */
    public List<Query> search(String prefix) {
        prefix = prefix.toLowerCase().trim();
        
        // Check cache first
        if (prefixCache.containsKey(prefix)) {
            cacheHits++;
            return prefixCache.get(prefix);
        }
        
        cacheMisses++;
        
        // Find all queries with this prefix
        List<Query> matches = new ArrayList<>();
        
        TrieNode prefixNode = findPrefixNode(prefix);
        if (prefixNode != null) {
            collectAllQueries(prefixNode, matches);
        }
        
        // Add frequency information
        List<Query> queriesWithFreq = new ArrayList<>();
        for (Query q : matches) {
            int freq = queryFrequency.getOrDefault(q.text, 0);
            queriesWithFreq.add(new Query(q.text, freq));
        }
        
        // Sort by frequency and get top 10
        Collections.sort(queriesWithFreq);
        List<Query> topResults = queriesWithFreq.subList(0, 
            Math.min(10, queriesWithFreq.size()));
        
        // Cache the result
        prefixCache.put(prefix, new ArrayList<>(topResults));
        
        return topResults;
    }
    
    /**
     * Find the Trie node representing the prefix
     */
    private TrieNode findPrefixNode(String prefix) {
        TrieNode current = root;
        
        for (char c : prefix.toCharArray()) {
            if (!current.children.containsKey(c)) {
                return null;
            }
            current = current.children.get(c);
        }
        
        return current;
    }
    
    /**
     * Collect all queries starting from a node
     */
    private void collectAllQueries(TrieNode node, List<Query> queries) {
        if (node.isEndOfQuery) {
            queries.add(new Query(node.query, 0));
        }
        
        for (TrieNode child : node.children.values()) {
            collectAllQueries(child, queries);
        }
    }
    
    /**
     * Invalidate cache entries affected by query update
     */
    private void invalidateCacheForQuery(String query) {
        // Simple approach: clear all cache entries with prefixes of this query
        for (int i = 1; i <= query.length(); i++) {
            String prefix = query.substring(0, i);
            prefixCache.remove(prefix);
        }
    }
    
    /**
     * Get trending queries (most searched recently)
     */
    public List<Query> getTrendingQueries(int n) {
        List<Query> allQueries = new ArrayList<>();
        
        for (Map.Entry<String, Integer> entry : queryFrequency.entrySet()) {
            allQueries.add(new Query(entry.getKey(), entry.getValue()));
        }
        
        Collections.sort(allQueries);
        
        return allQueries.subList(0, Math.min(n, allQueries.size()));
    }
    
    /**
     * Simple typo correction (edit distance of 1)
     */
    public List<String> suggestCorrections(String query) {
        List<String> suggestions = new ArrayList<>();
        
        // Check if exact match exists
        if (queryFrequency.containsKey(query)) {
            return suggestions; // No correction needed
        }
        
        // Find queries with edit distance of 1
        for (String candidate : queryFrequency.keySet()) {
            if (editDistance(query, candidate) == 1) {
                suggestions.add(candidate);
            }
            
            if (suggestions.size() >= 5) break;
        }
        
        return suggestions;
    }
    
    /**
     * Calculate edit distance between two strings (Levenshtein distance)
     */
    private int editDistance(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        
        // Optimization: if length difference > 1, distance > 1
        if (Math.abs(m - n) > 1) return Integer.MAX_VALUE;
        
        int[][] dp = new int[m + 1][n + 1];
        
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1],
                                           Math.min(dp[i - 1][j], dp[i][j - 1]));
                }
            }
        }
        
        return dp[m][n];
    }
    
    /**
     * Get system statistics
     */
    public void printStatistics() {
        System.out.println("\n=== Autocomplete System Statistics ===");
        System.out.println("Total Unique Queries: " + queryFrequency.size());
        System.out.println("Total Searches: " + totalSearches);
        System.out.println("Cache Size: " + prefixCache.size() + " / " + MAX_CACHE_SIZE);
        System.out.println("Cache Hits: " + cacheHits);
        System.out.println("Cache Misses: " + cacheMisses);
        
        if (cacheHits + cacheMisses > 0) {
            double hitRate = (cacheHits * 100.0) / (cacheHits + cacheMisses);
            System.out.println("Cache Hit Rate: " + String.format("%.1f", hitRate) + "%");
        }
        
        // Memory estimation
        long estimatedMemory = queryFrequency.size() * 50; // ~50 bytes per query
        System.out.println("Estimated Memory Usage: ~" + 
            String.format("%.2f", estimatedMemory / 1024.0 / 1024.0) + " MB");
    }
    
    /**
     * Load sample data
     */
    public void loadSampleData() {
        String[][] sampleData = {
            {"java tutorial", "1234567"},
            {"javascript", "987654"},
            {"java download", "456789"},
            {"java 21 features", "123456"},
            {"java programming", "345678"},
            {"java interview questions", "234567"},
            {"python tutorial", "567890"},
            {"python vs java", "234560"},
            {"machine learning", "876543"},
            {"artificial intelligence", "654321"},
            {"data structures", "432109"},
            {"algorithms", "321098"},
            {"web development", "210987"},
            {"react tutorial", "109876"},
            {"node js", "198765"}
        };
        
        for (String[] data : sampleData) {
            addQuery(data[0], Integer.parseInt(data[1]));
        }
    }
    
    // Main method with test cases
    public static void main(String[] args) {
        AutocompleteSystem autocomplete = new AutocompleteSystem();
        
        System.out.println("=== Autocomplete System for Search Engine ===\n");
        
        // Load sample data
        System.out.println("Loading sample search queries...");
        autocomplete.loadSampleData();
        System.out.println("Sample data loaded.\n");
        
        // Test 1: Search with prefix "jav"
        System.out.println("--- Test 1: Autocomplete for 'jav' ---");
        List<Query> results = autocomplete.search("jav");
        for (int i = 0; i < results.size(); i++) {
            System.out.println((i + 1) + ". " + results.get(i));
        }
        
        // Test 2: Search with prefix "java "
        System.out.println("\n--- Test 2: Autocomplete for 'java ' ---");
        results = autocomplete.search("java ");
        for (int i = 0; i < results.size(); i++) {
            System.out.println((i + 1) + ". " + results.get(i));
        }
        
        // Test 3: Update frequency (trending)
        System.out.println("\n--- Test 3: Updating Frequencies ---");
        System.out.println("Simulating 100 searches for 'java 21 features'...");
        for (int i = 0; i < 100; i++) {
            autocomplete.updateFrequency("java 21 features");
        }
        
        results = autocomplete.search("java ");
        System.out.println("\nUpdated results for 'java ':");
        for (int i = 0; i < results.size(); i++) {
            System.out.println((i + 1) + ". " + results.get(i));
        }
        
        // Test 4: Trending queries
        System.out.println("\n--- Test 4: Trending Queries ---");
        List<Query> trending = autocomplete.getTrendingQueries(5);
        System.out.println("Top 5 Trending Queries:");
        for (int i = 0; i < trending.size(); i++) {
            System.out.println((i + 1) + ". " + trending.get(i));
        }
        
        // Test 5: Typo correction
        System.out.println("\n--- Test 5: Typo Correction ---");
        System.out.println("Searching for 'jaav' (typo):");
        List<String> corrections = autocomplete.suggestCorrections("jaav");
        if (corrections.isEmpty()) {
            System.out.println("No exact match found.");
            System.out.println("Did you mean: " + corrections);
        }
        
        // Test 6: Performance benchmark
        System.out.println("\n--- Performance Benchmark ---");
        
        // Add more data for realistic testing
        Random random = new Random();
        System.out.println("Adding 10,000 additional queries...");
        for (int i = 0; i < 10000; i++) {
            String query = "query_" + i + "_test";
            autocomplete.addQuery(query, random.nextInt(1000));
        }
        
        // Test search performance
        long startTime = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            autocomplete.search("java");
        }
        long endTime = System.nanoTime();
        
        double avgTime = (endTime - startTime) / 1000.0 / 1000000.0; // milliseconds
        System.out.println("\nAverage search time (1000 iterations): " + 
            String.format("%.3f", avgTime) + " ms");
        System.out.println("Performance: " + (avgTime < 50 ? "< 50ms ✓" : "> 50ms"));
        
        // Test cache performance
        System.out.println("\nTesting cache performance (10,000 cached searches)...");
        startTime = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            autocomplete.search("java");
        }
        endTime = System.nanoTime();
        
        avgTime = (endTime - startTime) / 10000.0 / 1000000.0; // milliseconds
        System.out.println("Average cached search time: " + 
            String.format("%.3f", avgTime) + " ms");
        
        // Print statistics
        autocomplete.printStatistics();
    }
}
