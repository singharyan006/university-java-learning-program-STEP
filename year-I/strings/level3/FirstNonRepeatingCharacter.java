import java.util.Scanner;

public class FirstNonRepeatingCharacter {

    public static char findFirstNonRepeatingCharacter(String text) {
        int[] frequency = new int[256]; // Array to store frequency of each character

        // Loop through the string to count the frequency of each character
        for (int i = 0; i < text.length(); i++) {
            frequency[text.charAt(i)]++;
        }

        // Loop through the string again to find the first character with frequency 1
        for (int i = 0; i < text.length(); i++) {
            if (frequency[text.charAt(i)] == 1) {
                return text.charAt(i);
            }
        }

        return '\0'; // Return null character if no non-repeating character is found
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String text = scanner.nextLine();

        char result = findFirstNonRepeatingCharacter(text);

        if (result == '\0') {
            System.out.println("No non-repeating character found.");
        } else {
            System.out.println("The first non-repeating character is: " + result);
        }
    }
}
