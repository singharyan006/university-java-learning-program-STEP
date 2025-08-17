import java.util.Scanner;

public class NumberFormatDemo {

    public static void generateException(String input) {
        System.out.println("\nGenerating NumberFormatException...");
        int number = Integer.parseInt(input);
        System.out.println("Parsed number: " + number);
    }

    public static void handleException(String input) {
        System.out.println("\nHandling NumberFormatException...");
        try {
            int number = Integer.parseInt(input);
            System.out.println("Parsed number: " + number);
        } catch (NumberFormatException e) {
            System.out.println("Caught NumberFormatException: " + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("Caught RuntimeException: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a number (as string): ");
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
