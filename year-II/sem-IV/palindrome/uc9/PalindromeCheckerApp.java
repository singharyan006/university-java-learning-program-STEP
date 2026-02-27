import java.util.Scanner;

public class PalindromeCheckerApp {

    public static boolean isPalindrome(String input) {
        input = input.replaceAll("\\s+", "").toLowerCase();
        return check(input, 0, input.length() - 1);
    }

    private static boolean check(String str, int left, int right) {

        // Base condition
        if (left >= right)
            return true;

        // Mismatch condition
        if (str.charAt(left) != str.charAt(right))
            return false;

        // Recursive call
        return check(str, left + 1, right - 1);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String input = scanner.nextLine();

        System.out.println(isPalindrome(input) ? "Palindrome" : "Not a Palindrome");

        scanner.close();
    }
}