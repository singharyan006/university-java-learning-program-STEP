import java.util.*;

public class BonusCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double bonus;

        System.out.print("Enter your Salary amount : ");
        double salary = sc.nextDouble();

        System.out.print("Enter your Years Of Service: ");
        float years = sc.nextFloat();

        if (years > 5.0){
            bonus = 0.05 * salary;
            System.out.print("Your Bonus Salary is : "+bonus);
        }
        else{
            System.out.print("Your Bonus Salary is : 0 ");
        }

        sc.close();
    }
}