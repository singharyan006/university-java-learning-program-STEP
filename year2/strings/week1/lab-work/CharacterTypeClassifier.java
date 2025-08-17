// Write a program to find vowels and consonants in a string and display the character type -
// Vowel, Consonant, or Not a Letter
// Hint =>
// a. Create a method to check if the character is a vowel or consonant and return the result.
// The logic used here is as follows:
// i. Convert the character to lowercase if it is an uppercase letter using the ASCII values
// of the characters
// ii. Check if the character is a vowel or consonant and return Vowel, Consonant, or Not
// a Letter
// b. Create a Method to find vowels and consonants in a string using charAt() method and
// return the character and vowel or consonant in a 2D array
// c. Create a Method to display the 2D Array of Strings in a Tabular Format
// d. Finally, the main function takes user inputs, calls the user-defined methods, and displays
// the result.

import java.util.*;

public class CharacterTypeClassifier {
    // Method to check if a character is vowel, consonant, or not a letter
    public static String checkCharType(char ch) {
        if (ch >= 'A' && ch <= 'Z') {
            ch = (char)(ch + 32);
        }
        if (ch >= 'a' && ch <= 'z') {
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                return "Vowel";
            } else {
                return "Consonant";
            }
        } else {
            return "Not a Letter";
        }
    }

    // Method to build 2D array of character and its type
    public static String[][] classifyCharacters(String text) {
        int length = 0;
        try {
            while (true) {
                text.charAt(length);
                length++;
            }
        } catch (IndexOutOfBoundsException e) {
            // End of string
        }
        String[][] result = new String[length][2];
        for (int i = 0; i < length; i++) {
            char ch = text.charAt(i);
            result[i][0] = String.valueOf(ch);
            result[i][1] = checkCharType(ch);
        }
        return result;
    }

    // Method to display 2D array in tabular format
    public static void displayTable(String[][] data) {
        System.out.printf("%-10s %-15s\n", "Character", "Type");
        System.out.println("---------------------------");
        for (String[] row : data) {
            System.out.printf("%-10s %-15s\n", row[0], row[1]);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String input = sc.nextLine();

        String[][] characterData = classifyCharacters(input);
        displayTable(characterData);
        sc.close();
    }
}