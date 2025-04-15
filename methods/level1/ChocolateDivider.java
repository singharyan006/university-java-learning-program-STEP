// Create a program to divide N number of chocolates among M children. Print the number of chocolates each child will get and also the remaining chocolates
// Hint => 
// Get an integer value from user for the numberOfchocolates and numberOfChildren.
// Write the method to find the number of chocolates each child gets and number of remaining chocolates
// public static int[] findRemainderAndQuotient(int number, int divisor)

import java.util.*;

public class ChocolateDivision{
    public static int[] findRemainderAndQuotient(int numberOfChocolates, int numberOfChildren){
        int[] result = new int[2];
        result[0] = numberOfChocolates / numberOfChildren; // Chocolates per child
        result[1] = numberOfChocolates % numberOfChildren; // Remaining chocolates
        return result;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of chocolates : ");
        int numOfChocolates = sc.nextInt();

        System.out.print("Enter the number of children : ");
        int numOfChildren = sc.nextInt();

        int[] result = findRemainderAndQuotient(numOfChocolates, numOfChildren);
        
        System.out.println("Each child gets : " + result[0] + " chocolates");
        System.out.println("Remaining chocolates : " + result[1]);
    }
}