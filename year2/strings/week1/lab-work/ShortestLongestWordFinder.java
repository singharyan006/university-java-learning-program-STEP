// Write a program to split the text into words and find the shortest and longest strings in a
// given text
// Hint =>
// a. Take user input using the Scanner nextLine() method
// b. Create a Method to split the text into words using the charAt() method without using the
// String built-in split() method and return the words.
// c. Create a method to find and return a string's length without using the length() method.
// d. Create a method to take the word array and return a 2D String array of the word and its
// corresponding length. Use String built-in function String.valueOf() to generate the String
// value for the number
// e. Create a Method that takes the 2D array of word and corresponding length as
// parameters, find the shortest and longest string and return them in an 1D int array.
// f. The main function calls the user-defined methods and displays the result.

import java.util.*;

public class ShortestLongestWordFinder {
    // Method to find length of a string without using length()
    public static int getLength(String str) {
        int count = 0;
        try {
            while (true) {
                str.charAt(count);
                count++;
            }
        } catch (IndexOutOfBoundsException e) {
            // End of string
        }
        return count;
    }

    // Method to split text into words without using split()
    public static String[] customSplit(String str) {
        List<String> words = new ArrayList<>();
        int len = getLength(str);
        int start = 0;
        for (int i = 0; i <= len; i++) {
            if (i == len || str.charAt(i) == ' ') {
                if (start < i) {
                    words.add(str.substring(start, i));
                }
                start = i + 1;
            }
        }
        return words.toArray(new String[0]);
    }

    // Method to build 2D String array of word and its length
    public static String[][] buildWordLengthTable(String[] words) {
        String[][] table = new String[words.length][2];
        for (int i = 0; i < words.length; i++) {
            table[i][0] = words[i];
            table[i][1] = String.valueOf(getLength(words[i]));
        }
        return table;
    }

    // Method to find shortest and longest word indices
    public static int[] findShortestLongest(String[][] table) {
        int minIndex = 0, maxIndex = 0;
        int minLength = Integer.parseInt(table[0][1]);
        int maxLength = Integer.parseInt(table[0][1]);
        for (int i = 1; i < table.length; i++) {
            int length = Integer.parseInt(table[i][1]);
            if (length < minLength) {
                minLength = length;
                minIndex = i;
            }
            if (length > maxLength) {
                maxLength = length;
                maxIndex = i;
            }
        }
        return new int[]{minIndex, maxIndex};
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a sentence: ");
        String input = sc.nextLine();

        String[] words = customSplit(input);
        String[][] table = buildWordLengthTable(words);
        int[] result = findShortestLongest(table);

        System.out.printf("%-15s %-10s\n", "Word", "Length");
        System.out.println("-----------------------------");
        for (int i = 0; i < table.length; i++) {
            String word = table[i][0];
            int length = Integer.parseInt(table[i][1]);
            System.out.printf("%-15s %-10d\n", word, length);
        }

        System.out.println("\nShortest word: " + table[result[0]][0] + " (Length: " + table[result[0]][1] + ")");
        System.out.println("Longest word: " + table[result[1]][0] + " (Length: " + table[result[1]][1] + ")");
        sc.close();
    }
}