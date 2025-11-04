import java.util.Scanner;

public class ShortestLongestWordFinder {

    public static int findLength(String text) {
        int count = 0;
        try {
            while (true) {
                text.charAt(count);
                count++;
            }
        } catch (IndexOutOfBoundsException e) {
            return count;
        }
    }

    public static String[] manualSplit(String text) {
        int length = findLength(text);
        int spaceCount = 0;

        for (int i = 0; i < length; i++) {
            if (text.charAt(i) == ' ') {
                spaceCount++;
            }
        }

        int[] spaceIndexes = new int[spaceCount + 2];
        int index = 1;

        for (int i = 0; i < length; i++) {
            if (text.charAt(i) == ' ') {
                spaceIndexes[index++] = i;
            }
        }

        spaceIndexes[0] = -1;
        spaceIndexes[spaceCount + 1] = length;

        String[] words = new String[spaceCount + 1];
        for (int i = 0; i < words.length; i++) {
            int start = spaceIndexes[i] + 1;
            int end = spaceIndexes[i + 1];
            StringBuilder word = new StringBuilder();
            for (int j = start; j < end; j++) {
                word.append(text.charAt(j));
            }
            words[i] = word.toString();
        }

        return words;
    }

    public static String[][] buildWordLengthTable(String[] words) {
        String[][] table = new String[words.length][2];
        for (int i = 0; i < words.length; i++) {
            table[i][0] = words[i];
            table[i][1] = String.valueOf(findLength(words[i]));
        }
        return table;
    }

    public static int[] findShortestLongest(String[][] wordTable) {
        int minIndex = 0, maxIndex = 0;
        int minLength = Integer.parseInt(wordTable[0][1]);
        int maxLength = Integer.parseInt(wordTable[0][1]);

        for (int i = 1; i < wordTable.length; i++) {
            int currentLength = Integer.parseInt(wordTable[i][1]);
            if (currentLength < minLength) {
                minLength = currentLength;
                minIndex = i;
            }
            if (currentLength > maxLength) {
                maxLength = currentLength;
                maxIndex = i;
            }
        }

        return new int[]{minIndex, maxIndex};
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a sentence: ");
        String input = scanner.nextLine();

        String[] words = manualSplit(input);
        String[][] wordTable = buildWordLengthTable(words);
        int[] result = findShortestLongest(wordTable);

        System.out.printf("%-15s %-10s%n", "Word", "Length");
        System.out.println("-----------------------------");

        for (int i = 0; i < wordTable.length; i++) {
            String word = wordTable[i][0];
            int length = Integer.parseInt(wordTable[i][1]);
            System.out.printf("%-15s %-10d%n", word, length);
        }

        System.out.println("\nShortest word: " + wordTable[result[0]][0] +
                " (Length: " + wordTable[result[0]][1] + ")");
        System.out.println("Longest word: " + wordTable[result[1]][0] +
                " (Length: " + wordTable[result[1]][1] + ")");
    }
}
