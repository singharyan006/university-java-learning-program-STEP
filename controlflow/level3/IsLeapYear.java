import java.util.*;

public class IsLeapYear{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter the year : ");
        int year = sc.nextInt();

        if( year >= 1582){
            if( year % 4 == 0 || year % 400 == 0){
                System.out.printf("%d is a leap year.",year);
            }
            else{
                System.out.printf("%d is not a leap year.",year);
            }
        }
        else{
            System.out.print("Invalid Input.");
        }
    }
}