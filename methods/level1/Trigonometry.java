// Write a program to calculate various trigonometric functions using Math class given an angle in degrees
// Hint => 
// Method to calculate various trigonometric functions, Firstly convert to radians and then use Math function to find sine, cosine and tangent.
// public double[] calculateTrigonometricFunctions(double angle)

import java.util.*;

public class Trigonometry{
    public static double[] calculateTrigonometricFunctions(double angle){
        double radians = Math.toRadians(angle);
        double[] result = new double[3];
        result[0] = Math.sin(radians); // Sine
        result[1] = Math.cos(radians); // Cosine
        result[2] = Math.tan(radians); // Tangent
        return result;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the angle in degrees : ");
        double angle = sc.nextDouble();

        double[] result = calculateTrigonometricFunctions(angle);
        
        System.out.println("Sine of " + angle + " degrees is : " + result[0]);
        System.out.println("Cosine of " + angle + " degrees is : " + result[1]);
        System.out.println("Tangent of " + angle + " degrees is : " + result[2]);
    }
}