import java.util.*;

public class AgeHeightComparision {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the AGE of Amar, Akbar, Anthony : ");
        float amarAge = sc.nextFloat();
        float akbarAge = sc.nextFloat();
        float anthonyAge = sc.nextFloat();

        System.out.print("Enter the HEIGHTS of Amar, Akbar, Anthony : ");
        float amarHeight = sc.nextFloat();
        float akbarHeight = sc.nextFloat();
        float anthonyHeight = sc.nextFloat();

        if(amarAge < akbarAge && amarAge < anthonyAge){
            System.out.print("Amar is the youngest of all.");
        }
        else if(akbarAge < amarAge && akbarAge < anthonyAge){
            System.out.print("Akbar is the youngest of all.");
        }
        else if(anthonyAge < akbarAge && anthonyAge < amarAge){
            System.out.print("Anthony is the youngest of all.");
        }
        else{
            System.out.print("Invalid Comparision.");
        }

        if(amarHeight > akbarHeight && amarHeight > anthonyHeight){
            System.out.print("Amar is the tallest of all.");
        }
        else if(akbarHeight > amarHeight && akbarHeight > anthonyHeight){
            System.out.print("Akbar is the tallest of all.");
        }
        else if(anthonyHeight > akbarHeight && anthonyHeight > amarHeight){
            System.out.print("Anthony is the tallest of all.");
        }
        else{
            System.out.print("Invalid Comparision.");
        }

        
    }
}           