import java.util.*;

public class SpringSeason{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Taking Inputs 
        System.out.print("Enter the Month : ");// Month count
        int month = sc.nextInt();

        System.out.print("Enter the Day : "); // Day Count
        int day = sc.nextInt();

        if ( month >= 3 && month <= 5 ){ // Checking if month is in range of spring season
            if (month == 3){// for march
                if(day >= 20 && day <= 31){//day range 
                    System.out.print("It's Spring season");
                }
                else{
                    System.out.print("It's NOT Spring season");
                }
            }
            if (month == 4){// for april
                
                if(day >= 1 && day <= 30){ // date range
                    System.out.print("It's Spring season");
                }
                else{
                    System.out.print("It's NOT Spring season");
                }
               
            }
            if (month == 5){// for may
                if(day >= 1 && day <= 31){// day range
                    System.out.print("It's Spring season");
                }
                else{
                    System.out.print("It's NOT Spring season");
                }   
            }
            if (month == 6){// for june
                if(day >= 1 && day <= 20){// day range
                    System.out.print("It's Spring season");
                }
                else{
                    System.out.print("It's NOT Spring season");
                }   
            }
            
        }else{System.out.print("It's NOT Spring season");}
    }
}