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

    public void addRoomType(String roomType, int count) throws InventoryStateException {
        if (roomType == null || roomType.trim().isEmpty()) {
            throw new InventoryStateException("Room type cannot be null or blank.");
        }
        if (count < 0) {
            throw new InventoryStateException("Inventory count cannot be negative for room type: " + roomType);
        }

        inventory.put(roomType, count);
        roomTypeAllocations.putIfAbsent(roomType, new HashSet<>());
        roomTypeCounters.putIfAbsent(roomType, 0);
    }

    public boolean hasRoomType(String roomType) {
        return inventory.containsKey(roomType);
    }

    public int getAvailableRooms(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public synchronized String allocateRoom(String roomType) throws InvalidBookingException, InventoryStateException {
        if (!inventory.containsKey(roomType)) {
            throw new InvalidBookingException("Requested room type is invalid: " + roomType);
        }

        int availableCount = inventory.get(roomType);
        if (availableCount <= 0) {
            throw new InvalidBookingException("No available rooms for room type: " + roomType);
        }

        String roomId = generateUniqueRoomId(roomType);

        allocatedRoomIds.add(roomId);
        roomTypeAllocations.computeIfAbsent(roomType, key -> new HashSet<>()).add(roomId);

        int updatedCount = availableCount - 1;
        if (updatedCount < 0) {
            throw new InventoryStateException("Inventory would become negative for room type: " + roomType);
        }
        inventory.put(roomType, updatedCount);

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
