import java.util.Stack;
import java.util.Scanner;

class PalindromeChecker {

    // Public service method
    public boolean checkPalindrome(String input) {

        input = input.replaceAll("\\s+", "").toLowerCase();

        Stack<Character> stack = new Stack<>();

        for (char c : input.toCharArray()) {
            stack.push(c);
        }

        for (char c : input.toCharArray()) {
            if (c != stack.pop())
                return false;
        }

        return true;
    }
}

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        PalindromeChecker service = new PalindromeChecker();

        System.out.print("Enter a string: ");
        String input = scanner.nextLine();

        System.out.println(
                service.checkPalindrome(input) 
                ? "Palindrome" 
                : "Not a Palindrome"
        );

        scanner.close();
    }
}