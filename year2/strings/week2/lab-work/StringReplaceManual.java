//  Write a program to find and replace all occurrences of a 
// substring in a text without using the replace() method 
// Hint => 
// a. Take user input using the Scanner nextLine() method for the main text and the substring 
// to find and replace 
// b. Create a method to find all occurrences of the substring using indexOf() method in loop and store the starting positions in an array 
// c. Create a method to replace the substring manually by: 
// ● i. Building a new string character by character using charAt() method 
// ● ii. Skip the characters of the original substring and insert the replacement substring 
// d. Create a method to compare the result with the built-in replace() method and return a boolean 
// e. The main function calls all user-defined methods and displays both results along with the comparison
import java.util.Scanner;

public class StringReplaceManual {
	// Find all occurrences of substring
	public static int[] findOccurrences(String text, String find) {
		int[] positions = new int[text.length()];
		int count = 0, idx = 0;
		while ((idx = text.indexOf(find, idx)) != -1) {
			positions[count++] = idx;
			idx += find.length();
		}
		int[] result = new int[count];
		System.arraycopy(positions, 0, result, 0, count);
		return result;
	}

	// Replace substring manually
	public static String manualReplace(String text, String find, String replace) {
		StringBuilder sb = new StringBuilder();
		int i = 0;
		while (i < text.length()) {
			if (i <= text.length() - find.length() && text.substring(i, i + find.length()).equals(find)) {
				sb.append(replace);
				i += find.length();
			} else {
				sb.append(text.charAt(i));
				i++;
			}
		}
		return sb.toString();
	}

	// Compare with built-in replace
	public static boolean compareWithBuiltIn(String manual, String builtin) {
		return manual.equals(builtin);
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter main text: ");
		String text = sc.nextLine();
		System.out.print("Enter substring to find: ");
		String find = sc.nextLine();
		System.out.print("Enter replacement substring: ");
		String replace = sc.nextLine();

		int[] occurrences = findOccurrences(text, find);
		String manual = manualReplace(text, find, replace);
		String builtin = text.replace(find, replace);
		boolean same = compareWithBuiltIn(manual, builtin);

		System.out.println("\nOccurrences at positions: ");
		for (int pos : occurrences) System.out.print(pos + " ");
		System.out.println("\nManual Replace Result: " + manual);
		System.out.println("Built-in Replace Result: " + builtin);
		System.out.println("Results are same: " + same);
		sc.close();
	}
}