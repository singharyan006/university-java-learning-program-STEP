import java.util.*;

public class CountDownFor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the limit : ");
        int count = sc.nextInt();

        // Condition to check if given number is valid 
        if (count < 0 ){
            System.out.print("Invalid input");
        }
        else{
            for ( int i = count; i > 0; i--){
                System.out.println(i);
            }
        }
    }
}