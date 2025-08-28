//  Employee Payroll and Attendance System 
// Topic: Complex Business Logic with Multiple Object Types 
// Problem Statement: Create an integrated employee management system handling payroll, 
// attendance, and performance tracking. 
// Requirements: 
// ● Create an Employee class with: empId (String), empName (String), department 
// (String), designation (String), baseSalary (double), joinDate (String), 
// attendanceRecord (boolean array for 30 days) 
// ● Create a Department class with: deptId (String), deptName (String), manager 
// (Employee), employees (Employee array), budget (double) 
// ● Include static variables: totalEmployees (int), companyName (String), 
// totalSalaryExpense (double), workingDaysPerMonth (int) 
// ● Implement methods: markAttendance(int day, boolean present), 
// calculateSalary(), calculateBonus(), generatePaySlip(), 
// requestLeave() 
// ● Create different employee types with different salary calculation methods (Full-time, 
// Part-time, Contract) 
// ● Include static methods: calculateCompanyPayroll(), 
// getDepartmentWiseExpenses(), getAttendanceReport() 
// ● Implement performance-based bonus calculation and leave management 
// Deliverables: Complete HR management system with payroll processing, attendance tracking, 
// and performance evaluation capabilities. 


import java.util.*;

public class EmployeePayrollAttendanceSystem {

    static class Employee {
        String empId, empName, department, designation, joinDate;
        double baseSalary;
        boolean[] attendanceRecord;
        String empType;

        static int totalEmployees = 0;
        static String companyName = "TechNova Solutions";
        static double totalSalaryExpense = 0.0;
        static int workingDaysPerMonth = 30;

        public Employee(String empName, String department, String designation, double baseSalary, String joinDate, String empType) {
            this.empId = generateEmpId();
            this.empName = empName;
            this.department = department;
            this.designation = designation;
            this.baseSalary = baseSalary;
            this.joinDate = joinDate;
            this.empType = empType;
            this.attendanceRecord = new boolean[workingDaysPerMonth];
            totalEmployees++;
        }

        public static String generateEmpId() {
            return "EMP" + (totalEmployees + 1);
        }

        public void markAttendance(int day, boolean present) {
            if (day >= 1 && day <= workingDaysPerMonth) {
                attendanceRecord[day - 1] = present;
            }
        }

        public double calculateSalary() {
            int presentDays = 0;
            for (boolean present : attendanceRecord) {
                if (present) presentDays++;
            }

            double salary = 0;
            switch (empType) {
                case "Full-time":
                    salary = baseSalary;
                    break;
                case "Part-time":
                    salary = (baseSalary / workingDaysPerMonth) * presentDays;
                    break;
                case "Contract":
                    salary = baseSalary; // fixed
                    break;
            }

            totalSalaryExpense += salary;
            return salary;
        }

        public double calculateBonus() {
            int presentDays = 0;
            for (boolean present : attendanceRecord) {
                if (present) presentDays++;
            }
            double attendanceRate = (double) presentDays / workingDaysPerMonth;
            return attendanceRate >= 0.9 ? 0.10 * baseSalary : 0.0;
        }

        public void generatePaySlip() {
            double salary = calculateSalary();
            double bonus = calculateBonus();
            System.out.println("=== Pay Slip ===");
            System.out.println("Employee ID   : " + empId);
            System.out.println("Name          : " + empName);
            System.out.println("Department    : " + department);
            System.out.println("Designation   : " + designation);
            System.out.println("Type          : " + empType);
            System.out.println("Base Salary   : ₹" + baseSalary);
            System.out.println("Bonus         : ₹" + bonus);
            System.out.println("Total Pay     : ₹" + (salary + bonus));
            System.out.println("================\n");
        }

        public void requestLeave(int day) {
            if (day >= 1 && day <= workingDaysPerMonth) {
                attendanceRecord[day - 1] = false;
                System.out.println(empName + " requested leave on day " + day);
            }
        }

        public static double calculateCompanyPayroll(Employee[] employees) {
            totalSalaryExpense = 0;
            for (Employee e : employees) {
                e.calculateSalary();
            }
            return totalSalaryExpense;
        }

        public static void getAttendanceReport(Employee[] employees) {
            System.out.println("=== Attendance Report ===");
            for (Employee e : employees) {
                int presentDays = 0;
                for (boolean present : e.attendanceRecord) {
                    if (present) presentDays++;
                }
                System.out.println(e.empName + " - Present Days: " + presentDays);
            }
            System.out.println("==========================\n");
        }
    }

    static class Department {
        String deptId, deptName;
        Employee manager;
        Employee[] employees;
        double budget;

        public Department(String deptName, Employee manager, Employee[] employees, double budget) {
            this.deptId = generateDeptId();
            this.deptName = deptName;
            this.manager = manager;
            this.employees = employees;
            this.budget = budget;
        }

        public static String generateDeptId() {
            return "DPT" + UUID.randomUUID().toString().substring(0, 4).toUpperCase();
        }

        public double getDepartmentWiseExpenses() {
            double expense = 0;
            for (Employee e : employees) {
                expense += e.calculateSalary() + e.calculateBonus();
            }
            return expense;
        }

        public void displayDepartmentInfo() {
            System.out.println("=== Department Info ===");
            System.out.println("Department ID   : " + deptId);
            System.out.println("Name            : " + deptName);
            System.out.println("Manager         : " + manager.empName);
            System.out.println("Budget          : ₹" + budget);
            System.out.println("Employees       : ");
            for (Employee e : employees) {
                System.out.println(" - " + e.empName + " (" + e.empType + ")");
            }
            System.out.println("========================\n");
        }
    }

    public static void main(String[] args) {
        Employee e1 = new Employee("Alice", "Engineering", "Developer", 50000, "2025-01-10", "Full-time");
        Employee e2 = new Employee("Bob", "Engineering", "Intern", 15000, "2025-02-01", "Part-time");
        Employee e3 = new Employee("Charlie", "Engineering", "Consultant", 30000, "2025-03-15", "Contract");

        for (int i = 1; i <= 28; i++) {
            e1.markAttendance(i, true);
            e2.markAttendance(i, i % 2 == 0);
            e3.markAttendance(i, true);
        }

        Department engineering = new Department("Engineering", e1, new Employee[]{e1, e2, e3}, 200000);

        e1.generatePaySlip();
        e2.generatePaySlip();
        e3.generatePaySlip();

        engineering.displayDepartmentInfo();
        System.out.println("Department Expense: ₹" + engineering.getDepartmentWiseExpenses());

        System.out.println("Company Payroll: ₹" + Employee.calculateCompanyPayroll(new Employee[]{e1, e2, e3}));
        Employee.getAttendanceReport(new Employee[]{e1, e2, e3});
    }
}
