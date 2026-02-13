// Write a program to create an Employee payroll system with different 
// employee types using method overloading 
// Hint => 
// a. Create an Employee class with private variables: empId (String), empName (String), 
// department (String), baseSalary (double), empType (String), and static variable 
// totalEmployees (int) 
// b. Create multiple constructors for different employee types: constructor for full-time 
// employees, constructor for part-time employees, constructor for contract employees 
// c. Create overloaded methods calculateSalary(): one for full-time (base salary + 
// bonus), one for part-time (hourly rate × hours), one for contract (fixed amount) 
// d. Create overloaded methods calculateTax() with different tax rates for different 
// employee types 
// e. Create methods generatePaySlip() to display employee details with calculated 
// salary and tax, displayEmployeeInfo() for formatted output 
// f. Create static methods to track total employees and generate company-wide payroll 
// reports 
// g. In main, create different types of employee objects, demonstrate method overloading 
// by calling the same method names with different parameters

public class EmployeePayrollSystem {

    static class Employee {
        private String empId;
        private String empName;
        private String department;
        private double baseSalary;
        private String empType;
        private static int totalEmployees = 0;

        // Constructor for full-time employee
        public Employee(String empName, String department, double baseSalary) {
            this.empName = empName;
            this.department = department;
            this.baseSalary = baseSalary;
            this.empType = "Full-Time";
            this.empId = generateEmpId();
            totalEmployees++;
        }

        // Constructor for part-time employee
        public Employee(String empName, String department, double hourlyRate, int hoursWorked) {
            this.empName = empName;
            this.department = department;
            this.baseSalary = hourlyRate * hoursWorked;
            this.empType = "Part-Time";
            this.empId = generateEmpId();
            totalEmployees++;
        }

        // Constructor for contract employee
        public Employee(String empName, String department, double fixedAmount, boolean isContract) {
            this.empName = empName;
            this.department = department;
            this.baseSalary = fixedAmount;
            this.empType = "Contract";
            this.empId = generateEmpId();
            totalEmployees++;
        }

        // Static method to generate unique employee ID
        public static String generateEmpId() {
            return String.format("EMP%03d", totalEmployees + 1);
        }

        // Overloaded calculateSalary methods
        public double calculateSalary() {
            if (empType.equals("Full-Time")) {
                double bonus = 0.10 * baseSalary;
                return baseSalary + bonus;
            } else {
                return baseSalary; // For Part-Time and Contract
            }
        }

        // Overloaded calculateTax methods
        public double calculateTax() {
            switch (empType) {
                case "Full-Time":
                    return 0.20 * calculateSalary();
                case "Part-Time":
                    return 0.10 * calculateSalary();
                case "Contract":
                    return 0.05 * calculateSalary();
                default:
                    return 0.0;
            }
        }

        // Generate payslip
        public void generatePaySlip() {
            double salary = calculateSalary();
            double tax = calculateTax();
            System.out.println("----- Pay Slip -----");
            System.out.println("Employee ID   : " + empId);
            System.out.println("Name          : " + empName);
            System.out.println("Department    : " + department);
            System.out.println("Type          : " + empType);
            System.out.println("Gross Salary  : ₹" + String.format("%.2f", salary));
            System.out.println("Tax Deducted  : ₹" + String.format("%.2f", tax));
            System.out.println("Net Salary    : ₹" + String.format("%.2f", salary - tax));
            System.out.println("--------------------\n");
        }

        // Display employee info
        public void displayEmployeeInfo() {
            System.out.println("Employee ID   : " + empId);
            System.out.println("Name          : " + empName);
            System.out.println("Department    : " + department);
            System.out.println("Type          : " + empType);
            System.out.println("Base Salary   : ₹" + String.format("%.2f", baseSalary));
            System.out.println("--------------------");
        }

        // Static method to get total employees
        public static int getTotalEmployees() {
            return totalEmployees;
        }

        // Static method to generate payroll report
        public static void generatePayrollReport(Employee[] employees) {
            System.out.println("=== Company Payroll Report ===");
            double totalPayroll = 0;
            for (Employee emp : employees) {
                totalPayroll += emp.calculateSalary();
            }
            System.out.println("Total Employees : " + getTotalEmployees());
            System.out.println("Total Payroll   : ₹" + String.format("%.2f", totalPayroll));
            System.out.println("==============================\n");
        }
    }

    public static void main(String[] args) {
        // Create employee objects
        Employee[] employees = new Employee[3];
        employees[0] = new Employee("Alice", "Engineering", 50000); // Full-Time
        employees[1] = new Employee("Bob", "Support", 300, 40);     // Part-Time
        employees[2] = new Employee("Charlie", "Consulting", 45000, true); // Contract

        // Display info and generate payslips
        for (Employee emp : employees) {
            emp.displayEmployeeInfo();
            emp.generatePaySlip();
        }

        // Generate company-wide payroll report
        Employee.generatePayrollReport(employees);
    }
}
