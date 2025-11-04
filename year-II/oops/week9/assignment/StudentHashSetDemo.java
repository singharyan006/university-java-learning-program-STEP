import java.util.HashSet;
import java.util.Objects;

public class StudentHashSetDemo {

    static class Student {
        private int rollNo;
        private String name;

        public Student(int rollNo, String name) {
            this.rollNo = rollNo;
            this.name = name;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Student other = (Student) obj;
            return this.rollNo == other.rollNo;
        }

        @Override
        public int hashCode() {
            return Objects.hash(rollNo);
        }

        @Override
        public String toString() {
            return "Student{rollNo=" + rollNo + ", name='" + name + "'}";
        }
    }

    public static void main(String[] args) {
        Student s1 = new Student(301, "Riya");
        Student s2 = new Student(302, "Arjun");
        Student s3 = new Student(301, "Riya"); // same rollNo as s1

        HashSet<Student> studentSet = new HashSet<>();
        studentSet.add(s1);
        studentSet.add(s2);
        studentSet.add(s3); // won't be added due to equals/hashCode match with s1

        System.out.println("HashSet contents:");
        for (Student s : studentSet) {
            System.out.println(s);
        }
    }
}
