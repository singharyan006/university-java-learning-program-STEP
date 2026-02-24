import java.util.*;

/**
 * Problem 4: Plagiarism Detection System
 * 
 * Scenario: A university needs to check student submissions against a database 
 * of 100,000 previous essays to detect plagiarism efficiently.
 * 
 * Features:
 * - Break documents into n-grams (sequences of n words)
 * - Store n-grams in hash table with document references
 * - Find matching n-grams between documents
 * - Calculate similarity percentage
 * - Identify most similar documents in O(n) time
 * 
 * Concepts: String hashing, frequency counting, good hash function properties,
 * performance benchmarking
 */
public class PlagiarismDetectionSystem {
    
    // Document class
    static class Document {
        String docId;
        String content;
        Set<String> nGrams;
        int totalNGrams;
        
        Document(String docId, String content) {
            this.docId = docId;
            this.content = content;
            this.nGrams = new HashSet<>();
            this.totalNGrams = 0;
        }
        
        @Override
        public String toString() {
            return docId + " (" + totalNGrams + " n-grams)";
        }
    }
    
    // Similarity result class
    static class SimilarityResult implements Comparable<SimilarityResult> {
        String docId;
        int matchingNGrams;
        double similarityPercentage;
        
        SimilarityResult(String docId, int matchingNGrams, double similarityPercentage) {
            this.docId = docId;
            this.matchingNGrams = matchingNGrams;
            this.similarityPercentage = similarityPercentage;
        }
        
        @Override
        public int compareTo(SimilarityResult other) {
            return Double.compare(other.similarityPercentage, this.similarityPercentage);
        }
        
        String getStatus() {
            if (similarityPercentage > 60) return "PLAGIARISM DETECTED";
            if (similarityPercentage > 30) return "Highly Suspicious";
            if (similarityPercentage > 10) return "Suspicious";
            return "Acceptable";
        }
        
        @Override
        public String toString() {
            return String.format("%s: %.1f%% similarity (%d matching n-grams) - %s",
                docId, similarityPercentage, matchingNGrams, getStatus());
        }
    }
    
    // N-gram size (5-grams for better accuracy)
    private final int nGramSize;
    
    // HashMap: n-gram → Set of document IDs containing it
    private HashMap<String, Set<String>> nGramIndex;
    
    // All documents in the database
    private HashMap<String, Document> documents;
    
    // Statistics
    private long totalNGramsGenerated;
    private long totalComparisons;
    
    public PlagiarismDetectionSystem(int nGramSize) {
        this.nGramSize = nGramSize;
        this.nGramIndex = new HashMap<>();
        this.documents = new HashMap<>();
        this.totalNGramsGenerated = 0;
        this.totalComparisons = 0;
    }
    
    public PlagiarismDetectionSystem() {
        this(5); // Default 5-gram
    }
    
    /**
     * Add document to the database
     * Time Complexity: O(n) where n is number of words in document
     */
    public void addDocument(String docId, String content) {
        Document doc = new Document(docId, content);
        
        // Generate n-grams
        List<String> nGrams = generateNGrams(content);
        doc.totalNGrams = nGrams.size();
        
        // Store n-grams in document
        doc.nGrams.addAll(nGrams);
        
        // Index n-grams
        for (String nGram : nGrams) {
            nGramIndex.putIfAbsent(nGram, new HashSet<>());
            nGramIndex.get(nGram).add(docId);
        }
        
        documents.put(docId, doc);
        totalNGramsGenerated += nGrams.size();
        
        System.out.println("Added document: " + docId + " (" + nGrams.size() + " " + 
                         nGramSize + "-grams extracted)");
    }
    
    /**
     * Generate n-grams from text
     * Time Complexity: O(n) where n is number of words
     */
    private List<String> generateNGrams(String text) {
        List<String> nGrams = new ArrayList<>();
        
        // Normalize text: lowercase, remove punctuation
        String normalized = text.toLowerCase()
                               .replaceAll("[^a-z0-9\\s]", "")
                               .replaceAll("\\s+", " ")
                               .trim();
        
        String[] words = normalized.split(" ");
        
        // Generate n-grams
        for (int i = 0; i <= words.length - nGramSize; i++) {
            StringBuilder nGram = new StringBuilder();
            for (int j = 0; j < nGramSize; j++) {
                if (j > 0) nGram.append(" ");
                nGram.append(words[i + j]);
            }
            nGrams.add(nGram.toString());
        }
        
        return nGrams;
    }
    
