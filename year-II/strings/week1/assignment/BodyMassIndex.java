// a team of 10 members. For this create a program to find the BMI and display the height,
// weight, BMI, and status of each individual
// Hint =>
// a. Take user input for the person's weight (kg) and height (cm) and store it in the
// corresponding 2D array of 10 rows. The First Column stores the weight and the second
// column stores the height in cm
// b. Create a Method to find the BMI and status of every person given the person's height
// and weight and return the 2D String array. Use the formula BMI = weight / (height *
// height). Note unit is kg/m^2. For this convert cm to meter
// c. Create a Method that takes the 2D array of height and weight as parameters. Calls the
// user-defined method to compute the BMI and the BMI Status and stores in a 2D String
// array of height, weight, BMI, and status.
// d. Create a method to display the 2D string array in a tabular format of Person's Height,
// Weight, BMI, and the Status
// e. Finally, the main function takes user inputs, calls the user-defined methods, and displays
// the result.
import java.util.*;

public class BodyMassIndex {
    // Method to calculate BMI and status
    public static String[] calculateBMIAndStatus(double weight, double heightCm) {
        double heightM = heightCm / 100.0;
        double bmi = weight / (heightM * heightM);
        String status;
        if (bmi <= 18.4) {
            status = "Underweight";
        } else if (bmi <= 24.9) {
            status = "Normal";
        } else if (bmi <= 39.9) {
            status = "Overweight";
        } else {
            status = "Obese";
        }
        return new String[] {
            String.format("%.2f", heightCm),
            String.format("%.2f", weight),
            String.format("%.2f", bmi),
            status
        };
    }

    // Method to process all persons and build result array
    public static String[][] buildResultArray(double[][] data) {
        String[][] result = new String[data.length][4];
        for (int i = 0; i < data.length; i++) {
            result[i] = calculateBMIAndStatus(data[i][0], data[i][1]);
        }
        return result;
    }

    // Method to display the result in tabular format
    public static void displayResults(String[][] results) {
        System.out.printf("%-10s %-10s %-10s %-15s\n", "Height(cm)", "Weight(kg)", "BMI", "Status");
        System.out.println("----------------------------------------------------------");
        for (int i = 0; i < results.length; i++) {
            System.out.printf("%-10s %-10s %-10s %-15s\n", results[i][0], results[i][1], results[i][2], results[i][3]);
        }
    }

    // Main method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double[][] data = new double[10][2];
        for (int i = 0; i < 10; i++) {
            System.out.println("Enter details for Person " + (i + 1) + ":");
            System.out.print("Weight (kg): ");
            data[i][0] = sc.nextDouble();
            System.out.print("Height (cm): ");
            data[i][1] = sc.nextDouble();
        }
        String[][] results = buildResultArray(data);
        System.out.println("\nBMI Results:");
        displayResults(results);
        sc.close();
    }
}