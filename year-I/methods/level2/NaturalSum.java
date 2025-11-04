// Write a program to find the sum of n natural numbers using recursive method and compare the result with the formulae n*(n+1)/2 and show the result from both computations is correct. 
// Hint => 
// Take the user input number and check whether it's a Natural number, if not exit
// Write a Method to find the sum of n natural numbers using recursion
// Write a Method to find the sum of n natural numbers using the formulae n*(n+1)/2 
// Compare the two results and print the result

import java.util.*;

public class NaturalSum{
    public static int naturalSum(int limit){
        if( limit == 1){
            return 1;
        }
        return limit + naturalSum(limit - 1);
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number :");
        int num = sc.nextInt();
        if(num <= 0){
            System.out.print("Non-natural number entered.");
        }

        else{
            int formulaSum = (num * (num + 1))/2;
            int recursiveSum = naturalSum(num);

            System.out.println("Recursive Sum is :"+recursiveSum);
            System.out.println("Sum by formula :"+formulaSum);

            if (formulaSum == recursiveSum){
                System.out.println("\n....Function is working properly.....");
            }else{
                System.out.print("\n....Something is wrong...");
            }
        }
        sc.close(); 
    }
}