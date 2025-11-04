import java.util.*;

public class StringManipulation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a sentence with mixed formatting:");
        String input = scanner.nextLine();

        // 1. trim() - Remove extra spaces
        String trimmed = input.trim();
        System.out.println("Trimmed: " + trimmed);

        // 2. replace() - Replace all spaces with underscores
        String underscored = trimmed.replace(" ", "_");
        System.out.println("Spaces replaced with underscores: " + underscored);

        // 3. replaceAll() - Remove all digits using regex
        String noDigits = trimmed.replaceAll("\\d", "");
        System.out.println("Removed digits: " + noDigits);

        // 4. split() - Split sentence into words array
        String[] words = trimmed.split("\\s+");
        System.out.println("Words array: " + Arrays.toString(words));

        // 5. join() - Rejoin words with " | " separator
        String joined = String.join(" | ", words);
        System.out.println("Joined with '|': " + joined);

        // Additional processing
        String noPunct = removePunctuation(trimmed);
        System.out.println("Without punctuation: " + noPunct);

        String capitalized = capitalizeWords(noPunct);
        System.out.println("Capitalized words: " + capitalized);

        String reversed = reverseWordOrder(noPunct);
        System.out.println("Reversed word order: " + reversed);

        System.out.println("Word frequency:");
        countWordFrequency(noPunct);

        scanner.close();
    }

    // Method to remove punctuation
    public static String removePunctuation(String text) {
        return text.replaceAll("[\\p{Punct}]", "");
    }

    // Method to capitalize each word
    public static String capitalizeWords(String text) {
        String[] words = text.split("\\s+");
        StringBuilder result = new StringBuilder();
        for (String word : words) {
            if (!word.isEmpty()) {
                result.append(Character.toUpperCase(word.charAt(0)))
                      .append(word.substring(1).toLowerCase())
                      .append(" ");
            }
        }
        return result.toString().trim();
    }

    // Method to reverse word order
    public static String reverseWordOrder(String text) {
        String[] words = text.split("\\s+");
        Collections.reverse(Arrays.asList(words));
        return String.join(" ", words);
    }

    // Method to count word frequency
    public static void countWordFrequency(String text) {
        String[] words = text.toLowerCase().split("\\s+");
        Map<String, Integer> frequencyMap = new HashMap<>();
        for (String word : words) {
            frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
        }
        for (Map.Entry<String, Integer> entry : frequencyMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
