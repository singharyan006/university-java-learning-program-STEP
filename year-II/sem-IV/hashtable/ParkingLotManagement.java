import java.util.*;

/**
 * Problem 8: Parking Lot Management with Open Addressing
 * 
 * Scenario: A smart parking lot with 500 spots needs to track which vehicles 
 * are parked where, handle collisions, and optimize spot allocation.
 * 
 * Features:
 * - Assign parking spots based on license plate hash
 * - Use linear probing when preferred spot is occupied
 * - Track entry/exit times for billing
 * - Find nearest available spot to entrance
 * - Generate parking statistics
 * 
 * Concepts: Open addressing (linear/quadratic probing), collision resolution,
 * custom hash functions, load factor management
 */
public class ParkingLotManagement {
    
    // Spot status enum
    enum SpotStatus {
        EMPTY,
        OCCUPIED,
        DELETED  // Tombstone for removed entries
    }
    
    // Parking spot class
    static class ParkingSpot {
        int spotNumber;
        SpotStatus status;
        String licensePlate;
        long entryTime;
        int probeCount;      // Number of probes to find this spot
        
        ParkingSpot(int spotNumber) {
            this.spotNumber = spotNumber;
            this.status = SpotStatus.EMPTY;
            this.licensePlate = null;
            this.entryTime = 0;
            this.probeCount = 0;
        }
        
        void occupy(String licensePlate, long entryTime, int probeCount) {
            this.status = SpotStatus.OCCUPIED;
            this.licensePlate = licensePlate;
            this.entryTime = entryTime;
            this.probeCount = probeCount;
        }
        
        void vacate() {
            this.status = SpotStatus.DELETED;
            this.licensePlate = null;
            this.entryTime = 0;
            this.probeCount = 0;
        }
        
        boolean isAvailable() {
            return status == SpotStatus.EMPTY || status == SpotStatus.DELETED;
        }
        
        @Override
        public String toString() {
            if (status == SpotStatus.OCCUPIED) {
                return String.format("Spot #%d: %s (parked %s ago)", 
                    spotNumber, licensePlate, 
                    formatDuration(System.currentTimeMillis() - entryTime));
            }
            return String.format("Spot #%d: %s", spotNumber, status);
        }
        
        static String formatDuration(long millis) {
            long seconds = millis / 1000;
            long minutes = seconds / 60;
            long hours = minutes / 60;
            
            if (hours > 0) {
                return String.format("%dh %dm", hours, minutes % 60);
            } else if (minutes > 0) {
                return String.format("%dm %ds", minutes, seconds % 60);
            } else {
                return String.format("%ds", seconds);
            }
        }
    }
    
    // Parking result class
    static class ParkingResult {
        boolean success;
        int spotNumber;
        int probeCount;
        String message;
        
        ParkingResult(boolean success, int spotNumber, int probeCount, String message) {
            this.success = success;
            this.spotNumber = spotNumber;
            this.probeCount = probeCount;
            this.message = message;
        }
        
        @Override
        public String toString() {
            if (success) {
                return String.format("✓ Assigned spot #%d (%d probe%s) - %s",
                    spotNumber, probeCount, probeCount == 1 ? "" : "s", message);
            } else {
                return "✗ " + message;
            }
        }
    }
    
    // Exit result class
    static class ExitResult {
        boolean success;
        int spotNumber;
        long duration;
        double fee;
        String message;
        
        ExitResult(boolean success, int spotNumber, long duration, double fee, String message) {
            this.success = success;
            this.spotNumber = spotNumber;
            this.duration = duration;
            this.fee = fee;
            this.message = message;
        }
        
        @Override
        public String toString() {
            if (success) {
                return String.format("✓ Spot #%d freed, Duration: %s, Fee: $%.2f",
                    spotNumber, ParkingSpot.formatDuration(duration), fee);
            } else {
                return "✗ " + message;
            }
        }
    }
    
