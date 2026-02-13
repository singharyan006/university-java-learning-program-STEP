// File: EmployeeAuthSystem.java

import java.util.HashSet;

class Employee {
    private String empCode;
    private String name;

    public Employee(String empCode, String name) {
        this.empCode = empCode;
        this.name = name;
    }

    // Override equals(): same empCode means same employee
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Employee other = (Employee) obj;
        return empCode != null && empCode.equals(other.empCode);
    }

    // Override hashCode() based on empCode
    @Override
    public int hashCode() {
        return empCode != null ? empCode.hashCode() : 0;
    }

    // toString() showing both fields
    @Override
    public String toString() {
        return "Employee: " + empCode + ", Name: " + name;
    }
}

public class EmployeeAuthSystem {
    public static void main(String[] args) {
        // 1. Create two employees with same empCode
        Employee e1 = new Employee("BL001", "Ritika");
        Employee e2 = new Employee("BL001", "Ritika S.");

        // 2. Compare using == and equals()
        System.out.println("Using == : " + (e1 == e2)); // false
        System.out.println("Using equals() : " + e1.equals(e2)); // true

        // 3. Test using HashSet
        HashSet<Employee> set = new HashSet<>();
        set.add(e1);
        set.add(e2); // Should not be added if equals/hashCode are correct

        System.out.println("HashSet size: " + set.size()); // Should be 1
        for (Employee emp : set) {
            System.out.println(emp);
        }
    }
}
