import java.util.*;

public class NumberStorage {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double[] numbers = new double[10];
        double total = 0.0;
        int index = 0;
        while (true) {
            System.out.print("Enter a number (0 or negative to stop): ");
            double input = sc.nextDouble();
            if (input <= 0 || index == 10) {
                break;
            }
            numbers[index++] = input;
        }
        System.out.println("Stored numbers:");
        for (int i = 0; i < index; i++) {
            System.out.println(numbers[i]);
            total += numbers[i];
        }
        System.out.println("Sum of all numbers: " + total);
        sc.close();
    }
}
