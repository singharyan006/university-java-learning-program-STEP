import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class UseCase7TrainConsistManagementApp {
    public static void main(String[] args) {
        System.out.println("=== Train Consist Management App ===");
        System.out.println("Welcome to the Train Consist Management System");
        System.out.println("UC7: Sort Bogies by Capacity");

        List<Bogie> passengerBogies = new ArrayList<>();
        passengerBogies.add(new Bogie("Sleeper", 72));
        passengerBogies.add(new Bogie("AC Chair", 56));
        passengerBogies.add(new Bogie("First Class", 48));

        passengerBogies.sort(Comparator.comparingInt(Bogie::getCapacity).reversed());

        System.out.println("Passenger bogies sorted by capacity:");
        for (Bogie bogie : passengerBogies) {
            System.out.println(bogie.getName() + " -> " + bogie.getCapacity() + " seats");
        }

        System.out.println("Program continues.");
    }
}
