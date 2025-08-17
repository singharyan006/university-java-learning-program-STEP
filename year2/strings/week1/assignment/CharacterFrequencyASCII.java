// Write a program to find the frequency of characters in a string using unique characters and
// display the result
// Hint =>
// a. Create a method to Find unique characters in a string using the charAt() method and
// return them as a 1D array. Use Nested Loops to find the unique characters in the text
// b. Create a method to find the frequency of characters in a string and return the characters
// and their frequencies in a 2D array. The logic used here is as follows:
// i. Create an array to store the frequency of characters in the text. ASCII values of
// characters are used as indexes in the array to store the frequency of each character.
// There are 256 ASCII characters
// ii. Loop through the text to find the frequency of characters in the text
// iii. Call the uniqueCharacters() method to find the unique characters in the text
// iv. Create a 2D String array to store the unique characters and their frequencies.
// v. Loop through the unique characters and store the characters and their frequencies
// c. In the main function take user inputs, call user-defined methods, and displays result.

import java.util.*;

public class CharacterFrequencyASCII {
    // Method to find unique characters using charAt()
    public static char[] uniqueCharacters(String text) {
        int len = 0;
        try {
            while (true) {
                text.charAt(len);
                len++;
            }
        } catch (IndexOutOfBoundsException e) {}
        char[] unique = new char[len];
        int uniqueCount = 0;
        for (int i = 0; i < len; i++) {
            char c = text.charAt(i);
            boolean isUnique = true;
            for (int j = 0; j < i; j++) {
                if (text.charAt(j) == c) {
                    isUnique = false;
                    break;
                }
            }
            if (isUnique) {
                unique[uniqueCount++] = c;
            }
        }
        char[] result = new char[uniqueCount];
        for (int i = 0; i < uniqueCount; i++) {
            result[i] = unique[i];
        }
        return result;
    }

    // Method to find frequency of characters and return as 2D String array
    public static String[][] characterFrequencies(String text) {
        int[] freq = new int[256];
        int len = 0;
        try {
            while (true) {
                text.charAt(len);
                len++;
            }
        } catch (IndexOutOfBoundsException e) {}
        for (int i = 0; i < len; i++) {
            freq[text.charAt(i)]++;
        }
        char[] unique = uniqueCharacters(text);
        String[][] result = new String[unique.length][2];
        for (int i = 0; i < unique.length; i++) {
            result[i][0] = String.valueOf(unique[i]);
            result[i][1] = String.valueOf(freq[unique[i]]);
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String text = sc.nextLine();

        String[][] freqArr = characterFrequencies(text);
        System.out.printf("%-10s %-10s\n", "Character", "Frequency");
        System.out.println("---------------------");
        for (int i = 0; i < freqArr.length; i++) {
            System.out.printf("%-10s %-10s\n", freqArr[i][0], freqArr[i][1]);
        }
        sc.close();
    }
}