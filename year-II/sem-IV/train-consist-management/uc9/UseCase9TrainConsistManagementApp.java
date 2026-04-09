import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UseCase9TrainConsistManagementApp {
    public static void main(String[] args) {
        System.out.println("=== Train Consist Management App ===");
        System.out.println("Welcome to the Train Consist Management System");
        System.out.println("UC9: Group Bogies by Type");

        List<Bogie> bogies = new ArrayList<>();
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 56));
        bogies.add(new Bogie("First Class", 48));
        bogies.add(new Bogie("Sleeper", 70));
        bogies.add(new Bogie("AC Chair", 60));

        Map<String, List<Bogie>> groupedBogies = groupBogiesByType(bogies);

        System.out.println("Grouped bogies:");
        for (Map.Entry<String, List<Bogie>> entry : groupedBogies.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        System.out.println("Original bogie list remains unchanged: " + bogies);
        System.out.println("Program continues.");
    }

    public static Map<String, List<Bogie>> groupBogiesByType(List<Bogie> bogies) {
        if (bogies == null) {
            return Map.of();
        }

        return bogies.stream()
                .collect(Collectors.groupingBy(Bogie::getName));
    }
}
