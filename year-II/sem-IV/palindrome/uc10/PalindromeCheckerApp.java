import java.util.Scanner;

public class PalindromeChecker {

    // Normalize string (remove spaces + convert to lowercase)
    static String normalize(String input) {
        return input.replaceAll("\\s+", "").toLowerCase();
    }

    // Two-pointer palindrome check
    static boolean isPalindrome(String str) {
        int left = 0, right = str.length() - 1;

        while (left < right) {
            if (str.charAt(left++) != str.charAt(right--)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String input = scanner.nextLine();

        String processed = normalize(input);
        boolean result = isPalindrome(processed);

        System.out.println("Palindrome (Ignore Case & Spaces): " + result);

        scanner.close();
    }
}