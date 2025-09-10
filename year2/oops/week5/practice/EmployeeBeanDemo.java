import java.io.Serializable;
import java.text.NumberFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.lang.reflect.Method;

public class EmployeeBeanDemo {

    public static class EmployeeBean implements Serializable {
        private String employeeId;
        private String firstName;
        private String lastName;
        private double salary;
        private String department;
        private Date hireDate;
        private boolean isActive;

        public EmployeeBean() {}

        public EmployeeBean(String employeeId, String firstName, String lastName,
                            double salary, String department, Date hireDate, boolean isActive) {
            this.employeeId = employeeId;
            this.firstName = firstName;
            this.lastName = lastName;
            setSalary(salary);
            this.department = department;
            this.hireDate = hireDate;
            this.isActive = isActive;
        }

        public String getEmployeeId() { return employeeId; }
        public String getFirstName() { return firstName; }
        public String getLastName() { return lastName; }
        public double getSalary() { return salary; }
        public String getDepartment() { return department; }
        public Date getHireDate() { return hireDate; }
        public boolean isActive() { return isActive; }

        public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }
        public void setFirstName(String firstName) { this.firstName = firstName; }
        public void setLastName(String lastName) { this.lastName = lastName; }
        public void setSalary(double salary) {
            if (salary >= 0) {
                this.salary = salary;
            } else {
                throw new IllegalArgumentException("Salary must be non-negative");
            }
        }
        public void setDepartment(String department) { this.department = department; }
        public void setHireDate(Date hireDate) { this.hireDate = hireDate; }
        public void setActive(boolean active) { isActive = active; }

        public String getFullName() {
            return firstName + " " + lastName;
        }

        public int getYearsOfService() {
            if (hireDate == null) return 0;
            long diff = new Date().getTime() - hireDate.getTime();
            return (int) TimeUnit.MILLISECONDS.toDays(diff) / 365;
        }

        public String getFormattedSalary() {
            return NumberFormat.getCurrencyInstance().format(salary);
        }

        public void setFullName(String fullName) {
            String[] parts = fullName.split(" ");
            if (parts.length >= 2) {
                this.firstName = parts[0];
                this.lastName = parts[1];
            } else {
                throw new IllegalArgumentException("Full name must contain at least first and last name");
            }
        }

        @Override
        public String toString() {
            return "EmployeeBean{" +
                    "employeeId='" + employeeId + '\'' +
                    ", fullName='" + getFullName() + '\'' +
                    ", salary=" + getFormattedSalary() +
                    ", department='" + department + '\'' +
                    ", hireDate=" + hireDate +
                    ", yearsOfService=" + getYearsOfService() +
                    ", isActive=" + isActive +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof EmployeeBean)) return false;
            EmployeeBean that = (EmployeeBean) o;
            return Objects.equals(employeeId, that.employeeId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(employeeId);
        }
    }

    public static class JavaBeanProcessor {
        public static void printAllProperties(EmployeeBean emp) {
            Method[] methods = emp.getClass().getMethods();
            for (Method method : methods) {
                if ((method.getName().startsWith("get") || method.getName().startsWith("is"))
                        && method.getParameterCount() == 0) {
                    try {
                        Object value = method.invoke(emp);
                        String propName = method.getName()
                                .replaceFirst("get", "")
                                .replaceFirst("is", "");
                        System.out.println(propName + ": " + value);
                    } catch (Exception e) {
                        System.out.println("Error accessing " + method.getName());
                    }
                }
            }
        }

        public static void copyProperties(EmployeeBean source, EmployeeBean target) {
            Method[] methods = source.getClass().getMethods();
            for (Method getter : methods) {
                if ((getter.getName().startsWith("get") || getter.getName().startsWith("is"))
                        && getter.getParameterCount() == 0) {
                    try {
                        Object value = getter.invoke(source);
                        String propName = getter.getName()
                                .replaceFirst("get", "")
                                .replaceFirst("is", "");
                        String setterName = "set" + propName;
                        for (Method setter : target.getClass().getMethods()) {
                            if (setter.getName().equals(setterName)
                                    && setter.getParameterCount() == 1) {
                                setter.invoke(target, value);
                            }
                        }
                    } catch (Exception ignored) {}
                }
            }
        }
    }

    public static void main(String[] args) {
        EmployeeBean emp1 = new EmployeeBean();
        emp1.setEmployeeId("E001");
        emp1.setFullName("John Doe");
        emp1.setSalary(50000);
        emp1.setDepartment("Engineering");
        emp1.setHireDate(new Date(120, 0, 1)); // Jan 1, 2020
        emp1.setActive(true);

        EmployeeBean emp2 = new EmployeeBean("E002", "Jane", "Smith", 60000,
                "Marketing", new Date(118, 5, 15), true);

        System.out.println("Employee 1:");
        System.out.println(emp1);
        System.out.println("\nEmployee 2:");
        System.out.println(emp2);

        System.out.println("\nSorted by salary:");
        EmployeeBean[] employees = {emp1, emp2};
        Arrays.sort(employees, Comparator.comparingDouble(EmployeeBean::getSalary));
        for (EmployeeBean e : employees) {
            System.out.println(e.getFullName() + " - " + e.getFormattedSalary());
        }

        System.out.println("\nActive employees:");
        Arrays.stream(employees)
                .filter(EmployeeBean::isActive)
                .forEach(System.out::println);

        System.out.println("\nJavaBean Introspection:");
        JavaBeanProcessor.printAllProperties(emp1);

        System.out.println("\nCopying properties from emp1 to emp3:");
        EmployeeBean emp3 = new EmployeeBean();
        JavaBeanProcessor.copyProperties(emp1, emp3);
        System.out.println(emp3);
    }
}