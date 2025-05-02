import java.util.Scanner;

public class BMICalculator {
    public static double[][] getPersonDetails(int totalPersons) {
        Scanner scanner = new Scanner(System.in);
        double[][] details = new double[totalPersons][2];
        for (int i = 0; i < totalPersons; i++) {
            System.out.println("Enter details for Person " + (i + 1) + ":");
            while (true) {
                System.out.print("Weight (kg): ");
                if (scanner.hasNextDouble()) {
                    details[i][0] = scanner.nextDouble();
                    break;
                } else {
                    System.out.println("Invalid input! Please enter a numeric value.");
                    scanner.next();
                }
            }
            while (true) {
                System.out.print("Height (cm): ");
                if (scanner.hasNextDouble()) {
                    details[i][1] = scanner.nextDouble();
                    break;
                } else {
                    System.out.println("Invalid input! Please enter a numeric value.");
                    scanner.next();
                }
            }
        }
        return details;
    }

    public static String[] getBMIStatus(double weight, double heightCm) {
        double heightM = heightCm / 100.0;
        double bmi = weight / (heightM * heightM);
        String status;
        if (bmi < 18.5) status = "Underweight";
        else if (bmi < 25) status = "Normal";
        else if (bmi < 30) status = "Overweight";
        else status = "Obese";
        return new String[]{String.format("%.2f", bmi), status};
    }

    public static String[][] calculateBMI(double[][] details) {
        int n = details.length;
        String[][] result = new String[n][4];
        for (int i = 0; i < n; i++) {
            double weight = details[i][0];
            double height = details[i][1];
            String[] bmiStatus = getBMIStatus(weight, height);
            result[i][0] = String.valueOf(height);
            result[i][1] = String.valueOf(weight);
            result[i][2] = bmiStatus[0];
            result[i][3] = bmiStatus[1];
        }
        return result;
    }

    public static void displayBMIReport(String[][] report) {
        System.out.printf("%-10s %-10s %-10s %-15s\n", "Height(cm)", "Weight(kg)", "BMI", "Status");
        for (String[] row : report) {
            System.out.printf("%-10s %-10s %-10s %-15s\n", row[0], row[1], row[2], row[3]);
        }
    }

    public static void main(String[] args) {
        double[][] personDetails = getPersonDetails(10);
        String[][] bmiReport = calculateBMI(personDetails);
        displayBMIReport(bmiReport);
    }
}

