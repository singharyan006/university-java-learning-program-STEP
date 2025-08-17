import java.util.*;

public class DoubleOpt {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Taking input
        System.out.print("Enter value for a (double): ");
        double a = sc.nextDouble();

        System.out.print("Enter value for b (double): ");
        double b = sc.nextDouble();

        System.out.print("Enter value for c (double): ");
        double c = sc.nextDouble();

        // Performing operations
        double result1 = a + b * c;   // Multiplication (*) has higher precedence than addition (+)
        double result2 = a * b + c;   // Multiplication (*) first, then addition (+)
        double result3 = c + a / b;   // Division (/) first, then addition (+)
        double result4 = a % b + c;   // Modulus (%) first, then addition (+)

        // Printing results
        System.out.println("Result of a + b * c: " + result1);
        System.out.println("Result of a * b + c: " + result2);
        System.out.println("Result of c + a / b: " + result3);
        System.out.println("Result of a % b + c: " + result4);

    }
}