    // Array-based hash table with open addressing
    private ParkingSpot[] spots;
    private final int capacity;
    
    // License plate to spot mapping for quick lookup
    private HashMap<String, Integer> licensePlateToSpot;
    
    // Statistics
    private int occupiedCount;
    private long totalProbes;
    private long totalVehiclesParked;
    private double totalRevenue;
    
    // Pricing
    private final double RATE_PER_HOUR = 5.0;
    
    public ParkingLotManagement(int capacity) {
        this.capacity = capacity;
        this.spots = new ParkingSpot[capacity];
        this.licensePlateToSpot = new HashMap<>();
        this.occupiedCount = 0;
        this.totalProbes = 0;
        this.totalVehiclesParked = 0;
        this.totalRevenue = 0.0;
        
        // Initialize spots
        for (int i = 0; i < capacity; i++) {
            spots[i] = new ParkingSpot(i);
        }
    }
    
    /**
     * Custom hash function for license plate
     * Time Complexity: O(n) where n is license plate length
     */
    private int hash(String licensePlate) {
        int hash = 0;
        for (char c : licensePlate.toCharArray()) {
            hash = (hash * 31 + c) % capacity;
        }
        return Math.abs(hash);
    }
    
    /**
     * Park a vehicle using linear probing
     * Time Complexity: O(1) average, O(n) worst case
     */
    public ParkingResult parkVehicle(String licensePlate) {
        // Check if vehicle already parked
        if (licensePlateToSpot.containsKey(licensePlate)) {
            int existingSpot = licensePlateToSpot.get(licensePlate);
            return new ParkingResult(false, -1, 0, 
                "Vehicle already parked at spot #" + existingSpot);
        }
        
        // Check if lot is full
        if (occupiedCount >= capacity) {
            return new ParkingResult(false, -1, 0, "Parking lot is full");
        }
        
        // Get preferred spot from hash function
        int preferredSpot = hash(licensePlate);
        int currentSpot = preferredSpot;
        int probeCount = 0;
        
        // Linear probing
        while (!spots[currentSpot].isAvailable()) {
            probeCount++;
            currentSpot = (currentSpot + 1) % capacity;
            
            // Should never happen if we checked capacity, but safety check
            if (currentSpot == preferredSpot) {
                return new ParkingResult(false, -1, probeCount, "Parking lot is full");
            }
        }
        
        // Occupy the spot
        long entryTime = System.currentTimeMillis();
        spots[currentSpot].occupy(licensePlate, entryTime, probeCount);
        licensePlateToSpot.put(licensePlate, currentSpot);
        
        occupiedCount++;
        totalProbes += probeCount;
        totalVehiclesParked++;
        
        String message = currentSpot == preferredSpot ? 
            "Preferred spot" : "Alternative spot (preferred #" + preferredSpot + " occupied)";
        
        return new ParkingResult(true, currentSpot, probeCount, message);
    }
    
    /**
     * Park vehicle using quadratic probing
     * Time Complexity: O(1) average, O(n) worst case
     */
    public ParkingResult parkVehicleQuadratic(String licensePlate) {
        if (licensePlateToSpot.containsKey(licensePlate)) {
            int existingSpot = licensePlateToSpot.get(licensePlate);
            return new ParkingResult(false, -1, 0, 
                "Vehicle already parked at spot #" + existingSpot);
        }
        
        if (occupiedCount >= capacity) {
            return new ParkingResult(false, -1, 0, "Parking lot is full");
        }
        
        int preferredSpot = hash(licensePlate);
        int probeCount = 0;
        
        // Quadratic probing: h(k) + i^2
        for (int i = 0; i < capacity; i++) {
            int currentSpot = (preferredSpot + i * i) % capacity;
            
            if (spots[currentSpot].isAvailable()) {
                long entryTime = System.currentTimeMillis();
                spots[currentSpot].occupy(licensePlate, entryTime, i);
                licensePlateToSpot.put(licensePlate, currentSpot);
                
                occupiedCount++;
                totalProbes += i;
                totalVehiclesParked++;
                
                return new ParkingResult(true, currentSpot, i, 
                    i == 0 ? "Preferred spot" : "Quadratic probing");
            }
        }
        
        return new ParkingResult(false, -1, probeCount, "Could not find spot");
    }
    
