public class StringBuiltInMethods {
    public static void main(String[] args) {
        String sampleText = "  Java Programming is Fun and Challenging!  ";

        // 1. Original length including spaces
        System.out.println("Original Length (with spaces): " + sampleText.length());

        // 2. Trim and show new length
        String trimmedText = sampleText.trim();
        System.out.println("Trimmed Length: " + trimmedText.length());

        // 3. Character at index 5
        System.out.println("Character at index 5: " + sampleText.charAt(5));

        // 4. Extract "Programming"
        String extracted = sampleText.substring(sampleText.indexOf("Programming"), sampleText.indexOf("Programming") + "Programming".length());
        System.out.println("Extracted Substring: " + extracted);

        // 5. Index of "Fun"
        System.out.println("Index of 'Fun': " + sampleText.indexOf("Fun"));

        // 6. Contains "Java"
        System.out.println("Contains 'Java': " + sampleText.contains("Java"));

        // 7. Starts with "Java" after trimming
        System.out.println("Starts with 'Java' (trimmed): " + trimmedText.startsWith("Java"));

        // 8. Ends with '!'
        System.out.println("Ends with '!': " + sampleText.trim().endsWith("!"));

        // 9. Uppercase
        System.out.println("Uppercase: " + sampleText.toUpperCase());

        // 10. Lowercase
        System.out.println("Lowercase: " + sampleText.toLowerCase());

        // Vowel count
        int vowelCount = countVowels(sampleText);
        System.out.println("Vowel Count: " + vowelCount);

        // Occurrences of 'a'
        System.out.print("Occurrences of 'a': ");
        findAllOccurrences(sampleText, 'a');
    }

    // Method to count vowels using charAt()
    public static int countVowels(String text) {
        int count = 0;
        String vowels = "aeiouAEIOU";
        for (int i = 0; i < text.length(); i++) {
            if (vowels.indexOf(text.charAt(i)) != -1) {
                count++;
            }
        }
        return count;
    }

    // Method to find all positions of a character
    public static void findAllOccurrences(String text, char target) {
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == target) {
                System.out.print(i + " ");
            }
        }
        System.out.println(); // for newline
    }
}
