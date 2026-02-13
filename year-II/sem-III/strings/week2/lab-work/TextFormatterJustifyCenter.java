// Write a program to create a text formatter that justifies text to a 
// specified width using StringBuilder for efficient string manipulation 
// 4 
// Hint => 
// a. Take user input for the text to format and desired line width 
// b. Create a method to split text into words without using split(): 
// ● i. Use charAt() to identify spaces 
// ● ii. Extract words using substring() method 
// ● iii. Store words in an array 
// c. Create a method using StringBuilder to justify text: 
// ● i. Add words to current line until width limit is reached 
// ● ii. Distribute extra spaces evenly between words 
// ● iii. Handle last line separately (left-aligned only) 
// d. Create a method to center-align text: 
// ● i. Calculate padding needed on both sides 
// ● ii. Use StringBuilder to build centered lines 
// e. Create a method to compare performance: 
// ● i. Implement the same formatting using String concatenation 
// ● ii. Measure time difference using System.nanoTime() 
// f. Create a method to display the formatted text with: 
// ● i. Line numbers 
// ● ii. Character count per line 
// ● iii. Performance comparison results 
// g. The main function calls all methods and displays: 
// ● i. Original text 
// ● ii. Left-justified text 
// ● iii. Center-aligned text 
// ● iv. Performance analysis
import java.util.Scanner;

public class TextFormatterJustifyCenter {
	// Split text into words without using split()
	public static String[] splitWords(String text) {
		java.util.ArrayList<String> words = new java.util.ArrayList<>();
		int start = 0;
		for (int i = 0; i <= text.length(); i++) {
			if (i == text.length() || text.charAt(i) == ' ') {
				if (start < i) words.add(text.substring(start, i));
				start = i + 1;
			}
		}
		return words.toArray(new String[0]);
	}

	// Left-justify text
	public static String[] leftJustify(String[] words, int width) {
		java.util.ArrayList<String> lines = new java.util.ArrayList<>();
		StringBuilder line = new StringBuilder();
		for (String word : words) {
			if (line.length() + word.length() + (line.length() > 0 ? 1 : 0) > width) {
				lines.add(line.toString());
				line = new StringBuilder();
			}
			if (line.length() > 0) line.append(' ');
			line.append(word);
		}
		if (line.length() > 0) lines.add(line.toString());
		return lines.toArray(new String[0]);
	}

	// Center-align text
	public static String[] centerAlign(String[] lines, int width) {
		String[] centered = new String[lines.length];
		for (int i = 0; i < lines.length; i++) {
			int pad = (width - lines[i].length()) / 2;
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < pad; j++) sb.append(' ');
			sb.append(lines[i]);
			centered[i] = sb.toString();
		}
		return centered;
	}

	// Justify text using StringBuilder (distribute spaces)
	public static String[] justifyText(String[] words, int width) {
		java.util.ArrayList<String> lines = new java.util.ArrayList<>();
		int i = 0;
		while (i < words.length) {
			int lineLen = words[i].length();
			int j = i + 1;
			while (j < words.length && lineLen + 1 + words[j].length() <= width) {
				lineLen += 1 + words[j].length();
				j++;
			}
			int gaps = j - i - 1;
			StringBuilder line = new StringBuilder();
			if (gaps == 0 || j == words.length) {
				for (int k = i; k < j; k++) {
					if (k > i) line.append(' ');
					line.append(words[k]);
				}
			} else {
				int totalSpaces = width - (lineLen - gaps);
				int spacePerGap = totalSpaces / gaps;
				int extra = totalSpaces % gaps;
				for (int k = i; k < j; k++) {
					line.append(words[k]);
					if (k < j - 1) {
						for (int s = 0; s < spacePerGap; s++) line.append(' ');
						if (extra-- > 0) line.append(' ');
					}
				}
			}
			lines.add(line.toString());
			i = j;
		}
		return lines.toArray(new String[0]);
	}

	// Performance comparison
	public static long formatWithConcat(String[] words, int width) {
		long start = System.nanoTime();
		String result = "";
		String line = "";
		for (String word : words) {
			if (line.length() + word.length() + (line.length() > 0 ? 1 : 0) > width) {
				result += line + "\n";
				line = "";
			}
			if (line.length() > 0) line += " ";
			line += word;
		}
		if (line.length() > 0) result += line + "\n";
		long end = System.nanoTime();
		return end - start;
	}

	public static void displayFormatted(String[] lines) {
		for (int i = 0; i < lines.length; i++) {
			System.out.printf("Line %d (%d chars): %s\n", i+1, lines[i].length(), lines[i]);
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter text to format: ");
		String text = sc.nextLine();
		System.out.print("Enter desired line width: ");
		int width = sc.nextInt();
		sc.nextLine();

		String[] words = splitWords(text);
		String[] left = leftJustify(words, width);
		String[] justified = justifyText(words, width);
		String[] centered = centerAlign(left, width);

		long sbTime = System.nanoTime();
		justifyText(words, width);
		sbTime = System.nanoTime() - sbTime;
		long concatTime = formatWithConcat(words, width);

		System.out.println("\nOriginal text:\n" + text);
		System.out.println("\nLeft-justified text:");
		displayFormatted(left);
		System.out.println("\nCenter-aligned text:");
		displayFormatted(centered);
		System.out.println("\nJustified text:");
		displayFormatted(justified);
		System.out.println("\nPerformance analysis:");
		System.out.printf("StringBuilder time: %d ns\n", sbTime);
		System.out.printf("String concatenation time: %d ns\n", concatTime);
		sc.close();
	}
}