// Extend or Create a UnitConvertor utility class similar to the one shown in the notes to do the following.  Please define static methods for all the UnitConvertor class methods. E.g. 
// public static double convertKmToMiles(double km) => 
// Method To convert kilometers to miles and return the value. Use the following code  double km2miles = 0.621371;
// Method to convert miles to kilometers and return the value. Use the following code  double miles2km = 1.60934;
// Method to convert meters to feet and return the value. Use the following code to convert  double meters2feet = 3.28084;
// Method to convert feet to meters and return the value. Use the following code to convert  double feet2meters = 0.3048;
import java.util.*;

public class UnitConvertor {
    // Method to convert kilometers to miles
    public static double convertKmToMiles(double km) {
        double km2miles = 0.621371;
        return km * km2miles;
    }

    // Method to convert miles to kilometers
    public static double convertMilesToKm(double miles) {
        double miles2km = 1.60934;
        return miles * miles2km;
    }

    // Method to convert meters to feet
    public static double convertMetersToFeet(double meters) {
        double meters2feet = 3.28084;
        return meters * meters2feet;
    }

    // Method to convert feet to meters
    public static double convertFeetToMeters(double feet) {
        double feet2meters = 0.3048;
        return feet * feet2meters;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nUnit Converter Menu:");
            System.out.println("1. Convert Kilometers to Miles");
            System.out.println("2. Convert Miles to Kilometers");
            System.out.println("3. Convert Meters to Feet");
            System.out.println("4. Convert Feet to Meters");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter distance in kilometers: ");
                    double km = scanner.nextDouble();
                    System.out.println(km + " kilometers is equal to " + convertKmToMiles(km) + " miles.");
                    break;

                case 2:
                    System.out.print("Enter distance in miles: ");
                    double miles = scanner.nextDouble();
                    System.out.println(miles + " miles is equal to " + convertMilesToKm(miles) + " kilometers.");
                    break;

                case 3:
                    System.out.print("Enter distance in meters: ");
                    double meters = scanner.nextDouble();
                    System.out.println(meters + " meters is equal to " + convertMetersToFeet(meters) + " feet.");
                    break;

                case 4:
                    System.out.print("Enter distance in feet: ");
                    double feet = scanner.nextDouble();
                    System.out.println(feet + " feet is equal to " + convertFeetToMeters(feet) + " meters.");
                    break;

                case 5:
                    System.out.println("Exiting the program. Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}