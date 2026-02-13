//  Write a program to create a simple encryption and decryption 
// system using ASCII character shifting (Caesar Cipher implementation) 
// Hint => 
// a. Take user input for the text to encrypt and the shift value 
// b. Create a method to encrypt text using ASCII values: 
// ● i. For each character, get its ASCII value using (int) casting 
// ● ii. Shift the ASCII value by the given amount 
// ● iii. Handle wrap-around for alphabetic characters (A-Z, a-z) 
// ● iv. Keep non-alphabetic characters unchanged 
// c. Create a method to decrypt text: 
// ● i. Reverse the shifting process 
// ● ii. Handle negative shifts properly 
// d. Create a method to display ASCII values of characters before and after encryption 
// e. Create a method to validate that decryption returns the original text 
// 3 
// f. The main function takes inputs, calls encryption/decryption methods, and displays: 
// ● i. Original text with ASCII values 
// ● ii. Encrypted text with ASCII values 
// ● iii. Decrypted text with validation result
import java.util.Scanner;

public class CaesarCipherEncryptDecrypt {
	// Encrypt text using Caesar Cipher
	public static String encrypt(String text, int shift) {
		StringBuilder sb = new StringBuilder();
		for (char ch : text.toCharArray()) {
			if (ch >= 'A' && ch <= 'Z') {
				sb.append((char) ('A' + (ch - 'A' + shift + 26) % 26));
			} else if (ch >= 'a' && ch <= 'z') {
				sb.append((char) ('a' + (ch - 'a' + shift + 26) % 26));
			} else {
				sb.append(ch);
			}
		}
		return sb.toString();
	}

	// Decrypt text using Caesar Cipher
	public static String decrypt(String text, int shift) {
		return encrypt(text, -shift);
	}

	// Display ASCII values of characters
	public static void displayAscii(String label, String text) {
		System.out.print(label + ": ");
		for (char ch : text.toCharArray()) {
			System.out.print(ch + "(" + (int)ch + ") ");
		}
		System.out.println();
	}

	// Validate decryption
	public static boolean validate(String original, String decrypted) {
		return original.equals(decrypted);
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter text to encrypt: ");
		String input = sc.nextLine();
		System.out.print("Enter shift value: ");
		int shift = sc.nextInt();
		sc.nextLine();

		String encrypted = encrypt(input, shift);
		String decrypted = decrypt(encrypted, shift);

		System.out.println("\nResults:");
		displayAscii("Original", input);
		displayAscii("Encrypted", encrypted);
		displayAscii("Decrypted", decrypted);
		System.out.println("Decryption valid: " + validate(input, decrypted));
		sc.close();
	}
}

