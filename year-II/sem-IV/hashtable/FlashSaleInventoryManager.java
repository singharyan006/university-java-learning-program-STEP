import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

/**
 * Problem 2: E-commerce Flash Sale Inventory Manager
 * 
 * Scenario: During a flash sale, 50,000 customers simultaneously try to purchase 
 * limited stock items (only 100 units available). Prevent overselling while 
 * maintaining high performance.
 * 
 * Features:
 * - Real-time stock tracking in O(1) time
 * - Thread-safe concurrent request handling
 * - Waiting list when stock runs out
 * - Instant stock availability checks
 * 
 * Concepts: Hash table for instant lookup, collision resolution, 
 * load factor management, thread safety
 */
public class FlashSaleInventoryManager {
    
    // Product stock tracking - thread-safe
    private ConcurrentHashMap<String, AtomicInteger> productStock;
    
    // Waiting list for out-of-stock items (FIFO order)
    private ConcurrentHashMap<String, LinkedList<Integer>> waitingLists;
    
    // Purchase history
    private ConcurrentHashMap<String, List<PurchaseRecord>> purchaseHistory;
    
    // Lock objects for synchronized operations
    private ConcurrentHashMap<String, Object> productLocks;
    
    // Statistics
    private AtomicInteger successfulPurchases;
    private AtomicInteger failedPurchases;
    
    // Inner class to track purchase records
    static class PurchaseRecord {
        int userId;
        long timestamp;
        int quantity;
        
        PurchaseRecord(int userId, int quantity) {
            this.userId = userId;
            this.timestamp = System.currentTimeMillis();
            this.quantity = quantity;
        }
        
        @Override
        public String toString() {
            return "User " + userId + " bought " + quantity + " unit(s) at " + 
                   new Date(timestamp);
        }
    }
    
    public FlashSaleInventoryManager() {
        this.productStock = new ConcurrentHashMap<>();
        this.waitingLists = new ConcurrentHashMap<>();
        this.purchaseHistory = new ConcurrentHashMap<>();
        this.productLocks = new ConcurrentHashMap<>();
        this.successfulPurchases = new AtomicInteger(0);
        this.failedPurchases = new AtomicInteger(0);
    }
    
    /**
     * Add a product with initial stock
     * Time Complexity: O(1)
     */
    public void addProduct(String productId, int initialStock) {
        productStock.put(productId, new AtomicInteger(initialStock));
        waitingLists.put(productId, new LinkedList<>());
        purchaseHistory.put(productId, new CopyOnWriteArrayList<>());
        productLocks.put(productId, new Object());
        System.out.println("Added product: " + productId + " with stock: " + initialStock);
    }
    
    /**
     * Check stock availability
     * Time Complexity: O(1)
     */
    public int checkStock(String productId) {
        AtomicInteger stock = productStock.get(productId);
        if (stock == null) {
            return -1; // Product not found
        }
        return stock.get();
    }
    
    /**
     * Purchase an item (thread-safe)
     * Time Complexity: O(1) for stock check and update
     */
    public PurchaseResult purchaseItem(String productId, int userId) {
        return purchaseItem(productId, userId, 1);
    }
    
    /**
     * Purchase multiple items
     * Time Complexity: O(1)
     */
    public PurchaseResult purchaseItem(String productId, int userId, int quantity) {
        // Check if product exists
        if (!productStock.containsKey(productId)) {
            failedPurchases.incrementAndGet();
            return new PurchaseResult(false, "Product not found", 0, -1);
        }
        
        // Synchronize on product-specific lock to prevent race conditions
        synchronized (productLocks.get(productId)) {
            AtomicInteger stock = productStock.get(productId);
            int currentStock = stock.get();
            
            // Check if enough stock available
            if (currentStock >= quantity) {
                // Atomic decrement
                int newStock = stock.addAndGet(-quantity);
                
                // Record purchase
                PurchaseRecord record = new PurchaseRecord(userId, quantity);
                purchaseHistory.get(productId).add(record);
                successfulPurchases.incrementAndGet();
                
                return new PurchaseResult(true, "Success", newStock, -1);
            } else {
                // Add to waiting list
                LinkedList<Integer> waitingList = waitingLists.get(productId);
                waitingList.add(userId);
                int position = waitingList.size();
                failedPurchases.incrementAndGet();
                
                return new PurchaseResult(false, "Out of stock - Added to waiting list", 
                                         currentStock, position);
            }
        }
    }
    
    /**
     * Restock a product
     * Time Complexity: O(n) where n is number of waiting customers
     */
    public void restockProduct(String productId, int quantity) {
        if (!productStock.containsKey(productId)) {
            System.out.println("Product not found: " + productId);
            return;
        }
        
        synchronized (productLocks.get(productId)) {
            AtomicInteger stock = productStock.get(productId);
            int newStock = stock.addAndGet(quantity);
            
            System.out.println("Restocked " + productId + ": +" + quantity + 
                             " units. New stock: " + newStock);
            
            // Process waiting list
            LinkedList<Integer> waitingList = waitingLists.get(productId);
            int processed = 0;
            
            while (!waitingList.isEmpty() && stock.get() > 0) {
                int userId = waitingList.removeFirst();
                stock.decrementAndGet();
                
                PurchaseRecord record = new PurchaseRecord(userId, 1);
                purchaseHistory.get(productId).add(record);
                processed++;
                
                System.out.println("  Fulfilled waiting order for User " + userId);
            }
            
            if (processed > 0) {
                System.out.println("Processed " + processed + " waiting orders");
            }
        }
    }
    
