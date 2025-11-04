import java.util.*;

public class DivisibilityBy5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter a number : ");
        int num = sc.nextInt();

        if ( num % 5 == 0){
            System.out.printf("Is the number %d divisible by 5? \nYes",num);
        }
        else{
            System.out.printf("Is the number %d divisible by 5? \nNo",num);
        }
    }
}