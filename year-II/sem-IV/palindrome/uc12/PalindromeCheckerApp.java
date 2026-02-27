import java.util.*;

// Strategy Interface
interface PalindromeStrategy {
    boolean check(String input);
}

// Stack-based Strategy
class StackStrategy implements PalindromeStrategy {

    public boolean check(String input) {
        input = input.replaceAll("\\s+", "").toLowerCase();

        Stack<Character> stack = new Stack<>();
        for (char c : input.toCharArray())
            stack.push(c);

        for (char c : input.toCharArray())
            if (c != stack.pop())
                return false;

        return true;
    }
}

// Deque-based Strategy
class DequeStrategy implements PalindromeStrategy {

    public boolean check(String input) {
        input = input.replaceAll("\\s+", "").toLowerCase();

        Deque<Character> deque = new ArrayDeque<>();
        for (char c : input.toCharArray())
            deque.addLast(c);

        while (deque.size() > 1)
            if (!deque.removeFirst().equals(deque.removeLast()))
                return false;

        return true;
    }
}

// Context
class PalindromeChecker {

    private PalindromeStrategy strategy;

    public PalindromeChecker(PalindromeStrategy strategy) {
        this.strategy = strategy;
    }

    public boolean checkPalindrome(String input) {
        return strategy.check(input);
    }
}

// Main
public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Choose Strategy (1-Stack, 2-Deque): ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        PalindromeStrategy strategy =
                (choice == 1) ? new StackStrategy() : new DequeStrategy();

        PalindromeChecker checker = new PalindromeChecker(strategy);

        System.out.print("Enter a string: ");
        String input = scanner.nextLine();

        System.out.println(
                checker.checkPalindrome(input)
                        ? "Palindrome"
                        : "Not a Palindrome"
        );

        scanner.close();
    }
}