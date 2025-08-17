// Write a program to find the frequency of characters in a string using the charAt() method and
// display the result
// Hint =>
// a. Create a method to find the frequency of characters in a string using the charAt() method
// and return the characters and their frequencies in a 2D array. The logic used here is as
// follows:
// i. Create an array to store the frequency of characters in the text. ASCII values of
// characters are used as indexes in the array to store the frequency of each character.
// There are 256 ASCII characters
// ii. Loop through the text to find the frequency of characters in the text
// iii. Create an array to store the characters and their frequencies
// iv. Loop through the characters in the text and store the characters and their
// frequencies
// b. In the main function take user inputs, call user-defined methods, and displays result.

import java.util.*;

public class CharFrequency {
    // Method to find frequency of characters and return as 2D array
    public static Object[][] getCharFrequencies(String text) {
        int[] freq = new int[256]; // ASCII
        int len = text.length();
        // Count frequency
        for (int i = 0; i < len; i++) {
            freq[text.charAt(i)]++;
        }
        // Count unique characters
        int uniqueCount = 0;
        for (int i = 0; i < len; i++) {
            if (freq[text.charAt(i)] > 0) {
                uniqueCount++;
                freq[text.charAt(i)] = -freq[text.charAt(i)]; // Mark as processed
            }
        }
        Object[][] result = new Object[uniqueCount][2];
        int idx = 0;
        for (int i = 0; i < len; i++) {
            char c = text.charAt(i);
            if (freq[c] < 0) {
                result[idx][0] = c;
                result[idx][1] = -freq[c];
                freq[c] = 0; // Reset to avoid duplicate
                idx++;
            }
        }
        return result;
    }

    // Method to display character frequencies
    public static void displayFrequencies(Object[][] freqArr) {
        System.out.println("Character | Frequency");
        System.out.println("---------------------");
        for (Object[] row : freqArr) {
            System.out.println("     " + row[0] + "     |    " + row[1]);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String text = sc.nextLine();
        Object[][] freqArr = getCharFrequencies(text);
        displayFrequencies(freqArr);
        sc.close();
    }
}