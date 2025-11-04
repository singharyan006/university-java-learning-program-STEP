import java.util.*;

public class AtheleteRunning {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Taking Inputs from the user
        System.out.print("Enter first side of triangle (m): ");
        double side1 = sc.nextDouble();

        System.out.print("Enter second side of triangle (m): ");
        double side2 = sc.nextDouble();

        System.out.print("Enter third side of triangle (m): ");
        double side3 = sc.nextDouble();

        double perimeter = side1 + side2 + side3; // Calculating the perimeter of the triangle
        int rounds = (int) Math.ceil(5000 / perimeter);  // 5 km = 5000 meters and also doing the type conversion 
        // Math.ceil is used to round off the outcome 

        System.out.println("The total number of rounds the athlete will run is " + rounds + " to complete 5 km.");
    }
}
