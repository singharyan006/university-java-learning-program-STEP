import java.util.*;

public class ASCIIProcessor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String input = scanner.nextLine();

        System.out.println("\n=== CHARACTER ANALYSIS ===");
        for (char ch : input.toCharArray()) {
            int ascii = (int) ch;
            System.out.println("Character: '" + ch + "' | ASCII: " + ascii);
            String type = classifyCharacter(ch);
            System.out.println("Type: " + type);

            if (Character.isLetter(ch)) {
                char upper = Character.toUpperCase(ch);
                char lower = Character.toLowerCase(ch);
                System.out.println("Uppercase: '" + upper + "' | ASCII: " + (int) upper);
                System.out.println("Lowercase: '" + lower + "' | ASCII: " + (int) lower);
                System.out.println("ASCII Difference: " + Math.abs((int) upper - (int) lower));
            }
            System.out.println();
        }

        System.out.println("=== ASCII ART ===");
        displayASCIITable(33, 126); // Printable characters

        System.out.print("\nEnter shift value for Caesar Cipher: ");
        int shift = scanner.nextInt();
        scanner.nextLine(); // consume newline

        String encrypted = caesarCipher(input, shift);
        System.out.println("Encrypted (Caesar Cipher): " + encrypted);

        int[] asciiArray = stringToASCII(input);
        System.out.println("ASCII Array: " + Arrays.toString(asciiArray));

        String restored = asciiToString(asciiArray);
        System.out.println("Restored String: " + restored);

        scanner.close();
    }

    public static String classifyCharacter(char ch) {
        if (Character.isUpperCase(ch)) return "Uppercase Letter";
        else if (Character.isLowerCase(ch)) return "Lowercase Letter";
        else if (Character.isDigit(ch)) return "Digit";
        else return "Special Character";
    }

    public static char toggleCase(char ch) {
        if (Character.isUpperCase(ch)) return (char) (ch + 32);
        else if (Character.isLowerCase(ch)) return (char) (ch - 32);
        else return ch;
    }

    public static String caesarCipher(String text, int shift) {
        StringBuilder result = new StringBuilder();
        for (char ch : text.toCharArray()) {
            if (Character.isLetter(ch)) {
                char base = Character.isUpperCase(ch) ? 'A' : 'a';
                char shifted = (char) ((ch - base + shift) % 26 + base);
                result.append(shifted);
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }

    public static void displayASCIITable(int start, int end) {
        for (int i = start; i <= end; i++) {
            System.out.print((char) i + "(" + i + ") ");
            if ((i - start + 1) % 10 == 0) System.out.println();
        }
    }

    public static int[] stringToASCII(String text) {
        int[] asciiValues = new int[text.length()];
        for (int i = 0; i < text.length(); i++) {
            asciiValues[i] = (int) text.charAt(i);
        }
        return asciiValues;
    }

    public static String asciiToString(int[] asciiValues) {
        StringBuilder result = new StringBuilder();
        for (int val : asciiValues) {
            result.append((char) val);
        }
        return result.toString();
    }
}
