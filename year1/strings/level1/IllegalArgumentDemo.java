import java.util.Scanner;

public class IllegalArgumentDemo {

    public static void generateException(String input) {
        System.out.println("\nGenerating Runtime Exception...");
        String sub = input.substring(5, 2); 
        System.out.println("Substring: " + sub);
    }

    public static void handleException(String input) {
        System.out.println("\nHandling Runtime Exception...");
        try {
            String sub = input.substring(5, 2);
            System.out.println("Substring: " + sub);
        } catch (IllegalArgumentException e) {
            System.out.println("Caught IllegalArgumentException: " + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("Caught RuntimeException: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String userInput = scanner.nextLine();

        try {
            generateException(userInput);
        } catch (RuntimeException e) {
            System.out.println("Exception caught in main: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }

        handleException(userInput);

        scanner.close();
    }
}