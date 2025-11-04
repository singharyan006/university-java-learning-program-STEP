import java.util.Scanner;

public class StringIndexDemo {

    public static void generateException(String input) {
        System.out.println("Character at index 10: " + input.charAt(10));
    }

    public static void handleException(String input) {
        try {
            System.out.println("Character at index 10: " + input.charAt(10));
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Caught StringIndexOutOfBoundsException: Index is out of bounds.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String userInput = scanner.nextLine();

        //generateException(userInput);
        handleException(userInput);
    }
}
