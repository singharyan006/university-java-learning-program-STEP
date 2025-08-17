// Write a program to take 2 numbers and print their quotient and reminder
// Hint => 
// Take user input as integer
// Use division operator (/) for quotient and moduli operator (%) for reminder
// Write Method to find the reminder and the quotient of a number 
// public static int[] findRemainderAndQuotient(int number, int divisor)

import java.util.*;

public class DivisionCalc{
    public static int[] findRemainderAndQuotient(int number, int divisor){
        int[] result = new int[2];
        result[0] = number / divisor; // Quotient
        result[1] = number % divisor; // Remainder
        return result;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the first integer : ");
        int num1 = sc.nextInt();

        System.out.print("Enter the second integer : ");
        int num2 = sc.nextInt();

        int[] result = findRemainderAndQuotient(num1, num2);
        
        System.out.println("Quotient is : " + result[0]);
        System.out.println("Remainder is : " + result[1]);
    }
}