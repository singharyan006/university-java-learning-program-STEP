// Abstract class Employee
abstract class Employee {
    protected String name;
    protected double salary;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    // Abstract method to be implemented by subclasses
    public abstract double calculateBonus();
}

// Interface Payable
interface Payable {
    void generatePaySlip();
}

// Manager class extending Employee and implementing Payable
class Manager extends Employee implements Payable {
    private String department;

    public Manager(String name, double salary, String department) {
        super(name, salary);
        this.department = department;
    }

    @Override
    public double calculateBonus() {
        // Example: 20% of salary as bonus
        return salary * 0.20;
    }

    @Override
    public void generatePaySlip() {
        System.out.println("Pay Slip for Manager:");
        System.out.println("Name: " + name);
        System.out.println("Department: " + department);
        System.out.println("Salary: ₹" + salary);
        System.out.println("Bonus: ₹" + calculateBonus());
        System.out.println("Total Pay: ₹" + (salary + calculateBonus()));
    }
}

// Main class to test the implementation
public class EmployeePayableDemo {
    public static void main(String[] args) {
        Manager mgr = new Manager("Ravi Kumar", 75000.0, "Finance");
        mgr.generatePaySlip();
    }
}
