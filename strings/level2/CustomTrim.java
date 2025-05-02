import java.util.Scanner;

public class CustomTrim {

    public static int[] findTrimIndices(String text) {
        int start = 0, end = 0;

        try {
            while (text.charAt(start) == ' ') {
                start++;
            }
        } catch (IndexOutOfBoundsException e) {
            return new int[]{0, 0}; // All spaces or empty
        }

        try {
            end = start;
            while (true) {
                text.charAt(end);
                end++;
            }
        } catch (IndexOutOfBoundsException e) {
            end--; // Reached end of string
        }

        while (text.charAt(end) == ' ' && end > start) {
            end--;
        }

        return new int[]{start, end};
    }

    public static String customSubstring(String text, int start, int end) {
        String result = "";
        for (int i = start; i <= end; i++) {
            result += text.charAt(i);
        }
        return result;
    }

    public static boolean compareStrings(String s1, String s2) {
        int i = 0;
        try {
            while (true) {
                if (s1.charAt(i) != s2.charAt(i)) {
                    return false;
                }
                i++;
            }
        } catch (IndexOutOfBoundsException e) {
            try {
                s1.charAt(i); // check if s1 is longer
                return false;
            } catch (IndexOutOfBoundsException ignored) {}
            try {
                s2.charAt(i); // check if s2 is longer
                return false;
            } catch (IndexOutOfBoundsException ignored) {}
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter text with leading and trailing spaces: ");
        String input = scanner.nextLine();

        int[] indices = findTrimIndices(input);
        String trimmedCustom = customSubstring(input, indices[0], indices[1]);
        String trimmedBuiltIn = input.trim();

        System.out.println("Custom Trimmed Text: \"" + trimmedCustom + "\"");
        System.out.println("Built-in Trimmed Text: \"" + trimmedBuiltIn + "\"");

        boolean areEqual = compareStrings(trimmedCustom, trimmedBuiltIn);
        System.out.println("Are both trimmed results equal? " + areEqual);
    }
}
