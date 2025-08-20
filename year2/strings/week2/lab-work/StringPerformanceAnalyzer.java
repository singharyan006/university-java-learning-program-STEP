//  Write a program to analyze and compare the performance of 
// String concatenation vs StringBuilder vs StringBuffer for building large 
// strings 
// Hint => 
// a. Take user input for the number of iterations (e.g., 1000, 10000, 100000) 
// b. Create a method to perform String concatenation in a loop: 
// ● i. Use System.currentTimeMillis() to measure start and end time 
// 2 
// ● ii. Concatenate a sample string multiple times using the + operator 
// ● iii. Return the time taken and final string length 
// c. Create a method to perform StringBuilder operations: 
// ● i. Use StringBuilder.append() method in a loop 
// ● ii. Measure the time taken and return results 
// d. Create a method to perform StringBuffer operations: 
// ● i. Use StringBuffer.append() method in a loop 
// ● ii. Measure the time taken and return results 
// e. Create a method to display performance comparison in a tabular format showing: 
// ● i. Method used, Time taken (milliseconds), Memory efficiency 
// f. The main function calls all methods and displays the performance analysis
import java.util.Scanner;

public class StringPerformanceAnalyzer {
	static class Result {
		String method;
		long timeMs;
		int length;
		Result(String method, long timeMs, int length) {
			this.method = method;
			this.timeMs = timeMs;
			this.length = length;
		}
	}

	// String concatenation
	public static Result testStringConcat(int iterations) {
		long start = System.currentTimeMillis();
		String s = "";
		for (int i = 0; i < iterations; i++) {
			s += "abc";
		}
		long end = System.currentTimeMillis();
		return new Result("String (+)", end - start, s.length());
	}

	// StringBuilder
	public static Result testStringBuilder(int iterations) {
		long start = System.currentTimeMillis();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < iterations; i++) {
			sb.append("abc");
		}
		long end = System.currentTimeMillis();
		return new Result("StringBuilder", end - start, sb.length());
	}

	// StringBuffer
	public static Result testStringBuffer(int iterations) {
		long start = System.currentTimeMillis();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < iterations; i++) {
			sb.append("abc");
		}
		long end = System.currentTimeMillis();
		return new Result("StringBuffer", end - start, sb.length());
	}

	// Display performance comparison
	public static void displayResults(Result... results) {
		System.out.printf("%-15s %-20s %-20s\n", "Method", "Time (ms)", "Final Length");
		for (Result r : results) {
			System.out.printf("%-15s %-20d %-20d\n", r.method, r.timeMs, r.length);
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter number of iterations: ");
		int iterations = sc.nextInt();
		Result r1 = testStringConcat(iterations);
		Result r2 = testStringBuilder(iterations);
		Result r3 = testStringBuffer(iterations);
		System.out.println("\nPerformance Analysis:");
		displayResults(r1, r2, r3);
		sc.close();
	}
}

