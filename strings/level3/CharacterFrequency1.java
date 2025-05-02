import java.util.Scanner;

public class CharacterFrequency1 {

    public static String[] findCharacterFrequencies(String text) {
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

    public static void displayCharacterFrequencies(String[] frequencies) {
        System.out.println("Character | Frequency");
        System.out.println("---------------------");

        for (String frequency : frequencies) {
            if (frequency != null) {
                System.out.println(frequency);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String text = scanner.nextLine();

        String[] frequencies = findCharacterFrequencies(text);
        displayCharacterFrequencies(frequencies);
    }
}
