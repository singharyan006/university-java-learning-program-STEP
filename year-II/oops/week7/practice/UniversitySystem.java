public class UniversitySystem {
    public static void main(String[] args) {
        System.out.println("University System Upcasting Demo\n");

        Student alice = new Student("Alice", 20, "alice@uni.edu", "CS2021", "Computer Science");

        Person p = alice;

        p.introduce();
        p.getContactInfo();

        System.out.println();

        System.out.println("Accessing inherited field 'name' via Person reference: " + p.name);

        System.out.println();
        System.out.println("Explanation: Upcasting a subclass instance to a superclass reference is safe because the object still contains all subclass data. However, the compiler only allows calls to methods declared in the reference type (Person). To access subclass-specific methods, you must downcast back to the subclass.");

        System.out.println();

        if (p instanceof Student) {
            Student s = (Student) p;
            s.attendLecture();
            s.submitAssignment();
        }

        System.out.println();

        Person profRef = new Professor("Dr. Smith", 45, "smith@uni.edu", "Computer Science");
        profRef.introduce();
        profRef.getContactInfo();

        if (profRef instanceof Professor) {
            ((Professor) profRef).conductClass();
        }
    }
}

class Person {
    protected String name;
    protected int age;
    protected String email;

    public Person(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public void introduce() {
        System.out.println("Hi! I'm " + name + ", " + age + " years old.");
    }

    public void getContactInfo() {
        System.out.println("Email: " + email);
    }
}

class Student extends Person {
    private String studentId;
    private String major;

    public Student(String name, int age, String email, String studentId, String major) {
        super(name, age, email);
        this.studentId = studentId;
        this.major = major;
    }

    public void attendLecture() {
        System.out.println(name + " is attending " + major + " lecture");
    }

    public void submitAssignment() {
        System.out.println("Assignment submitted by " + studentId);
    }
}

class Professor extends Person {
    private String department;

    public Professor(String name, int age, String email, String department) {
        super(name, age, email);
        this.department = department;
    }

    public void conductClass() {
        System.out.println("Prof. " + name + " is teaching " + department);
    }
}
