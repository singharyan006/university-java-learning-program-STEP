import java.util.Scanner;

public class PalindromeCheckerApp {

    // Node definition
    static class Node {
        char data;
        Node next;

        Node(char data) {
            this.data = data;
        }
    }

    public static boolean isPalindrome(String input) {

        input = input.replaceAll("\\s+", "").toLowerCase();
        if (input.length() == 0) return true;

        // Convert string to linked list
        Node head = new Node(input.charAt(0));
        Node current = head;

        for (int i = 1; i < input.length(); i++) {
            current.next = new Node(input.charAt(i));
            current = current.next;
        }

        // Find middle (fast & slow pointer)
        Node slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Reverse second half
        Node prev = null;
        while (slow != null) {
            Node next = slow.next;
            slow.next = prev;
            prev = slow;
            slow = next;
        }

        // Compare halves
        Node left = head;
        Node right = prev;

        while (right != null) {
            if (left.data != right.data) return false;
            left = left.next;
            right = right.next;
        }

        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String input = scanner.nextLine();

        System.out.println(isPalindrome(input) ? "Palindrome" : "Not a Palindrome");

        scanner.close();
    }
}