import java.util.Scanner;

public class ArmstrongNumberCheck {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Step 1: Get user input
        System.out.print("Enter a number: ");
        int number = sc.nextInt();

        // Step 2: Initialize variables
        int sum = 0;
        int originalNumber = number;

        // Step 3: Loop through each digit
        while (originalNumber != 0) {
            int remainder = originalNumber % 10; // Extract last digit
            sum += Math.pow(remainder, 3); // Add cube of the digit to sum
            originalNumber /= 10; // Remove last digit
        }

        // Step 4: Compare sum with the original number
        if (sum == number) {
            System.out.println(number + " is an Armstrong Number.");
        } else {
            System.out.println(number + " is NOT an Armstrong Number.");
        }

        sc.close(); // Close scanner
    }
}
