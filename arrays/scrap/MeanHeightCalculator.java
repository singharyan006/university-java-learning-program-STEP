// Create a program to find the mean height of players present in a football team.
// Hint => 
// The formula to calculate the mean is: mean = sum of all elements / number of elements
// Create a double array named heights of size 11 and get input values from the user.
// Find the sum of all the elements present in the array.
// Divide the sum by 11 to find the mean height and print the mean height of the football team

import java.util.*;

public class MeanHeightCalculator{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //Created an array of size 11
        double[] height = new double[11];

        //Initialised a sum varible to hold the sum of heights of the players
        double heightSum = 0.0;

        //Used a for loop for getting inputs from the user in array and also to calucalate the sum of heights given
        for( int i = 0; i < 11; i++){
            System.out.print("Enter the height for player "+(i+1)+" :");
            height[i] = sc.nextDouble();
            heightSum += height[i];
        }

        //Calculating the mean height 
        double meanHeight = heightSum/11;
        System.out.print("The mean height of the players is :"+meanHeight); 
    }
}