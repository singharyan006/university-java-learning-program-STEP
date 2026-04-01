import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RoomInventory {
    private Map<String, Integer> inventory;
    private Set<String> allocatedRoomIds;
    private Map<String, Set<String>> roomTypeAllocations;
    private Map<String, Integer> roomTypeCounters;

    public RoomInventory() {
        inventory = new HashMap<>();
        allocatedRoomIds = new HashSet<>();
        roomTypeAllocations = new HashMap<>();
        roomTypeCounters = new HashMap<>();
    }

    public void addRoomType(String roomType, int count) {
        inventory.put(roomType, count);
        roomTypeAllocations.putIfAbsent(roomType, new HashSet<>());
        roomTypeCounters.putIfAbsent(roomType, 0);
    }

    public int getAvailableRooms(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public synchronized String allocateRoom(String roomType) {
        int availableCount = inventory.getOrDefault(roomType, 0);
        if (availableCount <= 0) {
            return null;
        }

        String roomId = generateUniqueRoomId(roomType);

        allocatedRoomIds.add(roomId);
        roomTypeAllocations.computeIfAbsent(roomType, key -> new HashSet<>()).add(roomId);
        inventory.put(roomType, availableCount - 1);

        return roomId;
    }

    private String generateUniqueRoomId(String roomType) {
        String normalizedType = roomType.trim().toUpperCase().replace(" ", "_");
        int nextNumber = roomTypeCounters.getOrDefault(roomType, 0) + 1;
        String candidateId = normalizedType + "-" + String.format("%03d", nextNumber);

        while (allocatedRoomIds.contains(candidateId)) {
            nextNumber++;
            candidateId = normalizedType + "-" + String.format("%03d", nextNumber);
        }

        roomTypeCounters.put(roomType, nextNumber);
        return candidateId;
    }

    public void displayInventory() {
        System.out.println("\n--- Current Room Inventory ---");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " available");
        }
        System.out.println("------------------------------");
    }

    public void displayRoomAllocations() {
        System.out.println("\n--- Room Allocations by Type ---");
        for (Map.Entry<String, Set<String>> entry : roomTypeAllocations.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
        System.out.println("--------------------------------");
    }
}