    /**
     * Analyze a document for plagiarism
     * Time Complexity: O(n * m) where n is n-grams in doc, m is avg docs per n-gram
     */
    public List<SimilarityResult> analyzeDocument(String docId) {
        Document targetDoc = documents.get(docId);
        if (targetDoc == null) {
            System.out.println("Document not found: " + docId);
            return new ArrayList<>();
        }
        
        System.out.println("\n=== Analyzing Document: " + docId + " ===");
        System.out.println("Extracted " + targetDoc.totalNGrams + " n-grams");
        
        // Count matching n-grams with other documents
        HashMap<String, Integer> matchCounts = new HashMap<>();
        
        for (String nGram : targetDoc.nGrams) {
            Set<String> docsWithNGram = nGramIndex.get(nGram);
            if (docsWithNGram != null) {
                for (String otherDocId : docsWithNGram) {
                    if (!otherDocId.equals(docId)) {
                        matchCounts.put(otherDocId, matchCounts.getOrDefault(otherDocId, 0) + 1);
                        totalComparisons++;
                    }
                }
            }
        }
        
        // Calculate similarity percentages
        List<SimilarityResult> results = new ArrayList<>();
        
        for (Map.Entry<String, Integer> entry : matchCounts.entrySet()) {
            String otherDocId = entry.getKey();
            int matchingNGrams = entry.getValue();
            
            Document otherDoc = documents.get(otherDocId);
            
            // Jaccard similarity: intersection / union
            int union = targetDoc.totalNGrams + otherDoc.totalNGrams - matchingNGrams;
            double similarity = (matchingNGrams * 100.0) / union;
            
            // Alternative: percentage of target doc that matches
            // double similarity = (matchingNGrams * 100.0) / targetDoc.totalNGrams;
            
            results.add(new SimilarityResult(otherDocId, matchingNGrams, similarity));
        }
        
        // Sort by similarity (highest first)
        Collections.sort(results);
        
        // Print results
        System.out.println("\nSimilarity Results:");
        if (results.isEmpty()) {
            System.out.println("No similar documents found.");
        } else {
            for (int i = 0; i < Math.min(5, results.size()); i++) {
                System.out.println("  " + (i + 1) + ". " + results.get(i));
            }
        }
        
        return results;
    }
    
    /**
     * Find documents similar to given text
     */
    public List<SimilarityResult> checkText(String text) {
        String tempId = "temp_" + System.currentTimeMillis();
        addDocument(tempId, text);
        List<SimilarityResult> results = analyzeDocument(tempId);
        
        // Remove temp document
        removeDocument(tempId);
        
        return results;
    }
    
    /**
     * Remove a document from the system
     */
    private void removeDocument(String docId) {
        Document doc = documents.remove(docId);
        if (doc != null) {
            // Remove from index
            for (String nGram : doc.nGrams) {
                Set<String> docs = nGramIndex.get(nGram);
                if (docs != null) {
                    docs.remove(docId);
                    if (docs.isEmpty()) {
                        nGramIndex.remove(nGram);
                    }
                }
            }
        }
    }
    
    /**
     * Get system statistics
     */
    public void printStatistics() {
        System.out.println("\n=== Plagiarism Detection Statistics ===");
        System.out.println("Total Documents: " + documents.size());
        System.out.println("Unique N-Grams: " + nGramIndex.size());
        System.out.println("Total N-Grams Generated: " + totalNGramsGenerated);
        System.out.println("Total Comparisons Made: " + totalComparisons);
        System.out.println("N-Gram Size: " + nGramSize);
        
        // Find most common n-gram
        String mostCommon = null;
        int maxOccurrences = 0;
        
        for (Map.Entry<String, Set<String>> entry : nGramIndex.entrySet()) {
            if (entry.getValue().size() > maxOccurrences) {
                maxOccurrences = entry.getValue().size();
                mostCommon = entry.getKey();
            }
        }
        
        if (mostCommon != null) {
            System.out.println("Most Common N-Gram: \"" + mostCommon + 
                             "\" (appears in " + maxOccurrences + " documents)");
        }
    }
    
