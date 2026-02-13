// Find unique characters in a string using the charAt() method and display the result
// Hint =>
// a. Create a Method to find the length of the text without using the String method length()
// b. Create a method to Find unique characters in a string using the charAt() method and
// return them as a 1D array. The logic used here is as follows:
// i. Create an array to store the unique characters in the text. The size is the length of
// the text
// ii. Loops to Find the unique characters in the text. Find the unique characters in the text
// using a nested loop. An outer loop iterates through each character and an inner loop
// checks if the character is unique by comparing it with the previous characters. If the
// character is unique, it is stored in the result array
// iii. Create a new array to store the unique characters
// c. Finally, the main function takes user inputs, calls the user-defined methods, and displays
// the result.

import java.util.*;

public class UniqueChar {
    // Method to find the length of the text without using String.length()
    public static int getLength(String text) {
        int count = 0;
        try {
            while (true) {
                text.charAt(count);
                count++;
            }
        } catch (IndexOutOfBoundsException e) {
            // End of string
        }
        return count;
    }

    // Method to find unique characters using charAt()
    public static char[] findUniqueChars(String text) {
        int len = getLength(text);
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
        // Create a new array with only the unique characters
        char[] result = new char[uniqueCount];
        for (int i = 0; i < uniqueCount; i++) {
            result[i] = unique[i];
        }
        return result;
    }

    // Method to display unique characters
    public static void displayUniqueChars(char[] chars) {
        System.out.print("Unique characters: ");
        for (char c : chars) {
            System.out.print(c + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String text = sc.nextLine();
        int len = getLength(text);
        System.out.println("Length (without using length()): " + len);
        char[] uniqueChars = findUniqueChars(text);
        displayUniqueChars(uniqueChars);
        sc.close();
    }
}