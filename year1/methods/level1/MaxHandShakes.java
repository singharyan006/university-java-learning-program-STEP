import java.util.*;

public class MaxHandShakes {
    // Method to calculate the maximum number of handshakes
    public static int handshakeCalc(int num) {
        return (num * (num - 1)) / 2; // Combination formula
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get integer input for the number of students
        System.out.print("Enter the number of students: ");
        int num = scanner.nextInt();

        // Validate input
        if (num < 2) {
            System.out.println("There must be at least 2 students for a handshake.");
        } else {
            // Calculate and display the maximum number of handshakes
            int result = handshakeCalc(num);
            System.out.println("The maximum number of handshakes possible is " + result);
        }

        scanner.close();
    }
}