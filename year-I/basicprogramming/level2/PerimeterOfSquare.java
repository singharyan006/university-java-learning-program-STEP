import java.util.*;

public class PerimeterOfSquare{
    public static void main(String[] args){

        // Using Scanner to take the input 
        Scanner sc = new Scanner ( System.in);

        // Taking the Input
        System.out.print("Enter the side length :");
        int side = sc.nextInt();

        int perimeter = 4 * side; //Calculating the Perimeter

        // Displaying the output
        System.out.printf("The length of the side is %d whose perimeter is %d",side,perimeter);
    }
}