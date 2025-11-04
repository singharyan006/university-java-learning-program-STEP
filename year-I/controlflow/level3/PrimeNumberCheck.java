import java.util.Scanner;

public class PrimeNumberCheck {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input number from user
        System.out.print("Enter a number: ");
        int num = sc.nextInt();

        // Prime number check
        boolean isPrime = true;

        if (num <= 1) {
            isPrime = false; // Numbers less than or equal to 1 are not prime
        } else {
            for (int i = 2; i <= Math.sqrt(num); i++) { // Loop from 2 to sqrt(num)
                if (num % i == 0) { // If divisible by any number other than 1 and itself
                    isPrime = false;
                    break; // Exit loop as it's not prime
                }
            }
        }

        // Output result
        if (isPrime) {
            System.out.println(num + " is a Prime Number.");
        } else {
            System.out.println(num + " is NOT a Prime Number.");
        }

        sc.close(); // Close scanner
    }
}