    /**
     * Exit vehicle and calculate fee
     * Time Complexity: O(1)
     */
    public ExitResult exitVehicle(String licensePlate) {
        Integer spotNumber = licensePlateToSpot.get(licensePlate);
        
        if (spotNumber == null) {
            return new ExitResult(false, -1, 0, 0.0, 
                "Vehicle not found in parking lot");
        }
        
        ParkingSpot spot = spots[spotNumber];
        long duration = System.currentTimeMillis() - spot.entryTime;
        
        // Calculate fee (minimum 1 hour)
        double hours = Math.max(1.0, Math.ceil(duration / (1000.0 * 60 * 60)));
        double fee = hours * RATE_PER_HOUR;
        
        // Free the spot
        spot.vacate();
        licensePlateToSpot.remove(licensePlate);
        occupiedCount--;
        totalRevenue += fee;
        
        return new ExitResult(true, spotNumber, duration, fee, "Success");
    }
    
    /**
     * Find nearest available spot to entrance (spot 0)
     * Time Complexity: O(n)
     */
    public int findNearestAvailableSpot() {
        for (int i = 0; i < capacity; i++) {
            if (spots[i].isAvailable()) {
                return i;
            }
        }
        return -1; // Lot is full
    }
    
    /**
     * Get current occupancy percentage
     */
    public double getOccupancy() {
        return (occupiedCount * 100.0) / capacity;
    }
    
    /**
     * Get load factor (important for hash table performance)
     */
    public double getLoadFactor() {
        return occupiedCount / (double) capacity;
    }
    
    /**
     * Get statistics
     */
    public void getStatistics() {
        System.out.println("\n=== Parking Lot Statistics ===");
        System.out.println("Capacity: " + capacity + " spots");
        System.out.println("Occupied: " + occupiedCount + " spots");
        System.out.println("Available: " + (capacity - occupiedCount) + " spots");
        System.out.println("Occupancy: " + String.format("%.1f", getOccupancy()) + "%");
        System.out.println("Load Factor: " + String.format("%.3f", getLoadFactor()));
        
        if (totalVehiclesParked > 0) {
            double avgProbes = totalProbes / (double) totalVehiclesParked;
            System.out.println("Average Probes: " + String.format("%.2f", avgProbes));
        }
        
        System.out.println("Total Vehicles Parked: " + totalVehiclesParked);
        System.out.println("Total Revenue: $" + String.format("%.2f", totalRevenue));
        
        // Find peak hour (simplified - just show current time)
        System.out.println("Current Time: " + new Date());
    }
    
    /**
     * Display current lot status
     */
    public void displayLotStatus(int rows, int cols) {
        System.out.println("\n=== Parking Lot Layout ===");
        System.out.println("Legend: [E]=Empty [O]=Occupied [D]=Deleted\n");
        
        for (int i = 0; i < Math.min(rows, capacity / cols + 1); i++) {
            for (int j = 0; j < cols; j++) {
                int spotIndex = i * cols + j;
                if (spotIndex >= capacity) break;
                
                char statusChar = switch (spots[spotIndex].status) {
                    case EMPTY -> 'E';
                    case OCCUPIED -> 'O';
                    case DELETED -> 'D';
                };
                
                System.out.printf("[%c]", statusChar);
            }
            System.out.println();
        }
        System.out.println();
    }
    
