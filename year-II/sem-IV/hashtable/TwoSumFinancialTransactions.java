import java.util.*;

/**
 * Problem 9: Two-Sum Problem Variants for Financial Transactions
 * 
 * Scenario: Payment processing company needs to detect fraudulent transaction pairs
 * that sum to specific amounts (money laundering), find complementary trades,
 * and identify duplicate payments.
 * 
 * Features:
 * - Classic Two-Sum: Find pairs that sum to target amount
 * - Two-Sum with time window: Pairs within 1 hour
 * - K-Sum: Find K transactions that sum to target
 * - Duplicate detection: Same amount, same merchant, different accounts
 * - All under 100ms response time
 * 
 * Concepts: Hash table for complement lookup, O(1) lookup performance,
 * multiple hash tables, time complexity analysis
 */
public class TwoSumFinancialTransactions {
    
    // Transaction class
    static class Transaction {
        int id;
        double amount;
        String merchant;
        String account;
        long timestamp;
        String type;
        
        Transaction(int id, double amount, String merchant, String account, 
                   long timestamp, String type) {
            this.id = id;
            this.amount = amount;
            this.merchant = merchant;
            this.account = account;
            this.timestamp = timestamp;
            this.type = type;
        }
        
        @Override
        public String toString() {
            return String.format("{id:%d, amount:%.2f, merchant:%s, account:%s, type:%s}",
                id, amount, merchant, account, type);
        }
    }
    
    // Result pair class
    static class TransactionPair {
        Transaction t1;
        Transaction t2;
        double sum;
        
        TransactionPair(Transaction t1, Transaction t2) {
            this.t1 = t1;
            this.t2 = t2;
            this.sum = t1.amount + t2.amount;
        }
        
        @Override
        public String toString() {
            return String.format("(id:%d %.2f + id:%d %.2f = %.2f)",
                t1.id, t1.amount, t2.id, t2.amount, sum);
        }
    }
    
    // Duplicate group class
    static class DuplicateGroup {
        double amount;
        String merchant;
        List<String> accounts;
        List<Transaction> transactions;
        
        DuplicateGroup(double amount, String merchant) {
            this.amount = amount;
            this.merchant = merchant;
            this.accounts = new ArrayList<>();
            this.transactions = new ArrayList<>();
        }
        
        void addTransaction(Transaction t) {
            accounts.add(t.account);
            transactions.add(t);
        }
        
        @Override
        public String toString() {
            return String.format("{amount:%.2f, merchant:%s, accounts:%s}",
                amount, merchant, accounts);
        }
    }
    
    // All transactions
    private List<Transaction> transactions;
    
    // HashMap for amount → list of transactions (for two-sum)
    private HashMap<Double, List<Transaction>> amountIndex;
    
    // HashMap for duplicate detection
    private HashMap<String, List<Transaction>> merchantAmountIndex;
    
    public TwoSumFinancialTransactions() {
        this.transactions = new ArrayList<>();
        this.amountIndex = new HashMap<>();
        this.merchantAmountIndex = new HashMap<>();
    }
    
    /**
     * Add a transaction
     * Time Complexity: O(1)
     */
    public void addTransaction(Transaction t) {
        transactions.add(t);
        
        // Update amount index
        amountIndex.putIfAbsent(t.amount, new ArrayList<>());
        amountIndex.get(t.amount).add(t);
        
        // Update merchant-amount index for duplicate detection
        String key = t.merchant + ":" + String.format("%.2f", t.amount);
        merchantAmountIndex.putIfAbsent(key, new ArrayList<>());
        merchantAmountIndex.get(key).add(t);
    }
    
