import java.util.*;

public class FactorialFor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number : ");
        int num = sc.nextInt();

        int factorial = 1;

        for(int i = num; i > 0; i-- ){
            factorial *= i; 
        }
        System.out.print(factorial);
    }
}