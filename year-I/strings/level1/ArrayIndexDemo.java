import java.util.Scanner;

public class ArrayIndexDemo {

    public static void generateException(String[] names) {
        System.out.println("\nGenerating ArrayIndexOutOfBoundsException...");
        String name = names[names.length];
        System.out.println("Name at invalid index: " + name);
    }

    public static void handleException(String[] names) {
        System.out.println("\nHandling ArrayIndexOutOfBoundsException...");
        try {
            String name = names[names.length];
            System.out.println("Name at invalid index: " + name);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Caught ArrayIndexOutOfBoundsException: " + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("Caught RuntimeException: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of names: ");
        int n = scanner.nextInt();
        scanner.nextLine();

        String[] names = new String[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Enter name " + (i + 1) + ": ");
            names[i] = scanner.nextLine();
        }

        try {
            generateException(names);
        } catch (RuntimeException e) {
            System.out.println("Exception caught in main: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }

        handleException(names);

        scanner.close();
    }
}