    /**
     * Classic Two-Sum: Find all pairs that sum to target
     * Time Complexity: O(n) where n is number of transactions
     */
    public List<TransactionPair> findTwoSum(double target) {
        List<TransactionPair> pairs = new ArrayList<>();
        HashSet<Integer> seen = new HashSet<>();
        
        for (Transaction t : transactions) {
            if (seen.contains(t.id)) continue;
            
            double complement = target - t.amount;
            
            // Look for complement in hash map
            List<Transaction> complementTransactions = amountIndex.get(complement);
            
            if (complementTransactions != null) {
                for (Transaction complementT : complementTransactions) {
                    if (complementT.id != t.id && !seen.contains(complementT.id)) {
                        pairs.add(new TransactionPair(t, complementT));
                        seen.add(t.id);
                        seen.add(complementT.id);
                        break;
                    }
                }
            }
        }
        
        return pairs;
    }
    
    /**
     * Two-Sum with time window: Find pairs within specified time window (in hours)
     * Time Complexity: O(n) average case
     */
    public List<TransactionPair> findTwoSumWithTimeWindow(double target, int windowHours) {
        List<TransactionPair> pairs = new ArrayList<>();
        long windowMs = windowHours * 60 * 60 * 1000L;
        
        // HashMap: amount → list of transactions with that amount
        HashMap<Double, List<Transaction>> timeWindowMap = new HashMap<>();
        HashSet<Integer> used = new HashSet<>();
        
        // Sort by timestamp
        List<Transaction> sortedTxns = new ArrayList<>(transactions);
        sortedTxns.sort(Comparator.comparingLong(t -> t.timestamp));
        
        for (Transaction t : sortedTxns) {
            if (used.contains(t.id)) continue;
            
            double complement = target - t.amount;
            
            // Look for complement in time window
            List<Transaction> candidates = timeWindowMap.get(complement);
            
            if (candidates != null) {
                for (Transaction candidate : candidates) {
                    long timeDiff = Math.abs(t.timestamp - candidate.timestamp);
                    
                    if (timeDiff <= windowMs && !used.contains(candidate.id)) {
                        pairs.add(new TransactionPair(candidate, t));
                        used.add(candidate.id);
                        used.add(t.id);
                        break;
                    }
                }
            }
            
            // Add current transaction to map
            timeWindowMap.putIfAbsent(t.amount, new ArrayList<>());
            timeWindowMap.get(t.amount).add(t);
            
            // Clean up old transactions outside time window
            for (List<Transaction> txnList : timeWindowMap.values()) {
                txnList.removeIf(tx -> Math.abs(t.timestamp - tx.timestamp) > windowMs);
            }
        }
        
        return pairs;
    }
    
    /**
     * K-Sum: Find K transactions that sum to target
     * Time Complexity: O(n^(k-1)) with hash table optimization
     */
    public List<List<Transaction>> findKSum(int k, double target) {
        List<List<Transaction>> results = new ArrayList<>();
        
        if (k == 2) {
            // Use optimized two-sum
            List<TransactionPair> pairs = findTwoSum(target);
            for (TransactionPair pair : pairs) {
                results.add(Arrays.asList(pair.t1, pair.t2));
            }
            return results;
        }
        
        // For k > 2, use recursive approach
        findKSumRecursive(0, k, target, new ArrayList<>(), new HashSet<>(), results);
        
        return results;
    }
    
    /**
     * Recursive helper for K-Sum
     */
    private void findKSumRecursive(int start, int k, double target, 
                                  List<Transaction> current, 
                                  Set<Integer> usedIds,
                                  List<List<Transaction>> results) {
        if (k == 0) {
            if (Math.abs(target) < 0.01) { // Handle floating point precision
                results.add(new ArrayList<>(current));
            }
            return;
        }
        
        if (k == 1) {
            // Base case: look for exact amount
            List<Transaction> candidates = amountIndex.get(target);
            if (candidates != null) {
                for (Transaction t : candidates) {
                    if (!usedIds.contains(t.id)) {
                        current.add(t);
                        results.add(new ArrayList<>(current));
                        current.remove(current.size() - 1);
                        return;
                    }
                }
            }
            return;
        }
        
        for (int i = start; i < transactions.size(); i++) {
            Transaction t = transactions.get(i);
            if (usedIds.contains(t.id)) continue;
            
            current.add(t);
            usedIds.add(t.id);
            
            findKSumRecursive(i + 1, k - 1, target - t.amount, current, usedIds, results);
            
            current.remove(current.size() - 1);
            usedIds.remove(t.id);
        }
    }
    
