public class EmployeeInfoPrinter {

    static class Employee {
        private int id;
        private String name;
        private double salary;

        public Employee(int id, String name, double salary) {
            this.id = id;
            this.name = name;
            this.salary = salary;
        }

        @Override
        public String toString() {
            return "Employee{id=" + id + ", name='" + name + "', salary=" + salary + "}";
        }
    }

    public static void main(String[] args) {
        Employee emp1 = new Employee(101, "Alice", 55000.0);
        Employee emp2 = new Employee(102, "Bob", 62000.0);
        Employee emp3 = new Employee(103, "Charlie", 48000.0);

        Employee[] employees = {emp1, emp2, emp3};

        for (Employee emp : employees) {
            System.out.println("Object Details: " + emp.toString());
            System.out.println("Class Name: " + emp.getClass().getName());
            System.out.println("---------------------------");
        }
    }
}
