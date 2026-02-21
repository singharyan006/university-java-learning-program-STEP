import java.util.Stack;

public class PalindromeCheckerApp {
    public static void main(String[] args) {

        String input = "madam";

        Stack<Character> stack = new Stack<>();

        // Push all characters into the stack
        for (int i = 0; i < input.length(); i++) {
            stack.push(input.charAt(i));
        }

        boolean isPalindrome = true;

        // Pop from stack and compare with original string
        for (int i = 0; i < input.length(); i++) {
            char poppedChar = stack.pop();

            if (input.charAt(i) != poppedChar) {
                isPalindrome = false;
                break;
            }
        }

        // Print result
        if (isPalindrome) {
            System.out.println("Palindrome");
        } else {
            System.out.println("Not Palindrome");
        }
    }
}
