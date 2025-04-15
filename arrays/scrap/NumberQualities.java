import java.util.*;

public class NumberQualities {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] numArray = new int[5];
        for ( int i = 0; i < 5; i++){
            System.out.println("Enter the" +(i+1)+ " number : ");
            int numArray[i] = sc.nextInt();
        }
        for ( int i = 0; i < numArray.length; i++){
            if( numArray[i] > 0 ){
                if( numArray[i] % 2 == 0){
                    System.out.println("Even Number.");
                }else{
                    System.out.println("Odd Number.");
                }
            }else if(numArray[i] < 0){
                System.out.println("Negative");
            }else{
                System.out.println("The number is zero.");
            }
        }
        
        sc.close();
    }
}