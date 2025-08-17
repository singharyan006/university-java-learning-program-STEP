import java.util.*;

public class ScanConverter{
    public static void main(String[] args){

        //Using the scanner to take the input with the alias sc
        Scanner sc = new Scanner( System.in);


        //declaring the varibales
        double km;
        double miles = 1.6;

        //Promting the user to feed  the distance for converting 
        System.out.print("Enter the distance you want to convert : ");
        km = sc.nextInt();// used for taking input as integer


        //Converting the distance from Km to Miles and storing in the output varible of  double datatype
        double output = km * miles; 

        //Printing the result
        System.out.print("Distance in miles would be :"+output);
    }
}