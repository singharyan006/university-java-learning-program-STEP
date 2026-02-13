// Write a program to find vowels and consonants in a string and display the count of Vowels
// and Consonants in the string
// Hint =>
// a. Create a method to check if the character is a vowel or consonant and return the result.
// The logic used here is as follows:

// 3

// i. Convert the character to lowercase if it is an uppercase letter using the ASCII values
// of the characters
// ii. Check if the character is a vowel or consonant and return Vowel, Consonant, or Not
// a Letter
// b. Create a Method to Method to find vowels and consonants in a string using charAt()
// method and finally return the count of vowels and consonants in an array
// c. Finally, the main function takes user inputs, calls the user-defined methods, and displays
// the result.

import java.util.*;

public class VowelConsonantCounter {
    // Method to check if a character is vowel, consonant, or not a letter
    public static String checkCharType(char ch) {
        // Convert to lowercase if uppercase
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

    // Method to count vowels and consonants in a string
    public static int[] countVowelsConsonants(String text) {
        int vowels = 0, consonants = 0;
        int i = 0;
        try {
            while (true) {
                char ch = text.charAt(i);
                String type = checkCharType(ch);
                if (type.equals("Vowel")) {
                    vowels++;
                } else if (type.equals("Consonant")) {
                    consonants++;
                }
                i++;
            }
        } catch (IndexOutOfBoundsException e) {
            // End of string
        }
        return new int[]{vowels, consonants};
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a sentence: ");
        String input = sc.nextLine();

        int[] counts = countVowelsConsonants(input);
        System.out.println("Vowels: " + counts[0]);
        System.out.println("Consonants: " + counts[1]);
        sc.close();
    }
}