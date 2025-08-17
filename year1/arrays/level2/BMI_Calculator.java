import java.util.*;

public class BMI_Calculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter the number of persons: ");
        int n = sc.nextInt();
        
        double[] weights = new double[n];
        double[] heights = new double[n];
        double[] bmiValues = new double[n];
        String[] weightStatus = new String[n];
        
        for (int i = 0; i < n; i++) {
            System.out.println("Enter details for person " + (i + 1) + ":");
            System.out.print("Weight (kg): ");
            weights[i] = sc.nextDouble();
            System.out.print("Height (m): ");
            heights[i] = sc.nextDouble();
            
            bmiValues[i] = weights[i] / (heights[i] * heights[i]);
            
            if (bmiValues[i] < 18.5) weightStatus[i] = "Underweight";
            else if (bmiValues[i] < 24.9) weightStatus[i] = "Normal weight";
            else if (bmiValues[i] < 29.9) weightStatus[i] = "Overweight";
            else weightStatus[i] = "Obese";
        }
        
        System.out.println("\nHeight (m) | Weight (kg) | BMI | Status");
        System.out.println("------------------------------------");
        for (int i = 0; i < n; i++) {
            System.out.printf("%.2f       | %.2f       | %.2f | %s\n", heights[i], weights[i], bmiValues[i], weightStatus[i]);
        }
        
        sc.close();
    }
}