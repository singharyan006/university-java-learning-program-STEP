import java.util.*;

public class SumWhile {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double total = 0, num = 0;

        System.out.print("Enter the number : ");
        num = sc.nextDouble();

        while(num != 0){
            System.out.print("Enter the number : ");
            num = sc.nextDouble();
            total += num;
        }
        
        System.out.println("Exiting!!!");
        System.out.println("Total sum "+total);
    }
}