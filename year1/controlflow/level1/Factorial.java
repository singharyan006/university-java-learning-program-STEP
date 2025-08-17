import java.util.*;

public class Factorial {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number : ");
        int num = sc.nextInt();

        int factorial = 1;

        while(num > 0){
            factorial *= num;
            num--; 
        }
        System.out.print(factorial);
    }
}