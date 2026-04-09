import java.util.HashMap;
import java.util.Map;

public class UseCase6TrainConsistManagementApp {
    public static void main(String[] args) {
        System.out.println("=== Train Consist Management App ===");
        System.out.println("Welcome to the Train Consist Management System");
        System.out.println("UC6: Map Bogie to Capacity");

        Map<String, Integer> bogieCapacities = new HashMap<>();

        bogieCapacities.put("Sleeper", 72);
        bogieCapacities.put("AC Chair", 64);
        bogieCapacities.put("First Class", 48);

        System.out.println("Bogie capacity details:");
        for (Map.Entry<String, Integer> entry : bogieCapacities.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue() + " seats");
        }

        System.out.println("Program continues.");
    }
}
