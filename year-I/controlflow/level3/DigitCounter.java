import java.util.Scanner;

public class DigitCounter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Step 1: Get user input
        System.out.print("Enter a number: ");
        int number = sc.nextInt();

        // Step 2: Initialize count variable
        int count = 0;
        int originalNumber = number; // Store the original number for reference

        // Step 3: Count digits using a loop
        if (number == 0) {
            count = 1; // Special case: If number is 0, it has one digit
        } else {
            while (number != 0) {
                number /= 10; // Remove the last digit
                count++; // Increment count
            }
        }

        // Step 4: Display the result
        System.out.println("The number " + originalNumber + " has " + count + " digits.");

        sc.close(); // Close scanner
    }
}
