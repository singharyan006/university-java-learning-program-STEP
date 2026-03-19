import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class RoomInventory {
    private Map<String, Integer> availableCountByType;
    private Map<String, Stack<String>> availableRoomPoolByType;
    private Set<String> allocatedRoomIds;
    private Map<String, Set<String>> allocatedByType;

    public RoomInventory() {
        availableCountByType = new HashMap<>();
        availableRoomPoolByType = new HashMap<>();
        allocatedRoomIds = new HashSet<>();
        allocatedByType = new HashMap<>();
    }

    public void addRoomType(String roomType, int count) {
        availableCountByType.put(roomType, count);
        allocatedByType.putIfAbsent(roomType, new HashSet<>());

        Stack<String> roomPool = new Stack<>();
        String normalizedType = roomType.trim().toUpperCase().replace(" ", "_");

        for (int i = count; i >= 1; i--) {
            roomPool.push(normalizedType + "-" + String.format("%03d", i));
        }

        availableRoomPoolByType.put(roomType, roomPool);
    }

    public boolean hasRoomType(String roomType) {
        return availableCountByType.containsKey(roomType);
    }

    public int getAvailableRooms(String roomType) {
        return availableCountByType.getOrDefault(roomType, 0);
    }

    public synchronized String allocateRoom(String roomType) {
        if (!hasRoomType(roomType)) {
            return null;
        }

        int currentAvailable = availableCountByType.get(roomType);
        Stack<String> pool = availableRoomPoolByType.get(roomType);

        if (currentAvailable <= 0 || pool.isEmpty()) {
            return null;
        }

        String roomId = pool.pop();
        allocatedRoomIds.add(roomId);
        allocatedByType.get(roomType).add(roomId);
        availableCountByType.put(roomType, currentAvailable - 1);

        return roomId;
    }

    public synchronized void releaseRoom(String roomType, String roomId) throws InvalidCancellationException {
        if (!hasRoomType(roomType)) {
            throw new InvalidCancellationException("Room type does not exist in inventory: " + roomType);
        }

        if (!allocatedRoomIds.contains(roomId)) {
            throw new InvalidCancellationException("Room ID is not currently allocated: " + roomId);
        }

        if (!allocatedByType.get(roomType).contains(roomId)) {
            throw new InvalidCancellationException("Room ID does not belong to room type allocation set: " + roomId);
        }

        allocatedRoomIds.remove(roomId);
        allocatedByType.get(roomType).remove(roomId);

        availableRoomPoolByType.get(roomType).push(roomId);
        availableCountByType.put(roomType, availableCountByType.get(roomType) + 1);
    }

    public void displayInventory() {
        System.out.println("\n--- Current Room Inventory ---");
        for (Map.Entry<String, Integer> entry : availableCountByType.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " available");
        }
        System.out.println("------------------------------");
    }

    public void displayRoomAllocations() {
        System.out.println("\n--- Active Room Allocations by Type ---");
        for (Map.Entry<String, Set<String>> entry : allocatedByType.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
        System.out.println("---------------------------------------");
    }
}
