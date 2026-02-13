// Write a program to extract and analyze different parts of an 
// email address using substring() and indexOf() methods 
// Hint => 
// a. Take user input for multiple email addresses using Scanner 
// b. Create a method to validate email format: 
// ● i. Check for exactly one '@' symbol using indexOf() and lastIndexOf() 
// ● ii. Check for at least one '.' after '@' symbol 
// ● iii. Validate that username and domain are not empty 
// c. Create a method to extract email components: 
// ● i. Extract username using substring() from start to '@' position 
// ● ii. Extract domain using substring() from '@' position to end 
// ● iii. Extract domain name and extension separately 
// d. Create a method to analyze email statistics: 
// ● i. Count total valid/invalid emails 
// ● ii. Find most common domain 
// ● iii. Calculate average username length 
// e. Create a method to display results in tabular format showing: 
// ● i. Email, Username, Domain, Domain Name, Extension, Valid/Invalid 
// f. The main function processes multiple emails and displays analysis results 
import java.util.*;

public class EmailAddressAnalyzer {
	static class EmailInfo {
		String email, username, domain, domainName, extension;
		boolean valid;
		EmailInfo(String email, String username, String domain, String domainName, String extension, boolean valid) {
			this.email = email;
			this.username = username;
			this.domain = domain;
			this.domainName = domainName;
			this.extension = extension;
			this.valid = valid;
		}
	}

	// Validate email format
	public static boolean isValidEmail(String email) {
		int at = email.indexOf('@');
		int lastAt = email.lastIndexOf('@');
		if (at == -1 || at != lastAt) return false;
		int dot = email.indexOf('.', at);
		if (dot == -1) return false;
		String username = email.substring(0, at);
		String domain = email.substring(at + 1);
		if (username.isEmpty() || domain.isEmpty()) return false;
		return true;
	}

	// Extract email components
	public static EmailInfo extractInfo(String email) {
		boolean valid = isValidEmail(email);
		String username = "", domain = "", domainName = "", extension = "";
		if (valid) {
			int at = email.indexOf('@');
			int dot = email.indexOf('.', at);
			username = email.substring(0, at);
			domain = email.substring(at + 1);
			domainName = domain.substring(0, domain.indexOf('.'));
			extension = domain.substring(domain.indexOf('.') + 1);
		}
		return new EmailInfo(email, username, domain, domainName, extension, valid);
	}

	// Analyze statistics
	public static void analyzeStats(List<EmailInfo> infos) {
		int validCount = 0, invalidCount = 0, totalUsernameLen = 0;
		Map<String, Integer> domainFreq = new HashMap<>();
		for (EmailInfo info : infos) {
			if (info.valid) {
				validCount++;
				totalUsernameLen += info.username.length();
				domainFreq.put(info.domainName, domainFreq.getOrDefault(info.domainName, 0) + 1);
			} else {
				invalidCount++;
			}
		}
		String mostCommonDomain = "";
		int maxFreq = 0;
		for (Map.Entry<String, Integer> entry : domainFreq.entrySet()) {
			if (entry.getValue() > maxFreq) {
				maxFreq = entry.getValue();
				mostCommonDomain = entry.getKey();
			}
		}
		double avgUsernameLen = validCount > 0 ? (double)totalUsernameLen / validCount : 0;
		System.out.println("\nStatistics:");
		System.out.println("Total valid emails: " + validCount);
		System.out.println("Total invalid emails: " + invalidCount);
		System.out.println("Most common domain: " + mostCommonDomain);
		System.out.printf("Average username length: %.2f\n", avgUsernameLen);
	}

	// Display results in tabular format
	public static void displayTable(List<EmailInfo> infos) {
		System.out.printf("%-30s %-15s %-25s %-15s %-10s %-10s\n", "Email", "Username", "Domain", "Domain Name", "Extension", "Valid?");
		for (EmailInfo info : infos) {
			System.out.printf("%-30s %-15s %-25s %-15s %-10s %-10s\n", info.email, info.username, info.domain, info.domainName, info.extension, info.valid ? "Valid" : "Invalid");
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		List<EmailInfo> infos = new ArrayList<>();
		System.out.print("Enter number of email addresses: ");
		int n = sc.nextInt();
		sc.nextLine();
		for (int i = 0; i < n; i++) {
			System.out.print("Enter email " + (i+1) + ": ");
			String email = sc.nextLine();
			infos.add(extractInfo(email));
		}
		displayTable(infos);
		analyzeStats(infos);
		sc.close();
	}
}