import java.util.*;

public class DigitFrequency {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter a number: ");
        int number = sc.nextInt();
        
        int temp = number;
        int count = 0;
        while (temp > 0) {
            count++;
            temp /= 10;
        }
        
        int[] digits = new int[count];
        int[] frequency = new int[10];
        
        temp = number;
        for (int i = 0; i < count; i++) {
            digits[i] = temp % 10;
            frequency[digits[i]]++;
            temp /= 10;
        }
        
        System.out.println("Digit | Frequency");
        for (int i = 0; i < 10; i++) {
            if (frequency[i] > 0) {
                System.out.println("   " + i + "   |     " + frequency[i]);
            }
        }
        
        sc.close();
    }
}