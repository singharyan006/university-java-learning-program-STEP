import java.util.Scanner;

public class HarshadNumberCheck {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Step 1: Get user input
        System.out.print("Enter a number: ");
        int number = sc.nextInt();

        // Step 2: Initialize sum variable
        int sum = 0;
        int originalNumber = number; // Store original number for reference

        // Step 3: Loop to extract each digit and calculate sum
        while (originalNumber != 0) {
            int digit = originalNumber % 10; // Get last digit
            sum += digit; // Add digit to sum
            originalNumber /= 10; // Remove last digit
        }

        // Step 4: Check if number is divisible by sum of digits
        if (number % sum == 0) {
            System.out.println(number + " is a Harshad Number.");
        } else {
            System.out.println(number + " is NOT a Harshad Number.");
        }

        sc.close(); // Close scanner
    }
}

