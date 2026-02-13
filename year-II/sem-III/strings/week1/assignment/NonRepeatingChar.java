// Write a program to find the first non-repeating character in a string and show the result
// Hint =>
// a. Non-repeating character is a character that occurs only once in the string
// b. Create a Method to find the first non-repeating character in a string using the charAt()
// method and return the character. The logic used here is as follows:
// i. Create an array to store the frequency of characters in the text. ASCII values of
// characters are used as indexes in the array to store the frequency of each character.
// There are 256 ASCII characters
// ii. Loop through the text to find the frequency of characters in the text
// iii. Loop through the text to find the first non-repeating character in the text by checking
// the frequency of each character
// c. In the main function take user inputs, call user-defined methods, and displays result.


import java.util.*;

public class NonRepeatingChar {
    // Method to find the first non-repeating character
    public static char findFirstNonRepeatingChar(String text) {
        int[] freq = new int[256]; // ASCII
        int len = text.length();
        // Count frequency
        for (int i = 0; i < len; i++) {
            freq[text.charAt(i)]++;
        }
        // Find first non-repeating
        for (int i = 0; i < len; i++) {
            if (freq[text.charAt(i)] == 1) {
                return text.charAt(i);
            }
        }
        return '\0'; // If none found
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String text = sc.nextLine();
        char result = findFirstNonRepeatingChar(text);
        if (result != '\0') {
            System.out.println("First non-repeating character: " + result);
        } else {
            System.out.println("No non-repeating character found.");
        }
        sc.close();
    }
}