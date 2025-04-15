import java.util.*;

public class AreaOfTriangle {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Conversion constants
        double inchToCm = 2.54;
        double cmToFeet = 0.032;
        double cmToInches = 0.393;

        // Taking inputs
        System.out.print("Enter the base in cm: ");
        double baseCm = sc.nextDouble();

        System.out.print("Enter the height in cm: ");
        double heightCm = sc.nextDouble();

        // Calculating area
        double areaCm = 0.5 * baseCm * heightCm;
        double areaIn = 0.5 * (baseCm / inchToCm) * (heightCm / inchToCm);

        // Converting height to feet and inches
        double heightFeet = heightCm * cmToFeet;
        double heightInches = heightCm * cmToInches;

        // Printing results
        System.out.printf("The area of the triangle is %.2f sq cm and %.2f sq in.\n", areaCm, areaIn);
        System.out.printf("Your height in cm is %.2f, in feet is %.2f, and in inches is %.2f.\n", heightCm, heightFeet, heightInches);

    }
}
