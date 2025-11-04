import java.util.HashSet;
import java.util.Objects;

public class StudentHashSetDemo {

    static class Student {
        private int id;
        private String name;

        public Student(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Student other = (Student) obj;
            return this.id == other.id;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }

        @Override
        public String toString() {
            return "Student [ID: " + id + ", Name: " + name + "]";
        }
    }

    public static void main(String[] args) {
        HashSet<Student> students = new HashSet<>();

        Student s1 = new Student(101, "Aarav");
        Student s2 = new Student(102, "Bhavna");
        Student s3 = new Student(101, "Aarav"); // Same ID as s1

        students.add(s1);
        students.add(s2);
        students.add(s3); // Should be ignored due to same ID

        System.out.println("Students in HashSet:");
        for (Student s : students) {
            System.out.println(s);
        }
    }
}
