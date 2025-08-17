// Write a program to take user input for 5 numbers and check whether a number is positive or negative. Further for positive numbers check if the number is even or odd. Finally compare the first and last elements of the array and display if they are equal, greater, or less
// Hint => 
// Write a Method to Check whether the number is positive or negative
// Write a Method to check whether the number is even or odd
// Write a Method to compare two numbers and return 1 if number1 > number2 or 0 if both are equal or -1 if number1 < number2 
// In the main program, Loop through the array using the length call the method isPositive() and if positive call method isEven() and print accordingly 
// If the number is negative, print negative. 
// Finally compare the first and last element of the array by calling the method compare() and display if they are equal, greater, or less

import java.util.*;

public class CheckNumber{
    public static String isPositiveNegative(int number) {
        if (number > 0) {
            return "Positive";
        } else if (number < 0) {
            return "Negative";
        } else {
            return "Zero";
        }
    }

    public static String isOddEven(int number){
        if (number % 2 == 0){
            return "Even";
        }else{
            return "Odd";
        }
    }

    public static int compareNumbers(int num1, int num2){
        if(num1>num2){
            return 1;
        }else if(num1==num2){
            return -1;
        }else{
            return 0;
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number count :");
        int count = sc.nextInt();

         int[] arr = new int[count];

        for(int i = 0; i < count; i++){
            System.out.println("Enter the "+(i+1)+"number :");
            arr[i] = sc.nextInt();
        }

        for(int i = 0; i < count; i++){
            if(isPositiveNegative(arr[i]) == "Positive"){
                System.out.println("Number is Positive and "+isOddEven(arr[i]));
            }else{
                System.out.println("Negative Number.");
            }
        }
    }
}