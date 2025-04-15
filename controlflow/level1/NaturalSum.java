import java.util.*;
public class NaturalSum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number : ");
        double num = sc.nextDouble();

        double total = 0;
        int i = 1;
        while(i <= num ){
            total += i;
            i++;
        }
        double formula = (num * (num + 1)) / 2;

        System.out.println("Total By Loop : "+total);
        if ( total == formula ){
            System.out.print("Correct");
        }
        else{
            System.out.print("Incorrect");
        }
		sc.close();
    }
}