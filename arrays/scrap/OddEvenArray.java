// Create a program to save odd and even numbers into odd and even arrays between 1 to the number entered by the user. Finally, print the odd and even numbers array
// Hint => 
// Get an integer input from the user, assign it to a variable number, and check for Natural Number. If not a natural number then print an error and exit the program
// Create an integer array for even and odd numbers with size = number / 2 + 1
// Create index variables for odd and even numbers and initialize them to zero
// Using a for loop, iterate from 1 to the number, and in each iteration of the loop, save the odd or even number into the corresponding array
// Finally, print the odd and even numbers array using the odd and even index


import java.util.*;

public class OddEvenArray{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the limit :");
        int limit = sc.nextInt();

        int evenArray = new int[limit/5];
        int oddArray = new int[limit/5];

        for (int i = 1; i <= limit; i++){
            
        }
    }
}