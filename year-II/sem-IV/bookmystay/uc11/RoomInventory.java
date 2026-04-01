import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RoomInventory {
    private Map<String, Integer> availableByType;
    private Map<String, Integer> typeCounters;
    private Set<String> allocatedRoomIds;
    private Map<String, Set<String>> allocatedByType;

    public RoomInventory() {
        availableByType = new HashMap<>();
        typeCounters = new HashMap<>();
        allocatedRoomIds = new HashSet<>();
        allocatedByType = new HashMap<>();
    }

    public synchronized void addRoomType(String roomType, int count) {
        availableByType.put(roomType, count);
        typeCounters.putIfAbsent(roomType, 0);
        allocatedByType.putIfAbsent(roomType, new HashSet<>());
    }

    public synchronized String allocateRoom(String roomType) {
        if (!availableByType.containsKey(roomType)) {
            return null;
        }

        int available = availableByType.get(roomType);
        if (available <= 0) {
            return null;
        }

        String roomId = generateUniqueRoomId(roomType);
        allocatedRoomIds.add(roomId);
        allocatedByType.get(roomType).add(roomId);
        availableByType.put(roomType, available - 1);

        return roomId;
    }

    private String generateUniqueRoomId(String roomType) {
        String normalizedType = roomType.trim().toUpperCase().replace(" ", "_");
        int next = typeCounters.getOrDefault(roomType, 0) + 1;
        String candidate = normalizedType + "-" + String.format("%03d", next);

        while (allocatedRoomIds.contains(candidate)) {
            next++;
            candidate = normalizedType + "-" + String.format("%03d", next);
        }

        typeCounters.put(roomType, next);
        return candidate;
    }

    public synchronized void displayInventory() {
        System.out.println("\n--- Current Room Inventory ---");
        for (Map.Entry<String, Integer> entry : availableByType.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " available");
        }
        System.out.println("------------------------------");
    }

    public synchronized void displayAllocations() {
        System.out.println("\n--- Allocated Room IDs by Type ---");
        for (Map.Entry<String, Set<String>> entry : allocatedByType.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
        System.out.println("----------------------------------");
    }
}
