import java.util.Scanner;

public class CharacterFrequency {

    public static String[][] findCharacterFrequencies(String text) {
        int[] frequency = new int[256]; // Array to store frequency of each character
        int length = text.length();

        // Loop through the string to count the frequency of each character
        for (int i = 0; i < length; i++) {
            frequency[text.charAt(i)]++;
        }

        // Create a 2D array to store the characters and their frequencies
        String[][] result = new String[length][2];
        int index = 0;

        // Loop through the frequency array to store non-zero frequency characters in the result array
        for (int i = 0; i < 256; i++) {
            if (frequency[i] > 0) {
                result[index][0] = String.valueOf((char) i);  // Store character
                result[index][1] = String.valueOf(frequency[i]); // Store frequency
                index++;
            }
        }

        return result;
    }

    public static void displayCharacterFrequencies(String[][] frequencies) {
        System.out.println("Character | Frequency");
        System.out.println("---------------------");
        
        for (int i = 0; i < frequencies.length; i++) {
            if (frequencies[i][0] != null) {
                System.out.println(frequencies[i][0] + "        | " + frequencies[i][1]);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String text = scanner.nextLine();

        // Find the frequencies of characters
        String[][] frequencies = findCharacterFrequencies(text);

        // Display the frequencies in a tabular format
        displayCharacterFrequencies(frequencies);
    }
}
