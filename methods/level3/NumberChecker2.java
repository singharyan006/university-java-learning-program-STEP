import java.util.Arrays;

public class NumberChecker2 {
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
    
    public static int[] reverseDigitsArray(int[] digits) {
        int[] reversed = new int[digits.length];
        for (int i = 0; i < digits.length; i++) {
            reversed[i] = digits[digits.length - 1 - i];
        }
        return reversed;
    }
    
    public static boolean areArraysEqual(int[] arr1, int[] arr2) {
        return Arrays.equals(arr1, arr2);
    }
    
    public static boolean isPalindrome(int num) {
        int[] digits = getDigitsArray(num);
        int[] reversed = reverseDigitsArray(digits);
        return areArraysEqual(digits, reversed);
    }
    
    public static boolean isDuckNumber(int num) {
        String numStr = String.valueOf(num);
        return numStr.contains("0") && numStr.charAt(0) != '0';
    }
    
    public static void main(String[] args) {
        int num = 153;
        System.out.println("Count of digits: " + countDigits(num));
        int[] digitsArray = getDigitsArray(num);
        System.out.println("Digits array: " + Arrays.toString(digitsArray));
        System.out.println("Reversed digits array: " + Arrays.toString(reverseDigitsArray(digitsArray)));
        System.out.println("Is Palindrome: " + isPalindrome(num));
        System.out.println("Is Duck Number: " + isDuckNumber(num));
    }
}
