// Write a program SpringSeason that takes two int values month and day from the command line and prints “Its a Spring Season” otherwise prints “Not a Spring Season”.
// Hint => Spring Season is from March 20 to June 20. Write a Method to check for Spring season and return a boolean true or false 

import java.util.*;

public class SpringSeasonChecker{
    public static boolean isSpring(int date, int month){
        if(month <= 3 && month >= 6){
            if(month == 3){
                if( date >=20 && date <=31 ){
                    return true;
                }
                else{
                    return false;
                }
            }
            else if(month == 4){
                if( date >=1 && date <=30 ){
                    return true;
                }
                else{
                    return false;
                }
            }
            else{
                if( date >=1 && date <=20 ){
                    return true;
                }
                else{
                    return false;
                }
            }

        }
        else{
            return false;
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the month integer value : ");
        int month = sc.nextInt();

        System.out.print("Enter the date : ");
        int date =sc.nextInt();

        System.out.print(isSpring(month,date));

    }
}