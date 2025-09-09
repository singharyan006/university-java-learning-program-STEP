import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.lang.reflect.Method;

public class EmployeeBean implements Serializable, Comparable<EmployeeBean> {

    private static final long serialVersionUID = 1L;

    // === JavaBean private fields ===
    private String employeeId;
    private String firstName;
    private String lastName;
    private double salary;
    private String department;
    private Date hireDate;
    private boolean active;

    // === Default no-argument constructor ===
    public EmployeeBean() {
    }

    // === Parameterized constructor ===
    public EmployeeBean(String employeeId, String firstName, String lastName, 
                        double salary, String department, Date hireDate, boolean active) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        setSalary(salary); // validation included
        this.department = department;
        this.hireDate = hireDate;
        this.active = active;
    }

    // === Standard getters ===
    public String getEmployeeId() {
        return employeeId;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public double getSalary() {
        return salary;
    }
    public String getDepartment() {
        return department;
    }
    public Date getHireDate() {
        return hireDate;
    }
    public boolean isActive() {
        return active;
    }

    // === Standard setters ===
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setSalary(double salary) {
        if (salary < 0) {
            throw new IllegalArgumentException("Salary must be positive.");
        }
        this.salary = salary;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }
    public void setActive(boolean active) {
        this.active = active;
    }

    // === Computed properties ===
    public String getFullName() {
        return firstName + " " + lastName;
    }

    public int getYearsOfService() {
        if (hireDate == null) return 0;
        Calendar hireCal = Calendar.getInstance();
        hireCal.setTime(hireDate);
        Calendar now = Calendar.getInstance();
        return now.get(Calendar.YEAR) - hireCal.get(Calendar.YEAR);
    }

    public String getFormattedSalary() {
        return NumberFormat.getCurrencyInstance(Locale.US).format(salary);
    }

    // === Derived properties with validation ===
    public void setFullName(String fullName) {
        if (fullName == null || !fullName.contains(" ")) {
            throw new IllegalArgumentException("Full name must contain first and last name.");
        }
        String[] parts = fullName.split(" ", 2);
        this.firstName = parts[0];
        this.lastName = parts[1];
    }

    // === Overrides ===
    @Override
    public String toString() {
        return "EmployeeBean{" +
                "employeeId='" + employeeId + '\'' +
                ", fullName='" + getFullName() + '\'' +
                ", salary=" + getFormattedSalary() +
                ", department='" + department + '\'' +
                ", hireDate=" + hireDate +
                ", active=" + active +
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

    @Override
    public int compareTo(EmployeeBean other) {
        return Double.compare(this.salary, other.salary);
    }

    // === Demo main method ===
    public static void main(String[] args) {
        EmployeeBean emp1 = new EmployeeBean();
        emp1.setEmployeeId("E001");
        emp1.setFullName("Alice Johnson");
        emp1.setSalary(75000);
        emp1.setDepartment("Engineering");
        emp1.setHireDate(new Date(120, 0, 15)); // Jan 15, 2020
        emp1.setActive(true);

        EmployeeBean emp2 = new EmployeeBean("E002", "Bob", "Smith",
                90000, "Finance", new Date(118, 5, 10), true);

        System.out.println(emp1);
        System.out.println(emp2);

        System.out.println("Years of service (emp1): " + emp1.getYearsOfService());
        System.out.println("Full name (emp2): " + emp2.getFullName());
        System.out.println("Formatted salary (emp2): " + emp2.getFormattedSalary());

        System.out.println("\n--- Introspection using JavaBeanProcessor ---");
        JavaBeanProcessor.printAllProperties(emp1);
        JavaBeanProcessor.printAllProperties(emp2);

        System.out.println("\n--- Copying properties from emp1 to emp3 ---");
        EmployeeBean emp3 = new EmployeeBean();
        JavaBeanProcessor.copyProperties(emp1, emp3);
        System.out.println(emp3);
    }
}

// === JavaBean utility class ===
class JavaBeanProcessor {
    public static void printAllProperties(EmployeeBean emp) {
        try {
            Method[] methods = emp.getClass().getMethods();
            for (Method m : methods) {
                if ((m.getName().startsWith("get") || m.getName().startsWith("is"))
                        && m.getParameterCount() == 0) {
                    Object value = m.invoke(emp);
                    String propName = m.getName().startsWith("get") ?
                            m.getName().substring(3) : m.getName().substring(2);
                    System.out.println(propName + " = " + value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void copyProperties(EmployeeBean source, EmployeeBean target) {
        try {
            Method[] methods = source.getClass().getMethods();
            for (Method m : methods) {
                if ((m.getName().startsWith("get") || m.getName().startsWith("is"))
                        && m.getParameterCount() == 0) {
                    String propName = m.getName().startsWith("get") ?
                            m.getName().substring(3) : m.getName().substring(2);
                    try {
                        Method setter = target.getClass().getMethod("set" + propName, m.getReturnType());
                        Object value = m.invoke(source);
                        setter.invoke(target, value);
                    } catch (NoSuchMethodException ignored) {
                        // skip if setter not found (e.g., computed property)
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