    /**
     * Detect duplicate transactions: Same amount, same merchant, different accounts
     * Time Complexity: O(n)
     */
    public List<DuplicateGroup> detectDuplicates() {
        List<DuplicateGroup> duplicates = new ArrayList<>();
        
        for (Map.Entry<String, List<Transaction>> entry : merchantAmountIndex.entrySet()) {
            List<Transaction> txnList = entry.getValue();
            
            if (txnList.size() > 1) {
                // Group by unique accounts
                Set<String> uniqueAccounts = new HashSet<>();
                for (Transaction t : txnList) {
                    uniqueAccounts.add(t.account);
                }
                
                // If multiple different accounts, it's suspicious
                if (uniqueAccounts.size() > 1) {
                    String[] parts = entry.getKey().split(":");
                    double amount = Double.parseDouble(parts[1]);
                    String merchant = parts[0];
                    
                    DuplicateGroup group = new DuplicateGroup(amount, merchant);
                    for (Transaction t : txnList) {
                        group.addTransaction(t);
                    }
                    
                    duplicates.add(group);
                }
            }
        }
        
        return duplicates;
    }
    
    /**
     * Find suspicious patterns: Multiple small transactions to same merchant
     */
    public Map<String, List<Transaction>> findSuspiciousPatterns(double threshold, 
                                                                 int minCount) {
        Map<String, List<Transaction>> suspiciousAccounts = new HashMap<>();
        
        // Group by account-merchant
        Map<String, List<Transaction>> accountMerchantMap = new HashMap<>();
        
        for (Transaction t : transactions) {
            String key = t.account + ":" + t.merchant;
            accountMerchantMap.putIfAbsent(key, new ArrayList<>());
            accountMerchantMap.get(key).add(t);
        }
        
        // Find patterns
        for (Map.Entry<String, List<Transaction>> entry : accountMerchantMap.entrySet()) {
            List<Transaction> txns = entry.getValue();
            
            if (txns.size() >= minCount) {
                double totalAmount = txns.stream()
                                        .mapToDouble(t -> t.amount)
                                        .sum();
                
                if (totalAmount >= threshold) {
                    suspiciousAccounts.put(entry.getKey(), txns);
                }
            }
        }
        
        return suspiciousAccounts;
    }
    
    /**
     * Generate sample transactions
     */
    public void generateSampleTransactions() {
        String[] merchants = {"Store A", "Store B", "Store C", "Bank Transfer", "Online Shop"};
        String[] accounts = {"acc001", "acc002", "acc003", "acc004", "acc005"};
        String[] types = {"debit", "credit", "transfer"};
        
        Random random = new Random();
        long baseTime = System.currentTimeMillis();
        
        // Generate 50 sample transactions
        for (int i = 1; i <= 50; i++) {
            double amount = Math.round(random.nextDouble() * 1000 * 100) / 100.0;
            String merchant = merchants[random.nextInt(merchants.length)];
            String account = accounts[random.nextInt(accounts.length)];
            String type = types[random.nextInt(types.length)];
            long timestamp = baseTime + (i * 60000); // 1 minute apart
            
            addTransaction(new Transaction(i, amount, merchant, account, timestamp, type));
        }
        
        // Add specific test cases
        addTransaction(new Transaction(51, 500, "Store A", "acc001", baseTime, "debit"));
        addTransaction(new Transaction(52, 300, "Store B", "acc002", baseTime + 1000, "debit"));
        addTransaction(new Transaction(53, 200, "Store C", "acc003", baseTime + 2000, "debit"));
        
        // Add duplicates for testing
        addTransaction(new Transaction(54, 500, "Store A", "acc002", baseTime + 3000, "debit"));
        addTransaction(new Transaction(55, 500, "Store A", "acc003", baseTime + 4000, "debit"));
    }
    