    /**
     * Performance benchmark
     */
    public void benchmarkPerformance() {
        System.out.println("\n=== Performance Benchmark ===");
        
        // Test n-gram generation
        String sampleText = "The quick brown fox jumps over the lazy dog. " +
                          "This is a sample text for performance testing. " +
                          "We are testing the plagiarism detection system.";
        
        long startTime = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            generateNGrams(sampleText);
        }
        long endTime = System.nanoTime();
        
        double avgTime = (endTime - startTime) / 1000.0 / 1000.0; // microseconds
        System.out.println("Average n-gram generation time (1000 iterations): " + 
                         String.format("%.3f", avgTime) + " μs");
        
        // Test lookup performance
        if (!documents.isEmpty()) {
            String firstDoc = documents.keySet().iterator().next();
            Document doc = documents.get(firstDoc);
            String sampleNGram = doc.nGrams.iterator().next();
            
            startTime = System.nanoTime();
            for (int i = 0; i < 10000; i++) {
                nGramIndex.get(sampleNGram);
            }
            endTime = System.nanoTime();
            
            avgTime = (endTime - startTime) / 10000.0 / 1000.0; // microseconds
            System.out.println("Average n-gram lookup time (10000 iterations): " + 
                             String.format("%.3f", avgTime) + " μs");
            System.out.println("Lookup Time Complexity: O(1) ✓");
        }
    }
    
    // Main method with test cases
    public static void main(String[] args) {
        PlagiarismDetectionSystem system = new PlagiarismDetectionSystem(5);
        
        System.out.println("=== Plagiarism Detection System ===\n");
        
        // Add sample documents
        System.out.println("--- Adding Documents to Database ---");
        
        system.addDocument("essay_001.txt", 
            "The impact of climate change on global ecosystems is profound. " +
            "Rising temperatures affect biodiversity and species distribution. " +
            "Scientists warn that immediate action is necessary to prevent catastrophic consequences.");
        
        system.addDocument("essay_002.txt",
            "Machine learning algorithms have revolutionized data analysis. " +
            "Neural networks can identify patterns in complex datasets. " +
            "Deep learning techniques continue to advance artificial intelligence.");
        
        system.addDocument("essay_003.txt",
            "The impact of climate change on global ecosystems is profound. " +
            "Temperature increases affect animal habitats and plant life. " +
            "Scientists warn that immediate action is necessary to prevent catastrophic consequences. " +
            "Governments must implement policies to reduce carbon emissions.");
        
        system.addDocument("essay_004.txt",
            "Renewable energy sources are crucial for sustainable development. " +
            "Solar and wind power offer clean alternatives to fossil fuels. " +
            "Investment in green technology will benefit future generations.");
        
        system.addDocument("essay_005.txt",
            "Machine learning algorithms have revolutionized data analysis techniques. " +
            "Neural networks can identify hidden patterns in complex datasets. " +
            "Deep learning techniques continue to advance artificial intelligence applications.");
        
        System.out.println();
        
        // Analyze documents for plagiarism
        System.out.println("\n--- Plagiarism Analysis ---");
        
        // Check essay_003 (should match essay_001)
        system.analyzeDocument("essay_003.txt");
        
        // Check essay_005 (should match essay_002)
        system.analyzeDocument("essay_005.txt");
        
        // Check new text
        System.out.println("\n--- Checking New Submission ---");
        String newSubmission = 
            "The impact of climate change on global ecosystems is profound and alarming. " +
            "Rising temperatures affect biodiversity and species distribution worldwide. " +
            "Scientists warn that immediate action is necessary to prevent catastrophic consequences.";
        
        List<SimilarityResult> results = system.checkText(newSubmission);
        System.out.println("\nNew submission analysis complete.");
        
        // Print statistics
        system.printStatistics();
        
        // Benchmark performance
        system.benchmarkPerformance();
    }
}
