import java.util.*;

public class CountDownWhile {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Taking inputs
        System.out.print("Enter the limit : ");
        int count = sc.nextInt();

        // Condition to check if given number is valid 
        if (count < 0 ){
            System.out.print("Invalid input");
        }

        // using while to print countdown
        else{
            while (count > 0){
                System.out.println(count);
                count--;
            }
        }
    }
}