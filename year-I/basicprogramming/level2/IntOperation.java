import java.util.Scanner;

public class IntOperation {
    public static void main(String[] args) {
        // Using Scanner for taking input under the alias sc
        Scanner scanner = new Scanner(System.in);

        // Taking input
        System.out.print("Enter value for a: ");
        int a = scanner.nextInt();

        // Taking input
        System.out.print("Enter value for b: ");
        int b = scanner.nextInt();

        // Taking input
        System.out.print("Enter value for c: ");
        int c = scanner.nextInt();

        // Performing operations
        int result1 = a + b * c;   // Multiplication (*) has higher precedence than addition (+)
        int result2 = a * b + c;   // Multiplication (*) first, then addition (+)
        int result3 = c + a / b;   // Division (/) first, then addition (+)
        int result4 = a % b + c;   // Modulus (%) first, then addition (+)

        // Printing results
        System.out.println("Result of a + b * c: " + result1);
        System.out.println("Result of a * b + c: " + result2);
        System.out.println("Result of c + a / b: " + result3);
        System.out.println("Result of a % b + c: " + result4);

        scanner.close();
    }
}
