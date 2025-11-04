import java.util.*;

public class Converter{
    public static void main(String[] args){
        
        // Using the Scanner to take the input under the alias sc
        Scanner sc = new Scanner(System.in);

        // Initialised varibles for conversion
        double yardFeet = 1.0/3; 
        double yardMiles = 1.0/1760;

        // Prompting the user to give the input
        System.out.print("Enter the Distance in feet :");
        double distanceInFeet = sc.nextDouble();

        // Conversion in yard and miles
        double distanceInYard = yardFeet * distanceInFeet;
        double distanceInMiles = yardMiles * distanceInYard;
        
        // Displaying the ouput
        System.out.print("Distance in yard is "+distanceInYard);
        System.out.print("Distance in Miles is "+distanceInMiles);
    }
        
}