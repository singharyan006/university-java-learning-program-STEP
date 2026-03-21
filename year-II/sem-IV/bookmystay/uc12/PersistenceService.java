import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class PersistenceService {
    private final String stateFilePath;

    public PersistenceService(String stateFilePath) {
        this.stateFilePath = stateFilePath;
    }

    public void save(SystemState state) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(stateFilePath))) {
            outputStream.writeObject(state);
            System.out.println("[Persistence] State saved successfully to: " + stateFilePath);
        } catch (IOException ex) {
            System.out.println("[Persistence Error] Failed to save state: " + ex.getMessage());
        }
    }

    public SystemState loadOrDefault() {
        File file = new File(stateFilePath);

        if (!file.exists()) {
            System.out.println("[Recovery] State file not found. Starting with a safe default state.");
            return createDefaultState();
        }

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(stateFilePath))) {
            Object loadedObject = inputStream.readObject();
            if (!(loadedObject instanceof SystemState)) {
                System.out.println("[Recovery Error] Invalid state format. Falling back to safe default state.");
                return createDefaultState();
            }

            System.out.println("[Recovery] State restored successfully from: " + stateFilePath);
            return (SystemState) loadedObject;
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("[Recovery Error] Failed to load persisted state. Reason: " + ex.getMessage());
            System.out.println("[Recovery] Continuing with a safe default state.");
            return createDefaultState();
        }
    }

    private SystemState createDefaultState() {
        RoomInventory inventory = new RoomInventory();
        BookingHistory history = new BookingHistory();
        return new SystemState(inventory, history);
    }
}
