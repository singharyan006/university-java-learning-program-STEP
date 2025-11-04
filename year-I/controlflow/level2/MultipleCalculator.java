import java.util.*;

public class MultipleCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int result = 0;
        int count = 1;

        System.out.print("Enter the number :");
        int num = sc.nextInt();

        System.out.println("The multiple of the number given are as follows...");
        do{
            result = num * count;
            System.out.println(result);
            count++;
        }while(result < 100 );
    }
}