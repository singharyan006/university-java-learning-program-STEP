// Write a program to take user input for the age of all 10 students in a class and check whether the student can vote depending on his/her age is greater or equal to 18.
// Hint => 
// Create a class public class StudentVoteChecker and define a method public boolean canStudentVote(int age) which takes in age as a parameter and returns true or false
// Inside the method firstly validate the age for a negative number, if a negative return is false cannot vote. For valid age check for age is 18 or above return true; else return false;
// In the main function define an array of 10 integer elements, loop through the array by take user input for the student's age, call canStudentVote() and display the result


import java.util.*; // Fixed missing semicolon

public class StudentVoteChecker {
    // Method to check if a student can vote based on age
    public boolean canStudentVote(int age) {
        // Validate the age for negative number
        if (age < 0) {
            System.out.println("Invalid age: Age cannot be negative.");
            return false; // Cannot vote if age is negative
        }
        // Check if age is 18 or above
        return age >= 18;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentVoteChecker checker = new StudentVoteChecker();
        
        // Array to store ages of 10 students
        int[] ages = new int[10];
        
        // Loop to take user input for ages
        for (int i = 0; i < ages.length; i++) {
            System.out.print("Enter the age of student " + (i + 1) + ": ");
            ages[i] = scanner.nextInt();
            
            // Check if the student can vote and display the result
            if (checker.canStudentVote(ages[i])) {
                System.out.println("Student " + (i + 1) + " is eligible to vote.");
            } else {
                System.out.println("Student " + (i + 1) + " is not eligible to vote.");
            }
        }
        
        scanner.close(); // Properly close the scanner
    }
}