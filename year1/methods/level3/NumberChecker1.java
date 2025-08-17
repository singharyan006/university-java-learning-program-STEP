import java.util.Arrays;

public class NumberChecker1 {
    public static int countDigits(int num) {
        return String.valueOf(num).length();
    }
    
    public static int[] getDigitsArray(int num) {
        String numStr = String.valueOf(num);
        int[] digits = new int[numStr.length()];
        for (int i = 0; i < numStr.length(); i++) {
            digits[i] = numStr.charAt(i) - '0';
        }
        return digits;
    }
    
    public static int sumOfDigits(int[] digits) {
        int sum = 0;
        for (int digit : digits) {
            sum += digit;
        }
        return sum;
    }
    
    public static int sumOfSquaresOfDigits(int[] digits) {
        int sum = 0;
        for (int digit : digits) {
            sum += Math.pow(digit, 2);
        }
        return sum;
    }
    
    public static boolean isHarshadNumber(int num) {
        int[] digits = getDigitsArray(num);
        int sum = sumOfDigits(digits);
        return num % sum == 0;
    }
    
    public static int[][] digitFrequency(int[] digits) {
        int[][] freq = new int[10][2];
        for (int i = 0; i < 10; i++) {
            freq[i][0] = i;
        }
        for (int digit : digits) {
            freq[digit][1]++;
        }
        return freq;
    }
    
    public static void findLargestTwo(int[] digits) {
        int largest = Integer.MIN_VALUE;
        int secondLargest = Integer.MIN_VALUE;
        
        for (int digit : digits) {
            if (digit > largest) {
                secondLargest = largest;
                largest = digit;
            } else if (digit > secondLargest && digit != largest) {
                secondLargest = digit;
            }
        }
        System.out.println("Largest: " + largest + ", Second Largest: " + (secondLargest == Integer.MIN_VALUE ? "None" : secondLargest));
    }
    
    public static void findSmallestTwo(int[] digits) {
        int smallest = Integer.MAX_VALUE;
        int secondSmallest = Integer.MAX_VALUE;
        
        for (int digit : digits) {
            if (digit < smallest) {
                secondSmallest = smallest;
                smallest = digit;
            } else if (digit < secondSmallest && digit != smallest) {
                secondSmallest = digit;
            }
        }
        System.out.println("Smallest: " + smallest + ", Second Smallest: " + (secondSmallest == Integer.MAX_VALUE ? "None" : secondSmallest));
    }
    
    public static void main(String[] args) {
        int num = 153;
        System.out.println("Count of digits: " + countDigits(num));
        int[] digitsArray = getDigitsArray(num);
        System.out.println("Digits array: " + Arrays.toString(digitsArray));
        System.out.println("Sum of digits: " + sumOfDigits(digitsArray));
        System.out.println("Sum of squares of digits: " + sumOfSquaresOfDigits(digitsArray));
        System.out.println("Is Harshad Number: " + isHarshadNumber(num));
        
        int[][] frequency = digitFrequency(digitsArray);
        System.out.println("Digit Frequencies:");
        for (int[] pair : frequency) {
            if (pair[1] > 0) {
                System.out.println("Digit: " + pair[0] + " -> Frequency: " + pair[1]);
            }
        }
        
        findLargestTwo(digitsArray);
        findSmallestTwo(digitsArray);
    }
}