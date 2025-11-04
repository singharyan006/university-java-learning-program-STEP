// Write a program to find the frequency of characters in a string using nested loops and
// display the result
// Hint =>
// a. Create a method to find the frequency of characters in a string and return the characters
// and their frequencies in a 1D array. The logic used here is as follows:
// i. Create an array to store the frequency of each character in the text and an array to
// store the characters in the text using the toCharArray() method
// ii. Loops to Find the frequency of each character in the text and store the result in a
// frequency array. For this use a Nested Loop with an Outer loop to iterate through
// each character in the text and initialize the frequency of each character to 1. And an
// Inner loop to check for duplicate characters. In case of duplicate increment the
// frequency value and set the duplicate characters to '0' to avoid counting them again.
// iii. Create a 1D String array to store the characters and their frequencies. For this
// Iterate through the characters in the text and store the characters and their
// frequencies
// b. Finally, the main function takes user inputs, calls the user-defined methods, and displays
// the result.

import java.util.*;

public class CharacterFrequencyNestedLoops {
    // Method to find frequency of characters using nested loops
    public static String[] characterFrequencies(String text) {
        char[] chars = text.toCharArray();
        int[] frequencies = new int[chars.length];
        String[] result = new String[chars.length];

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != '0') {
                frequencies[i] = 1;
                for (int j = i + 1; j < chars.length; j++) {
                    if (chars[i] == chars[j]) {
                        frequencies[i]++;
                        chars[j] = '0';
                    }
                }
                result[i] = String.valueOf(chars[i]) + ": " + frequencies[i];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String text = sc.nextLine();

        String[] freqArr = characterFrequencies(text);
        System.out.println("Character | Frequency");
        System.out.println("---------------------");
        for (String frequency : freqArr) {
            if (frequency != null) {
                System.out.println(frequency);
            }
        }
        sc.close();
    }
}