// Write a program to convert text between different cases 
// (uppercase, lowercase, title case) using ASCII values without using built-in 
// case conversion methods 
// Hint => 
// a. Take user input using the Scanner nextLine() method 
// b. Create a method to convert a character to uppercase using ASCII values: 
// ● i. Check if the character is a lowercase letter (ASCII 97-122) 
// ● ii. Convert by subtracting 32 from the ASCII value 
// c. Create a method to convert a character to lowercase using ASCII values: 
// ● i. Check if the character is an uppercase letter (ASCII 65-90) 
// ● ii. Convert by adding 32 to the ASCII value 
// d. Create a method for title case conversion: 
// ● i. Convert the first character of each word to uppercase 
// ● ii. Convert all other characters to lowercase 
// e. Create a method to compare results with built-in methods (toUpperCase(), 
// toLowerCase()) 
// f. The main function calls all methods and displays the results in a tabular format
import java.util.Scanner;

public class AsciiCaseConverter {
	// Convert a character to uppercase using ASCII values
	public static char toUpperAscii(char ch) {
		if (ch >= 'a' && ch <= 'z') {
			return (char) (ch - 32);
		}
		return ch;
	}

	// Convert a character to lowercase using ASCII values
	public static char toLowerAscii(char ch) {
		if (ch >= 'A' && ch <= 'Z') {
			return (char) (ch + 32);
		}
		return ch;
	}

	// Convert a string to uppercase using ASCII values
	public static String toUpperAscii(String str) {
		StringBuilder sb = new StringBuilder();
		for (char ch : str.toCharArray()) {
			sb.append(toUpperAscii(ch));
		}
		return sb.toString();
	}

	// Convert a string to lowercase using ASCII values
	public static String toLowerAscii(String str) {
		StringBuilder sb = new StringBuilder();
		for (char ch : str.toCharArray()) {
			sb.append(toLowerAscii(ch));
		}
		return sb.toString();
	}

	// Convert a string to title case using ASCII values
	public static String toTitleCaseAscii(String str) {
		StringBuilder sb = new StringBuilder();
		boolean newWord = true;
		for (char ch : str.toCharArray()) {
			if (Character.isWhitespace(ch)) {
				sb.append(ch);
				newWord = true;
			} else {
				if (newWord) {
					sb.append(toUpperAscii(ch));
					newWord = false;
				} else {
					sb.append(toLowerAscii(ch));
				}
			}
		}
		return sb.toString();
	}

	// Compare results with built-in methods
	public static void compareWithBuiltIn(String str) {
		System.out.printf("%-20s %-20s %-20s %-20s %-20s\n", "Original", "ASCII Upper", "Built-in Upper", "ASCII Lower", "Built-in Lower");
		System.out.printf("%-20s %-20s %-20s %-20s %-20s\n", str, toUpperAscii(str), str.toUpperCase(), toLowerAscii(str), str.toLowerCase());
		System.out.printf("%-20s %-20s\n", "ASCII Title Case", "Built-in Title Case");
		System.out.printf("%-20s %-20s\n", toTitleCaseAscii(str), toTitleCaseBuiltIn(str));
	}

	// Built-in title case (for comparison)
	public static String toTitleCaseBuiltIn(String str) {
		String[] words = str.split("\\s+");
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < words.length; i++) {
			if (words[i].length() > 0) {
				sb.append(Character.toUpperCase(words[i].charAt(0)));
				sb.append(words[i].substring(1).toLowerCase());
			}
			if (i < words.length - 1) sb.append(" ");
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter a string: ");
		String input = sc.nextLine();
		System.out.println("\nResults:\n");
		compareWithBuiltIn(input);
		sc.close();
	}
}