// Extend or Create a UnitConvertor utility class similar to the one shown in the notes to do the following.  Please define static methods for all the UnitConvertor class methods. E.g. 
// public static double convertYardsToFeet(double yards) => 
// Method to convert yards to feet and return the value. Use following code to convert  double yards2feet = 3;
// Method to convert feet to yards and return the value. Use following code to convert  double feet2yards = 0.333333;
// Method to convert meters to inches and return the value. Use following code to convert  double meters2inches = 39.3701;
// Method to convert inches to meters and return the value. Use following code to convert  double inches2meters = 0.0254;
// Method to convert inches to centimeters and return the value. Use the following code  double inches2cm = 2.54;

import java.util.*;

public class UnitConvertor2 {
    // Method to convert yards to feet
    public static double convertYardsToFeet(double yards) {
        double yards2feet = 3;
        return yards * yards2feet;
    }

    // Method to convert feet to yards
    public static double convertFeetToYards(double feet) {
        double feet2yards = 0.333333;
        return feet * feet2yards;
    }

    // Method to convert meters to inches
    public static double convertMetersToInches(double meters) {
        double meters2inches = 39.3701;
        return meters * meters2inches;
    }

    // Method to convert inches to meters
    public static double convertInchesToMeters(double inches) {
        double inches2meters = 0.0254;
        return inches * inches2meters;
    }

    // Method to convert inches to centimeters
    public static double convertInchesToCm(double inches) {
        double inches2cm = 2.54;
        return inches * inches2cm;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nUnit Converter Menu:");
            System.out.println("1. Convert Yards to Feet");
            System.out.println("2. Convert Feet to Yards");
            System.out.println("3. Convert Meters to Inches");
            System.out.println("4. Convert Inches to Meters");
            System.out.println("5. Convert Inches to Centimeters");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter distance in yards: ");
                    double yards = scanner.nextDouble();
                    System.out.println(yards + " yards is equal to " + convertYardsToFeet(yards) + " feet.");
                    break;

                case 2:
                    System.out.print("Enter distance in feet: ");
                    double feet = scanner.nextDouble();
                    System.out.println(feet + " feet is equal to " + convertFeetToYards(feet) + " yards.");
                    break;

                case 3:
                    System.out.print("Enter distance in meters: ");
                    double meters = scanner.nextDouble();
                    System.out.println(meters + " meters is equal to " + convertMetersToInches(meters) + " inches.");
                    break;

                case 4:
                    System.out.print("Enter distance in inches: ");
                    double inches = scanner.nextDouble();
                    System.out.println(inches + " inches is equal to " + convertInchesToMeters(inches) + " meters.");
                    break;

                case 5:
                    System.out.print("Enter distance in inches: ");
                    double inches2 = scanner.nextDouble();
                    System.out.println(inches2 + " inches is equal to " + convertInchesToCm(inches2) + " centimeters.");
                    break;

                case 6:
                    System.out.println("Exiting the program. Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
