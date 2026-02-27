import java.util.Scanner;

public class PalindromeCheckerApp {

    // Approach 1: Reverse using StringBuilder
    static boolean checkWithReverse(String str) {
        String reversed = new StringBuilder(str).reverse().toString();
        return str.equals(reversed);
    }

    // Approach 2: Two-pointer technique
    static boolean checkWithTwoPointers(String str) {
        int left = 0, right = str.length() - 1;
        while (left < right) {
            if (str.charAt(left++) != str.charAt(right--)) {
                return false;
            }
        }
        return true;
    }

    // Approach 3: Recursive method
    static boolean checkRecursively(String str, int left, int right) {
        if (left >= right) return true;
        if (str.charAt(left) != str.charAt(right)) return false;
        return checkRecursively(str, left + 1, right - 1);
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String input = scanner.nextLine();

        // Reverse Method
        long start1 = System.nanoTime();
        boolean result1 = checkWithReverse(input);
        long end1 = System.nanoTime();

        // Two Pointer Method
        long start2 = System.nanoTime();
        boolean result2 = checkWithTwoPointers(input);
        long end2 = System.nanoTime();

        // Recursive Method
        long start3 = System.nanoTime();
        boolean result3 = checkRecursively(input, 0, input.length() - 1);
        long end3 = System.nanoTime();

        System.out.println("\nResults:");
        System.out.println("Reverse Method: " + result1 + 
                " | Time: " + (end1 - start1) + " ns");

        System.out.println("Two Pointer Method: " + result2 + 
                " | Time: " + (end2 - start2) + " ns");

        System.out.println("Recursive Method: " + result3 + 
                " | Time: " + (end3 - start3) + " ns");

        scanner.close();
    }
}