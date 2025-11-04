// Write a program to find the smallest and the largest of the 3 numbers.
// Hint => 
// Take user input for 3 numbers
// Write a single method to find the smallest and largest of the three numbers
// public static int[] findSmallestAndLargest(int number1, int number2, int number3)
import java.util.*;

public class Comparision{
    public static int[] findSmallestAndLargest(int number1, int number2, int number3){
        int[] result = new int[2];
        if(number1 < number2 && number1 < number3){
            result[0] = number1;
        }
        else if(number2 < number1 && number2 < number3){
            result[0] = number2;
        }
        else{
            result[0] = number3;
        }

        if(number1 > number2 && number1 > number3){
            result[1] = number1;
        }
        else if(number2 > number1 && number2 > number3){
            result[1] = number2;
        }
        else{
            result[1] = number3;
        }

        return result;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the first integer : ");
        int num1 = sc.nextInt();

        System.out.print("Enter the second integer : ");
        int num2 = sc.nextInt();

        System.out.print("Enter the third integer : ");
        int num3 = sc.nextInt();

        int[] result = findSmallestAndLargest(num1, num2, num3);
        
        System.out.println("Smallest Number is : " + result[0]);
        System.out.println("Largest Number is : " + result[1]);
    }
}