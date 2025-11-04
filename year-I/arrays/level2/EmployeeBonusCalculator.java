import java.util.*;

public class EmployeeBonusCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final int EMPLOYEES = 10;
        
        double[] salaries = new double[EMPLOYEES];
        double[] yearsOfService = new double[EMPLOYEES];
        double[] bonuses = new double[EMPLOYEES];
        double[] newSalaries = new double[EMPLOYEES];
        
        double totalOldSalary = 0, totalNewSalary = 0, totalBonus = 0;

        for (int i = 0; i < EMPLOYEES; i++) {
            System.out.println("Enter details for Employee " + (i + 1) + ":");
            
            while (true) {
                System.out.print("Enter salary: ");
                if (sc.hasNextDouble()) {
                    salaries[i] = sc.nextDouble();
                    if (salaries[i] > 0) break;
                } else {
                    sc.next(); 
                }
                System.out.println("Invalid input! Salary must be a positive number. Try again.");
            }
            
            while (true) {
                System.out.print("Enter years of service: ");
                if (sc.hasNextDouble()) {
                    yearsOfService[i] = sc.nextDouble();
                    if (yearsOfService[i] >= 0) break;
                } else {
                    sc.next(); 
                }
                System.out.println("Invalid input! Years of service must be a non-negative number. Try again.");
            }
        }
  
        for (int i = 0; i < EMPLOYEES; i++) {
            if (yearsOfService[i] > 5) {
                bonuses[i] = salaries[i] * 0.05;
            } else {
                bonuses[i] = salaries[i] * 0.02;
            }
            
            newSalaries[i] = salaries[i] + bonuses[i];
            totalOldSalary += salaries[i];
            totalNewSalary += newSalaries[i];
            totalBonus += bonuses[i];
        }
        
        System.out.println("\nEmployee Details:");
        System.out.printf("%-10s %-15s %-10s %-15s%n", "Employee", "Old Salary", "Bonus", "New Salary");
        for (int i = 0; i < EMPLOYEES; i++) {
            System.out.printf("%-10d %-15.2f %-10.2f %-15.2f%n", (i + 1), salaries[i], bonuses[i], newSalaries[i]);
        }
        
        System.out.println("\nTotal Old Salary: " + totalOldSalary);
        System.out.println("Total Bonus Payout: " + totalBonus);
        System.out.println("Total New Salary: " + totalNewSalary);
        
        sc.close();
    }
}