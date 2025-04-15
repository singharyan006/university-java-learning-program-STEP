import java.util.Scanner;

public class TotalIncome {
    public static void main(String[] args) {

        // Using scanner to take the inputs under the alias sc.
        Scanner scanner = new Scanner(System.in);

        // Promting the user to give the input
        System.out.print("Enter salary: INR ");
        double salary = scanner.nextDouble();

        System.out.print("Enter bonus: INR ");
        double bonus = scanner.nextDouble();

        // Calculating the Total Salary
        double totalIncome = salary + bonus;
        
        // Displaying the output
        System.out.println("The salary is INR " + salary + " and bonus is INR " + bonus + ". Hence Total Income is INR " + totalIncome + ".");

        scanner.close();
    }
}
