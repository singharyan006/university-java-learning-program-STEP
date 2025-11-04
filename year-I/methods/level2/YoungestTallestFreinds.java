// Create a program to find the youngest friends among 3 Amar, Akbar and Anthony based on their ages and tallest among the friends based on their heights and display it
// Hint => 
// Take user input for age and height for the 3 friends and store it in two arrays each to store the values for age and height of the 3 friends
// Write a Method to find the youngest of the 3 friends
// Write a Method to find the tallest of the 3 friends

import java.util.*;

public class YoungestTallestFriends {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Arrays to store ages and heights of 3 friends
        int[] ages = new int[3];
        double[] heights = new double[3];
        
        // Input for ages and heights
        for (int i = 0; i < 3; i++) {
            System.out.print("Enter age of friend " + (i + 1) + ": ");
            ages[i] = scanner.nextInt();
            while (ages[i] <= 0) {
                System.out.print("Invalid age. Please enter a positive value for age of friend " + (i + 1) + ": ");
                ages[i] = scanner.nextInt();
            }

            System.out.print("Enter height of friend " + (i + 1) + " (in meters): ");
            heights[i] = scanner.nextDouble();
            while (heights[i] <= 0) {
                System.out.print("Invalid height. Please enter a positive value for height of friend " + (i + 1) + ": ");
                heights[i] = scanner.nextDouble();
            }
        }
        
        // Find the youngest friend
        int youngestIndex = findYoungest(ages);
        System.out.println("The youngest friend is Friend " + (youngestIndex + 1) + " with age: " + ages[youngestIndex] + " years.");
        
        // Find the tallest friend
        int tallestIndex = findTallest(heights);
        System.out.println("The tallest friend is Friend " + (tallestIndex + 1) + " with height: " + heights[tallestIndex] + " meters.");
        
        scanner.close(); // Properly close the scanner
    }
    
    // Method to find the index of the youngest friend
    public static int findYoungest(int[] ages) {
        int youngestIndex = 0;
        for (int i = 1; i < ages.length; i++) {
            if (ages[i] < ages[youngestIndex]) {
                youngestIndex = i;
            }
        }
        return youngestIndex;
    }
    
    // Method to find the index of the tallest friend
    public static int findTallest(double[] heights) {
        int tallestIndex = 0;
        for (int i = 1; i < heights.length; i++) {
            if (heights[i] > heights[tallestIndex]) {
                tallestIndex = i;
            }
        }
        return tallestIndex;
    }
}
