import java.util.HashSet;
import java.util.Set;

public class UseCase3TrainConsistManagementApp {
    public static void main(String[] args) {
        System.out.println("=== Train Consist Management App ===");
        System.out.println("Welcome to the Train Consist Management System");
        System.out.println("UC3: Track Unique Bogie IDs");

        Set<String> bogieIds = new HashSet<>();

        bogieIds.add("BG101");
        bogieIds.add("BG102");
        bogieIds.add("BG101");
        bogieIds.add("BG103");
        bogieIds.add("BG102");

        System.out.println("Unique bogie IDs: " + bogieIds);
        System.out.println("Total unique bogies: " + bogieIds.size());
        System.out.println("Program continues.");
    }
}
