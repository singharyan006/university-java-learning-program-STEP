import java.util.*;

public class HeightCalculator{
    public static void main(String[] args){
        //Using Scanner to  accept the values from the user
        Scanner sc = new Scanner(System.in);

        //declaring the variables with values 
        int foot = 12; double inch = 2.54;

        //Promting the user to feed the foot part of the height 
        System.out.print("Enter your Height (only foot) :");
        int foots = sc.nextInt();

        //Promting the user to feed the inch part of the height 
        System.out.print("Enter your Height (only inches) :");
        int inches = sc.nextInt();

        //Converting the heihgt from foots and inches to centimeteres
        double height = ((foots * foot) * inch) + (inches * inch);

        //Printing the converted height 
        System.out.print("Your Height in cm would be :"+height);
    }
}