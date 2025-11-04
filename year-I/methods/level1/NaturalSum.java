import java.util.*;

public class NaturalSum {
    public static int sumCalc(int limit) {
        int sum = 0;
        for (int i = 1; i <= limit; i++) {
            sum += i;
        }
        return sum;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // Added Scanner object initialization
        System.out.print("Enter the limit: ");
        int limit = sc.nextInt();

        System.out.println("The sum of natural numbers up to " + limit + " is: " + sumCalc(limit));
        sc.close(); // Close the scanner to avoid resource leaks
    }
}