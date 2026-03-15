public class UseCase5BookingRequestQueue {
    public static void main(String[] args) {
        System.out.println("======================================");
        System.out.println("      Welcome to Book My Stay");
        System.out.println("    Hotel Booking System v5.1");
        System.out.println("======================================");

        // Initialize queue system (Inventory and Domain logic are decoupled for this stage)
        BookingRequestQueue queue = new BookingRequestQueue();

        System.out.println("\n[System] Receiving incoming booking requests during peak hours...");
        
        // Simulating simultaneous guest requests preserving FIFO order
        queue.addRequest(new Reservation("Alice", "Suite Room"));
        queue.addRequest(new Reservation("Bob", "Single Room"));
        queue.addRequest(new Reservation("Charlie", "Double Room"));
        queue.addRequest(new Reservation("Diana", "Suite Room"));
        queue.addRequest(new Reservation("Eve", "Single Room"));

        // Display queue state showing fair arrival order
        queue.displayQueueStatus();
        
        System.out.println("\n[Note] Requests have been properly queued. No inventory changes or room allocations have occurred yet.");
    }
}