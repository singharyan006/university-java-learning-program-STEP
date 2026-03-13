public class UseCase3InventorySetup {
    public static void main(String[] args) {
        System.out.println("======================================");
        System.out.println("      Welcome to Book My Stay");
        System.out.println("    Hotel Booking System v3.1");
        System.out.println("======================================");

        // Initialize domain objects
        Room singleRoom = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suiteRoom = new SuiteRoom();

        // Initialize centralized inventory component
        RoomInventory inventory = new RoomInventory();

        // Register room types in inventory
        inventory.addRoomType("Single Room", 10);
        inventory.addRoomType("Double Room", 15);
        inventory.addRoomType("Suite Room", 5);

        // Display individual room details and look up availability from centralized inventory
        System.out.println("\n--- Initializing System State ---\n");

        singleRoom.displayDetails();
        System.out.println("Available: " + inventory.getAvailableRooms("Single Room"));
        System.out.println("--------------------------------------");

        doubleRoom.displayDetails();
        System.out.println("Available: " + inventory.getAvailableRooms("Double Room"));
        System.out.println("--------------------------------------");

        suiteRoom.displayDetails();
        System.out.println("Available: " + inventory.getAvailableRooms("Suite Room"));
        System.out.println("--------------------------------------");

        // Display the centralized inventory
        inventory.displayInventory();

        // Simulate some controlled updates to room availability
        System.out.println("\n[System] Simulating updates to availability...");
        inventory.updateRoomAvailability("Single Room", 8);  // e.g., 2 booked
        inventory.updateRoomAvailability("Double Room", 14); // e.g., 1 booked

        // Verify the updated inventory
        inventory.displayInventory();
    }
}
