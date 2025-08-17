import java.util.Scanner;

public class LowerCaseCompare {

    public static String convertToLowerCase(String text) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (ch >= 'A' && ch <= 'Z') {
                ch = (char) (ch + 32);
            }
            result.append(ch);
        }
        return result.toString();
    }

    public static boolean compareStrings(String str1, String str2) {
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
        System.out.print("Enter a string: ");
        String input = scanner.nextLine();

        String builtInLower = input.toLowerCase();
        String manualLower = convertToLowerCase(input);

        boolean isSame = compareStrings(builtInLower, manualLower);

        System.out.println("Built-in lowercase: " + builtInLower);
        System.out.println("Manual lowercase:   " + manualLower);
        System.out.println("Are both conversions equal? " + isSame);
    }
}
