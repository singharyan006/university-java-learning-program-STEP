import java.util.*;

public class PowerCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number : ");
        int num = sc.nextInt();

        System.out.print("Enter the power : ");
        int power = sc.nextInt();

        int result = 1;

        for (int i = 1; i <= power; i++){
            result *= num;
            System.out.printf("%d to the power %d equals : %d \n",num,i,result);
        }
    }
}