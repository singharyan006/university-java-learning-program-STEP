import java.util.ArrayList;
import java.util.List;

public class UseCase10TrainConsistManagementApp {
    public static void main(String[] args) {
        System.out.println("=== Train Consist Management App ===");
        System.out.println("Welcome to the Train Consist Management System");
        System.out.println("UC10: Count Total Seats in Train");

        List<Bogie> bogies = new ArrayList<>();
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 56));
        bogies.add(new Bogie("First Class", 48));
        bogies.add(new Bogie("Premium Sleeper", 80));

        int totalSeats = calculateTotalSeats(bogies);

        System.out.println("Total seating capacity of the train: " + totalSeats);
        System.out.println("Original bogie list remains unchanged: " + bogies);
        System.out.println("Program continues.");
    }

    public static int calculateTotalSeats(List<Bogie> bogies) {
        if (bogies == null) {
            return 0;
        }

        return bogies.stream()
                .map(b -> b.capacity)
                .reduce(0, Integer::sum);
    }
}