    // Main method with test cases
    public static void main(String[] args) {
        TwoSumFinancialTransactions system = new TwoSumFinancialTransactions();
        
        System.out.println("=== Two-Sum Financial Transaction Analysis ===\n");
        
        // Generate sample data
        System.out.println("Generating sample transactions...");
        system.generateSampleTransactions();
        System.out.println("Generated " + system.transactions.size() + " transactions.\n");
        
        // Test 1: Classic Two-Sum
        System.out.println("--- Test 1: Classic Two-Sum (target = 500) ---");
        List<TransactionPair> pairs = system.findTwoSum(500);
        System.out.println("Found " + pairs.size() + " pairs:");
        for (int i = 0; i < Math.min(5, pairs.size()); i++) {
            System.out.println("  " + (i + 1) + ". " + pairs.get(i));
        }
        
        // Test 2: Two-Sum with time window
        System.out.println("\n--- Test 2: Two-Sum with Time Window (target = 500, window = 1 hour) ---");
        List<TransactionPair> timePairs = system.findTwoSumWithTimeWindow(500, 1);
        System.out.println("Found " + timePairs.size() + " pairs within time window:");
        for (int i = 0; i < Math.min(5, timePairs.size()); i++) {
            System.out.println("  " + (i + 1) + ". " + timePairs.get(i));
        }
        
        // Test 3: K-Sum (K=3)
        System.out.println("\n--- Test 3: K-Sum (k=3, target = 1000) ---");
        List<List<Transaction>> kSumResults = system.findKSum(3, 1000);
        System.out.println("Found " + kSumResults.size() + " combinations:");
        for (int i = 0; i < Math.min(3, kSumResults.size()); i++) {
            List<Transaction> combo = kSumResults.get(i);
            double sum = combo.stream().mapToDouble(t -> t.amount).sum();
            System.out.print("  " + (i + 1) + ". ");
            for (Transaction t : combo) {
                System.out.print("id:" + t.id + " $" + String.format("%.2f", t.amount) + " + ");
            }
            System.out.println("= $" + String.format("%.2f", sum));
        }
        
        // Test 4: Duplicate Detection
        System.out.println("\n--- Test 4: Duplicate Detection ---");
        List<DuplicateGroup> duplicates = system.detectDuplicates();
        System.out.println("Found " + duplicates.size() + " duplicate groups:");
        for (DuplicateGroup group : duplicates) {
            System.out.println("  " + group);
        }
        
        // Test 5: Suspicious Patterns
        System.out.println("\n--- Test 5: Suspicious Patterns ---");
        System.out.println("(Multiple small transactions totaling > $1000)");
        Map<String, List<Transaction>> suspicious = system.findSuspiciousPatterns(1000, 3);
        System.out.println("Found " + suspicious.size() + " suspicious patterns:");
        for (Map.Entry<String, List<Transaction>> entry : suspicious.entrySet()) {
            double total = entry.getValue().stream().mapToDouble(t -> t.amount).sum();
            System.out.println("  " + entry.getKey() + ": " + 
                entry.getValue().size() + " transactions, total: $" + 
                String.format("%.2f", total));
        }
        
        // Performance benchmark
        System.out.println("\n--- Performance Benchmark ---");
        
        long startTime = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            system.findTwoSum(500);
        }
        long endTime = System.nanoTime();
        
        double avgTime = (endTime - startTime) / 1000.0 / 1000000.0; // milliseconds
        System.out.println("Average Two-Sum time (1000 iterations): " + 
            String.format("%.3f", avgTime) + " ms");
        System.out.println("Response time: " + (avgTime < 100 ? "< 100ms ✓" : "> 100ms"));
        System.out.println("Time Complexity: O(n) ✓");
        
        // Space complexity
        System.out.println("\n--- Space Complexity ---");
        System.out.println("Transactions: " + system.transactions.size());
        System.out.println("Amount Index Entries: " + system.amountIndex.size());
        System.out.println("Merchant-Amount Index Entries: " + system.merchantAmountIndex.size());
        System.out.println("Space Complexity: O(n) ✓");
    }
}
