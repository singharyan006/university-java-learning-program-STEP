import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class RoomInventory implements Serializable {
    private static final long serialVersionUID = 1L;

    private Map<String, Integer> availableByType;

    public RoomInventory() {
        availableByType = new HashMap<>();
    }

    public void addRoomType(String roomType, int count) {
        availableByType.put(roomType, count);
    }

    public void updateCount(String roomType, int count) {
        availableByType.put(roomType, count);
    }

    public int getAvailable(String roomType) {
        return availableByType.getOrDefault(roomType, 0);
    }

    public Map<String, Integer> getSnapshot() {
        return Collections.unmodifiableMap(new HashMap<>(availableByType));
    }

    public void displayInventory() {
        System.out.println("\n--- Current Room Inventory ---");
        for (Map.Entry<String, Integer> entry : availableByType.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " available");
        }
        System.out.println("------------------------------");
    }
}
