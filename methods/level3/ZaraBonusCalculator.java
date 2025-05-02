import java.util.Random;

public class ZaraBonusCalculator {

    public static double[][] generateSalaryAndService(int numberOfEmployees) {
        double[][] data = new double[numberOfEmployees][2];
        Random rand = new Random();

        for (int i = 0; i < numberOfEmployees; i++) {
            double salary = 10000 + rand.nextInt(90000); // 5-digit salary
            int years = rand.nextInt(11); // 0 to 10 years of service
            data[i][0] = salary;
            data[i][1] = years;
        }
        return data;
    }

    public static double[][] calculateBonusAndNewSalary(double[][] data) {
        int n = data.length;
        double[][] newData = new double[n][3]; // salary, bonus, new salary

        for (int i = 0; i < n; i++) {
            double salary = data[i][0];
            int years = (int) data[i][1];
            double bonus = (years > 5) ? salary * 0.05 : salary * 0.02;
            double newSalary = salary + bonus;

            newData[i][0] = bonus;
            newData[i][1] = newSalary;
            newData[i][2] = years;
        }

        return newData;
    }

    public static void displaySalaryDetails(double[][] oldData, double[][] newData) {
        double totalOldSalary = 0;
        double totalBonus = 0;
        double totalNewSalary = 0;

        System.out.printf("%-5s %-10s %-15s %-10s %-15s %-15s\n", "ID", "Service", "Old Salary", "Bonus", "New Salary", "Bonus %");
        System.out.println("-------------------------------------------------------------------------------");

        for (int i = 0; i < oldData.length; i++) {
            double salary = oldData[i][0];
            int years = (int) oldData[i][1];
            double bonus = newData[i][0];
            double newSalary = newData[i][1];
            double bonusPercent = (years > 5) ? 5 : 2;

            totalOldSalary += salary;
            totalBonus += bonus;
            totalNewSalary += newSalary;

            System.out.printf("%-5d %-10d %-15.2f %-10.2f %-15.2f %-14.0f%%\n",
                    (i + 1), years, salary, bonus, newSalary, bonusPercent);
        }

        System.out.println("-------------------------------------------------------------------------------");
        System.out.printf("%-16s %-15.2f %-10.2f %-15.2f\n",
                "TOTALS", totalOldSalary, totalBonus, totalNewSalary);
    }

    public static void main(String[] args) {
        int numEmployees = 10;

        double[][] oldData = generateSalaryAndService(numEmployees);
        double[][] newData = calculateBonusAndNewSalary(oldData);
        displaySalaryDetails(oldData, newData);
    }
}
