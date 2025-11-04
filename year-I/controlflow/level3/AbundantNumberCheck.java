import java.util.Scanner;

public class AbundantNumberCheck {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Step 1: Get user input
        System.out.print("Enter a number: ");
        int number = sc.nextInt();

        // Step 2: Initialize sum variable
        int sum = 0;

        // Step 3: Loop through divisors from 1 to number-1
        for (int i = 1; i < number; i++) {
            if (number % i == 0) { // Check if i is a divisor
                sum += i; // Add divisor to sum
            }
        }

        // Step 4: Check if sum of divisors is greater than the number
        if (sum > number) {
            System.out.println(number + " is an Abundant Number.");
        } else {
            System.out.println(number + " is NOT an Abundant Number.");
        }

        sc.close(); // Close scanner
    }
}