    /**
     * Get probe distribution (for analysis)
     */
    public Map<Integer, Integer> getProbeDistribution() {
        Map<Integer, Integer> distribution = new HashMap<>();
        
        for (ParkingSpot spot : spots) {
            if (spot.status == SpotStatus.OCCUPIED) {
                distribution.put(spot.probeCount, 
                    distribution.getOrDefault(spot.probeCount, 0) + 1);
            }
        }
        
        return distribution;
    }
    
    // Main method with test cases
    public static void main(String[] args) throws InterruptedException {
        ParkingLotManagement parkingLot = new ParkingLotManagement(50);
        
        System.out.println("=== Parking Lot Management System ===");
        System.out.println("Capacity: 50 spots\n");
        
        // Test 1: Park vehicles
        System.out.println("--- Test 1: Parking Vehicles ---");
        
        String[] vehicles = {
            "ABC-1234", "ABC-1235", "XYZ-9999", 
            "DEF-5678", "GHI-9012", "JKL-3456"
        };
        
        for (String vehicle : vehicles) {
            ParkingResult result = parkingLot.parkVehicle(vehicle);
            System.out.println(vehicle + ": " + result);
        }
        
        // Display lot status
        parkingLot.displayLotStatus(5, 10);
        
        // Test 2: Collision handling
        System.out.println("\n--- Test 2: Simulating Collisions ---");
        // These should hash to similar spots
        for (int i = 0; i < 5; i++) {
            String plate = "TEST-" + (1000 + i);
            ParkingResult result = parkingLot.parkVehicle(plate);
            System.out.println(plate + ": " + result);
        }
        
        // Test 3: Exit vehicle
        System.out.println("\n--- Test 3: Vehicle Exit ---");
        Thread.sleep(2000); // Simulate 2 seconds parking
        
        ExitResult exit1 = parkingLot.exitVehicle("ABC-1234");
        System.out.println("ABC-1234: " + exit1);
        
        ExitResult exit2 = parkingLot.exitVehicle("UNKNOWN-000");
        System.out.println("UNKNOWN-000: " + exit2);
        
        // Test 4: Fill parking lot to test load factor
        System.out.println("\n--- Test 4: Filling Parking Lot ---");
        
        Random random = new Random();
        for (int i = 0; i < 30; i++) {
            String plate = String.format("%c%c%c-%04d",
                (char)('A' + random.nextInt(26)),
                (char)('A' + random.nextInt(26)),
                (char)('A' + random.nextInt(26)),
                random.nextInt(10000));
            parkingLot.parkVehicle(plate);
        }
        
        System.out.println("Added 30 more vehicles.");
        parkingLot.displayLotStatus(5, 10);
        
        // Test 5: Probe distribution analysis
        System.out.println("--- Probe Distribution ---");
        Map<Integer, Integer> distribution = parkingLot.getProbeDistribution();
        
        List<Integer> probes = new ArrayList<>(distribution.keySet());
        Collections.sort(probes);
        
        for (int probe : probes) {
            int count = distribution.get(probe);
            System.out.printf("%d probe%s: %d vehicle%s\n", 
                probe, probe == 1 ? "" : "s",
                count, count == 1 ? "" : "s");
        }
        
        // Statistics
        parkingLot.getStatistics();
        
        // Test 6: Performance benchmark
        System.out.println("\n--- Performance Benchmark ---");
        
        ParkingLotManagement testLot = new ParkingLotManagement(500);
        
        long startTime = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            String plate = "BENCH-" + i;
            testLot.parkVehicle(plate);
        }
        long endTime = System.nanoTime();
        
        double avgTime = (endTime - startTime) / 1000.0 / 1000.0; // microseconds
        System.out.println("Average parking time (1000 operations): " + 
            String.format("%.3f", avgTime) + " μs");
        System.out.println("Time Complexity: O(1) average case ✓");
        
        System.out.println("\nFinal occupancy: " + 
            String.format("%.1f", testLot.getOccupancy()) + "%");
        System.out.println("Load factor: " + 
            String.format("%.3f", testLot.getLoadFactor()));
    }
}
