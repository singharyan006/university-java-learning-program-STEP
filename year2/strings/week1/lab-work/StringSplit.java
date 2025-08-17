// Write a program to split the text into words, compare the result with the split() method and
// display the result
// Hint =>
// a. Take user input using the Scanner nextLine() method
// b. Create a Method to find the length of the String without using the built-in length() method.
// c. Create a Method to split the text into words using the charAt() method without using the
// String built-in split() method and return the words. Use the following logic
// i. Firstly Count the number of words in the text and create an array to store the
// indexes of the spaces for each word in a 1D array
// ii. Then Create an array to store the words and use the indexes to extract the words
// d. Create a method to compare the two String arrays and return a boolean
// e. The main function calls the user-defined method and the built-in split() method. Call the
// user defined method to compare the two string arrays and display the result


import java.util.*;

public class StringSplit {
    // Method to find the length of the String without using length()
    public static int getLength(String str) {
        int count = 0;
        try {
            while (true) {
                str.charAt(count);
                count++;
            }
        } catch (IndexOutOfBoundsException e) {
            // End of string reached
        }
        return count;
    }

    // Method to split the text into words without using split()
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

    // Method to compare two String arrays
    public static boolean compareArrays(String[] arr1, String[] arr2) {
        if (arr1.length != arr2.length) return false;
        for (int i = 0; i < arr1.length; i++) {
            if (!arr1[i].equals(arr2[i])) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the String of your choice: ");
        String str = sc.nextLine();

        // Using built-in split()
        String[] builtInSplit = str.split(" ");

        // Using custom split
        String[] customSplitResult = customSplit(str);

        // Compare the two arrays
        boolean areEqual = compareArrays(builtInSplit, customSplitResult);

        // Display results
        System.out.println("Built-in split() result: " + Arrays.toString(builtInSplit));
        System.out.println("Custom split result: " + Arrays.toString(customSplitResult));
        System.out.println("Are both results equal? " + areEqual);
        System.out.println("Length of string (without length()): " + getLength(str));
    }
}