    /**
     * Get waiting list position
     */
    public int getWaitingListPosition(String productId, int userId) {
        LinkedList<Integer> waitingList = waitingLists.get(productId);
        if (waitingList == null) return -1;
        
        int position = 0;
        for (int id : waitingList) {
            position++;
            if (id == userId) return position;
        }
        return -1; // Not in waiting list
    }
    
    /**
     * Get statistics
     */
    public void printStatistics() {
        System.out.println("\n=== Flash Sale Statistics ===");
        System.out.println("Successful Purchases: " + successfulPurchases.get());
        System.out.println("Failed Purchases: " + failedPurchases.get());
        System.out.println("\nProduct Stock Levels:");
        
        for (Map.Entry<String, AtomicInteger> entry : productStock.entrySet()) {
            String productId = entry.getKey();
            int stock = entry.getValue().get();
            int waiting = waitingLists.get(productId).size();
            int sold = purchaseHistory.get(productId).size();
            
            System.out.println("  " + productId + ":");
            System.out.println("    Stock: " + stock + " units");
            System.out.println("    Sold: " + sold + " units");
            System.out.println("    Waiting: " + waiting + " customers");
        }
    }
    
    /**
     * Simulate concurrent purchases
     */
    public void simulateConcurrentPurchases(String productId, int numCustomers) {
        System.out.println("\n=== Simulating " + numCustomers + 
                         " concurrent purchase attempts ===");
        
        ExecutorService executor = Executors.newFixedThreadPool(50);
        CountDownLatch latch = new CountDownLatch(numCustomers);
        
        long startTime = System.currentTimeMillis();
        
        for (int i = 0; i < numCustomers; i++) {
            final int userId = 10000 + i;
            executor.submit(() -> {
                try {
                    purchaseItem(productId, userId);
                } finally {
                    latch.countDown();
                }
            });
        }
        
        try {
            latch.await(); // Wait for all purchases to complete
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        long endTime = System.currentTimeMillis();
        executor.shutdown();
        
        System.out.println("Completed " + numCustomers + " purchase attempts in " + 
                         (endTime - startTime) + "ms");
    }
    
    // Result class
    static class PurchaseResult {
        boolean success;
        String message;
        int remainingStock;
        int waitingListPosition;
        
        PurchaseResult(boolean success, String message, int remainingStock, 
                      int waitingListPosition) {
            this.success = success;
            this.message = message;
            this.remainingStock = remainingStock;
            this.waitingListPosition = waitingListPosition;
        }
        
        @Override
        public String toString() {
            if (success) {
                return message + ", " + remainingStock + " units remaining";
            } else {
                return message + (waitingListPosition > 0 ? 
                       ", position #" + waitingListPosition : "");
            }
        }
    }
    
    // Main method with test cases
    public static void main(String[] args) {
        FlashSaleInventoryManager manager = new FlashSaleInventoryManager();
        
        System.out.println("=== E-commerce Flash Sale Inventory Manager ===\n");
        
        // Add products
        manager.addProduct("IPHONE15_256GB", 100);
        manager.addProduct("MACBOOK_PRO", 50);
        manager.addProduct("AIRPODS_PRO", 200);
        System.out.println();
        
        // Check stock
        System.out.println("--- Stock Check ---");
        System.out.println("checkStock('IPHONE15_256GB'): " + 
            manager.checkStock("IPHONE15_256GB") + " units available");
        System.out.println();
        
        // Manual purchases
        System.out.println("--- Manual Purchases ---");
        PurchaseResult result1 = manager.purchaseItem("IPHONE15_256GB", 12345);
        System.out.println("purchaseItem('IPHONE15_256GB', userId=12345): " + result1);
        
        PurchaseResult result2 = manager.purchaseItem("IPHONE15_256GB", 67890);
        System.out.println("purchaseItem('IPHONE15_256GB', userId=67890): " + result2);
        System.out.println();
        
        // Simulate flash sale with concurrent purchases
        manager.simulateConcurrentPurchases("IPHONE15_256GB", 150);
        
        System.out.println("\nStock after flash sale: " + 
            manager.checkStock("IPHONE15_256GB") + " units");
        
        // Show waiting list
        LinkedList<Integer> waitingList = manager.waitingLists.get("IPHONE15_256GB");
        System.out.println("Customers on waiting list: " + waitingList.size());
        
        // Restock and process waiting list
        System.out.println("\n--- Restocking ---");
        manager.restockProduct("IPHONE15_256GB", 30);
        
        // Print final statistics
        manager.printStatistics();
        
        // Performance test
        System.out.println("\n--- Performance Test ---");
        long startTime = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            manager.checkStock("IPHONE15_256GB");
        }
        long endTime = System.nanoTime();
        double avgTime = (endTime - startTime) / 10000.0 / 1000.0; // microseconds
        System.out.println("Average time for 10,000 stock checks: " + 
            String.format("%.3f", avgTime) + " μs per check");
        System.out.println("Time Complexity: O(1) ✓");
    }
}
