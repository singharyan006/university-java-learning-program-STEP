import java.util.Scanner;

public class BMICalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Step 1: Take user input for weight and height
        System.out.print("Enter weight (kg): ");
        double weight = sc.nextDouble();
        
        System.out.print("Enter height (cm): ");
        double heightCm = sc.nextDouble();

        // Step 2: Convert height from cm to meters
        double heightM = heightCm / 100;

        // Step 3: Calculate BMI
        double bmi = weight / (heightM * heightM);

        // Step 4: Determine weight status
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

        // Step 5: Display BMI and weight status
        System.out.printf("Your BMI: %.2f\n", bmi);
        System.out.println("Weight Status: " + status);

        sc.close(); // Close scanner
    }
}
