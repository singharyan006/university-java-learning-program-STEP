import java.util.Scanner;

public class SubstringComparison {

    public static String createSubstringUsingCharAt(String str, int start, int end) {
        String result = "";
        for (int i = start; i < end; i++) {
            result += str.charAt(i);
        }
        return result;
    }

    public static boolean compareUsingCharAt(String str1, String str2) {
        if (str1.length() != str2.length()) {
            return false;
        }
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the original string: ");
        String original = scanner.next();

        System.out.print("Enter the start index: ");
        int start = scanner.nextInt();

        System.out.print("Enter the end index: ");
        int end = scanner.nextInt();

        String substrCharAt = createSubstringUsingCharAt(original, start, end);
        String substrBuiltIn = original.substring(start, end);

        boolean comparisonResult = compareUsingCharAt(substrCharAt, substrBuiltIn);

        System.out.println("\nSubstring using charAt(): " + substrCharAt);
        System.out.println("Substring using substring(): " + substrBuiltIn);
        System.out.println("Are both substrings equal? " + comparisonResult);

        scanner.close();
    }
}
