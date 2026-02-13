import java.util.*;

public class StringUtilityApp {

    private static StringBuilder output = new StringBuilder();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== STRING UTILITY APPLICATION ===");

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Text Analysis");
            System.out.println("2. String Transformation");
            System.out.println("3. ASCII Operations");
            System.out.println("4. Performance Testing");
            System.out.println("5. String Comparison Analysis");
            System.out.println("6. Custom String Algorithms");
            System.out.println("0. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter text for analysis: ");
                    performTextAnalysis(scanner.nextLine());
                    break;
                case 2:
                    System.out.print("Enter text for transformation: ");
                    String text = scanner.nextLine();
                    System.out.print("Enter operations (comma-separated: trim,upper,lower,replace): ");
                    String[] ops = scanner.nextLine().split(",");
                    System.out.println("Transformed: " + performTransformations(text, ops));
                    break;
                case 3:
                    System.out.print("Enter text for ASCII operations: ");
                    performASCIIOperations(scanner.nextLine());
                    break;
                case 4:
                    System.out.print("Enter number of iterations for performance test: ");
                    performPerformanceTest(scanner.nextInt());
                    break;
                case 5:
                    System.out.print("Enter first string: ");
                    String s1 = scanner.nextLine();
                    System.out.print("Enter second string: ");
                    String s2 = scanner.nextLine();
                    performComparisonAnalysis(new String[]{s1, s2});
                    break;
                case 6:
                    System.out.print("Enter text for custom algorithms: ");
                    performCustomAlgorithms(scanner.nextLine());
                    break;
                case 0:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    public static void performTextAnalysis(String text) {
        String[] words = text.trim().split("\\s+");
        String[] sentences = text.split("[.!?]");
        Map<Character, Integer> freq = new HashMap<>();

        for (char ch : text.toCharArray()) {
            if (Character.isLetterOrDigit(ch)) {
                freq.put(ch, freq.getOrDefault(ch, 0) + 1);
            }
        }

        output.setLength(0);
        output.append("Length: ").append(text.length()).append("\n");
        output.append("Word Count: ").append(words.length).append("\n");
        output.append("Sentence Count: ").append(sentences.length).append("\n");
        output.append("Character Frequency:\n");
        for (Map.Entry<Character, Integer> entry : freq.entrySet()) {
            output.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        displayResults();
    }

    public static String performTransformations(String text, String[] operations) {
        StringBuilder sb = new StringBuilder(text);
        for (String op : operations) {
            switch (op.trim().toLowerCase()) {
                case "trim":
                    sb = new StringBuilder(sb.toString().trim());
                    break;
                case "upper":
                    sb = new StringBuilder(sb.toString().toUpperCase());
                    break;
                case "lower":
                    sb = new StringBuilder(sb.toString().toLowerCase());
                    break;
                case "replace":
                    sb = new StringBuilder(sb.toString().replace(" ", "_"));
                    break;
            }
        }
        return sb.toString();
    }

    public static void performASCIIOperations(String text) {
        output.setLength(0);
        for (char ch : text.toCharArray()) {
            output.append("Char: ").append(ch)
                  .append(" | ASCII: ").append((int) ch).append("\n");
        }

        output.append("\nCaesar Cipher (+3): ");
        for (char ch : text.toCharArray()) {
            if (Character.isLetter(ch)) {
                char base = Character.isUpperCase(ch) ? 'A' : 'a';
                output.append((char) ((ch - base + 3) % 26 + base));
            } else {
                output.append(ch);
            }
        }
        output.append("\n");
        displayResults();
    }

    public static void performPerformanceTest(int iterations) {
        long start, end;

        start = System.nanoTime();
        String s = "";
        for (int i = 0; i < iterations; i++) s += "x";
        end = System.nanoTime();
        System.out.println("String time: " + (end - start) + " ns");

        start = System.nanoTime();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < iterations; i++) sb.append("x");
        end = System.nanoTime();
        System.out.println("StringBuilder time: " + (end - start) + " ns");

        start = System.nanoTime();
        StringBuffer sf = new StringBuffer();
        for (int i = 0; i < iterations; i++) sf.append("x");
        end = System.nanoTime();
        System.out.println("StringBuffer time: " + (end - start) + " ns");
    }

    public static void performComparisonAnalysis(String[] strings) {
        String str1 = strings[0];
        String str2 = strings[1];

        output.setLength(0);
        output.append("Reference Equality (==): ").append(str1 == str2).append("\n");
        output.append("Content Equality (equals): ").append(str1.equals(str2)).append("\n");
        output.append("Case-Insensitive Equality: ").append(str1.equalsIgnoreCase(str2)).append("\n");
        output.append("Lexicographic Comparison: ").append(str1.compareTo(str2)).append("\n");
        output.append("Case-Insensitive Lexicographic Comparison: ").append(str1.compareToIgnoreCase(str2)).append("\n");

        int distance = levenshtein(str1, str2);
        int maxLen = Math.max(str1.length(), str2.length());
        double similarity = maxLen == 0 ? 100.0 : ((1.0 - (double) distance / maxLen) * 100);
        output.append("Similarity: ").append(String.format("%.2f", similarity)).append("%\n");

        displayResults();
    }

    public static void performCustomAlgorithms(String text) {
        output.setLength(0);
        String cleaned = text.replaceAll("\\s+", "").toLowerCase();
        String reversed = new StringBuilder(cleaned).reverse().toString();

        output.append("Palindrome: ").append(cleaned.equals(reversed)).append("\n");

        char[] chars = cleaned.toCharArray();
        Arrays.sort(chars);
        output.append("Sorted Characters: ").append(new String(chars)).append("\n");

        output.append("Pattern 'Java' found at index: ").append(text.indexOf("Java")).append("\n");

        displayResults();
    }

    public static void displayResults() {
        System.out.println("\n--- RESULTS ---");
        System.out.println(output.toString());
    }

    // Levenshtein distance helper
    private static int levenshtein(String a, String b) {
        int[][] dp = new int[a.length() + 1][b.length() + 1];
        for (int i = 0; i <= a.length(); i++) dp[i][0] = i;
        for (int j = 0; j <= b.length(); j++) dp[0][j] = j;

        for (int i = 1; i <= a.length(); i++) {
            for (int j = 1; j <= b.length(); j++) {
                int cost = a.charAt(i - 1) == b.charAt(j - 1) ? 0 : 1;
                dp[i][j] = Math.min(
                    Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1),
                    dp[i - 1][j - 1] + cost
                );
            }
        }
        return dp[a.length()][b.length()];
    }
}
