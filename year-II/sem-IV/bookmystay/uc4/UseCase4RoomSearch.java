import java.util.ArrayList;
import java.util.List;

public class UseCase4RoomSearch {
    public static void main(String[] args) {
        System.out.println("======================================");
        System.out.println("      Welcome to Book My Stay");
        System.out.println("    Hotel Booking System v4.1");
        System.out.println("======================================");

        // Initialize domain objects
        Room singleRoom = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suiteRoom = new SuiteRoom();

        List<Room> allRooms = new ArrayList<>();
        allRooms.add(singleRoom);
        allRooms.add(doubleRoom);
        allRooms.add(suiteRoom);

        // Initialize centralized inventory component
        RoomInventory inventory = new RoomInventory();

        // Register room types in inventory (Notice Suite is 0)
        inventory.addRoomType("Single Room", 10);
        inventory.addRoomType("Double Room", 15);
        inventory.addRoomType("Suite Room", 0);

        // Initialize the Search Service with strictly read-only access intent
        SearchService searchService = new SearchService(inventory, allRooms);

        System.out.println("\n[Guest] Initiating room search...");
        
        // Searching for available rooms
        searchService.displayAvailableRooms();

        // Simulate some state changes elsewhere in the system
        System.out.println("\n[System] Simulating inventory update (2 Suites become available)...");
        inventory.updateRoomAvailability("Suite Room", 2);

        System.out.println("\n[Guest] Initiating room search again...");
        // Search again to reflect new state
        searchService.displayAvailableRooms();
    }
}
