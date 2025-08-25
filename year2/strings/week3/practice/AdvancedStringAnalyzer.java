import java.util.*;

public class AdvancedStringAnalyzer {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== ADVANCED STRING ANALYZER ===");
        System.out.print("Enter first string: ");
        String str1 = scanner.nextLine();

        System.out.print("Enter second string: ");
        String str2 = scanner.nextLine();

        performAllComparisons(str1, str2);

        System.out.println("\nSimilarity Percentage: " + calculateSimilarity(str1, str2) + "%");

        System.out.println("\nMemory Usage Analysis:");
        analyzeMemoryUsage(str1, str2);

        System.out.println("\nOptimized String Processing:");
        String[] inputs = {str1, str2, "Extra", "Words"};
        System.out.println(optimizedStringProcessing(inputs));

        System.out.println("\nString Intern Demonstration:");
        demonstrateStringIntern();

        scanner.close();
    }

    // Levenshtein distance-based similarity
    public static double calculateSimilarity(String str1, String str2) {
        int len1 = str1.length();
        int len2 = str2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];

        for (int i = 0; i <= len1; i++) dp[i][0] = i;
        for (int j = 0; j <= len2; j++) dp[0][j] = j;

        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                int cost = (str1.charAt(i - 1) == str2.charAt(j - 1)) ? 0 : 1;
                dp[i][j] = Math.min(
                    Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1),
                    dp[i - 1][j - 1] + cost
                );
            }
        }

        int maxLen = Math.max(len1, len2);
        return maxLen == 0 ? 100.0 : ((1.0 - (double) dp[len1][len2] / maxLen) * 100);
    }

    public static void performAllComparisons(String str1, String str2) {
        System.out.println("\n=== COMPARISON RESULTS ===");
        System.out.println("Reference Equality (==): " + (str1 == str2));
        System.out.println("Content Equality (equals): " + str1.equals(str2));
        System.out.println("Case-Insensitive Equality: " + str1.equalsIgnoreCase(str2));
        System.out.println("Lexicographic Comparison: " + str1.compareTo(str2));
        System.out.println("Case-Insensitive Lexicographic Comparison: " + str1.compareToIgnoreCase(str2));
    }

    public static void analyzeMemoryUsage(String... strings) {
        for (String s : strings) {
            System.out.println("String: \"" + s + "\" | Length: " + s.length() + " | Approx. Memory: " + (s.length() * 2) + " bytes");
        }
    }

    public static String optimizedStringProcessing(String[] inputs) {
        StringBuilder sb = new StringBuilder();
        for (String input : inputs) {
            sb.append(input.toUpperCase()).append(" | ");
        }
        return sb.toString().trim();
    }

    public static void demonstrateStringIntern() {
        String a = "Java";
        String b = new String("Java");
        String c = b.intern();

        System.out.println("a == b: " + (a == b)); // false
        System.out.println("a == c (after intern): " + (a == c)); // true
    }
}
