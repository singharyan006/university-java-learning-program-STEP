import java.util.*;

public class LargestDigits1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter a number: ");
        int number = sc.nextInt();
        
        int maxDigit = 10;
        int[] digits = new int[maxDigit];
        int index = 0;
        
        while (number != 0) {
            if (index == maxDigit) {
                maxDigit += 10;
                int[] temp = new int[maxDigit];
                System.arraycopy(digits, 0, temp, 0, digits.length);
                digits = temp;
            }
            
            digits[index++] = number % 10;
            number /= 10;
        }
        
        if (index == 0) {
            System.out.println("No digits found in the input!");
            return;
        }
        
        int largest = 0, secondLargest = 0;
        for (int i = 0; i < index; i++) {
            if (digits[i] > largest) {
                secondLargest = largest;
                largest = digits[i];
            } else if (digits[i] > secondLargest && digits[i] != largest) {
                secondLargest = digits[i];
            }
        }
        
        System.out.println("Largest digit: " + largest);
        if (secondLargest != 0)
            System.out.println("Second largest digit: " + secondLargest);
        else
            System.out.println("No second largest digit found!");
        
        sc.close();
    }
}
