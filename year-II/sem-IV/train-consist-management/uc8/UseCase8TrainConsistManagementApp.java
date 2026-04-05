import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UseCase8TrainConsistManagementApp {
    public static void main(String[] args) {
        System.out.println("=== Train Consist Management App ===");
        System.out.println("Welcome to the Train Consist Management System");
        System.out.println("UC8: Filter Passenger Bogies Using Streams");

        List<Bogie> bogies = new ArrayList<>();
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 56));
        bogies.add(new Bogie("First Class", 48));
        bogies.add(new Bogie("Premium Sleeper", 80));

        List<Bogie> filteredBogies = filterPassengerBogiesByCapacity(bogies, 60);

        System.out.println("Filtered bogies with capacity greater than 60:");
        for (Bogie bogie : filteredBogies) {
            System.out.println(bogie);
        }

        System.out.println("Original bogie list remains unchanged: " + bogies);
        System.out.println("Program continues.");
    }

    public static List<Bogie> filterPassengerBogiesByCapacity(List<Bogie> bogies, int threshold) {
        if (bogies == null) {
            return new ArrayList<>();
        }

        return bogies.stream()
                .filter(b -> b.capacity > threshold)
                .collect(Collectors.toList());
    }
}
