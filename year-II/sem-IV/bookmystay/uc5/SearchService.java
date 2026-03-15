import java.util.ArrayList;
import java.util.List;

public class SearchService {
    private RoomInventory inventory;
    private List<Room> rooms;

    public SearchService(RoomInventory inventory, List<Room> rooms) {
        this.inventory = inventory;
        this.rooms = rooms;
    }

    public void displayAvailableRooms() {
        System.out.println("\n--- Available Rooms for Booking ---");
        boolean anyRoomAvailable = false;

        for (Room room : rooms) {
            String roomType = room.roomType;
            int availableCount = inventory.getAvailableRooms(roomType);

            if (availableCount > 0) {
                anyRoomAvailable = true;
                System.out.println("\n[" + roomType + " - " + availableCount + " Available]");
                room.displayDetails();
            }
        }

        if (!anyRoomAvailable) {
            System.out.println("No rooms are currently available.");
        }
        System.out.println("-----------------------------------");
    }
}
