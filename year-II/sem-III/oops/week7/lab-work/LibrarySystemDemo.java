// PROBLEM 4: University Library System
// Concept: Upcasting
// Design a library system with different types of users:
// ● Students can borrow books and access computers
// ● Faculty can reserve books and access research databases
// ● Guests can only browse books
// Create a general "LibraryUser" system that can handle any user type for common
// operations like entry logging and basic info display.
// Hint: Think bigger picture - store specialists as generalists safely!




public class LibrarySystemDemo {
    public static void main(String[] args) {
        LibraryUser[] users = new LibraryUser[3];
        users[0] = new Student("Alice", "CS2021");
        users[1] = new Faculty("Dr. Brown", "Physics");
        users[2] = new Guest("Visitor");

        for (LibraryUser u : users) {
            u.logEntry();
            u.displayInfo();
            System.out.println();
        }

        // Demonstrate upcasting limitations and downcasting
        LibraryUser lu = users[0]; // upcast Student -> LibraryUser
        // lu.borrowBook(); // compile error if uncommented
        if (lu instanceof Student) {
            ((Student) lu).borrowBook("Introduction to Algorithms");
            ((Student) lu).accessComputer();
        }

        LibraryUser fac = users[1];
        if (fac instanceof Faculty) {
            ((Faculty) fac).reserveBook("Advanced Quantum Mechanics");
            ((Faculty) fac).accessResearchDB();
        }
    }
}

class LibraryUser {
    protected String name;

    public LibraryUser(String name) {
        this.name = name;
    }

    public void logEntry() {
        System.out.println(name + " entered the library.");
    }

    public void displayInfo() {
        System.out.println("User: " + name + " (General library user)");
    }
}

class Student extends LibraryUser {
    private String studentId;

    public Student(String name, String studentId) {
        super(name);
        this.studentId = studentId;
    }

    public void borrowBook(String title) {
        System.out.println(name + " borrowed: " + title + " (ID: " + studentId + ")");
    }

    public void accessComputer() {
        System.out.println(name + " is accessing a public computer.");
    }

    @Override
    public void displayInfo() {
        System.out.println("Student: " + name + " (" + studentId + ")");
    }
}

class Faculty extends LibraryUser {
    private String department;

    public Faculty(String name, String department) {
        super(name);
        this.department = department;
    }

    public void reserveBook(String title) {
        System.out.println(name + " reserved: " + title);
    }

    public void accessResearchDB() {
        System.out.println(name + " is accessing a research database.");
    }

    @Override
    public void displayInfo() {
        System.out.println("Faculty: " + name + " (" + department + ")");
    }
}

class Guest extends LibraryUser {
    public Guest(String name) {
        super(name);
    }

    public void browseBooks() {
        System.out.println(name + " is browsing books.");
    }

    @Override
    public void displayInfo() {
        System.out.println("Guest: " + name + " (limited access)");
    }
}
