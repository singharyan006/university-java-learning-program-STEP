public class UniversityInnerClassDemo {

    static class University {
        private String universityName = "TechVerse University";

        // Member Inner Class
        class Department {
            private String deptName;

            public Department(String deptName) {
                this.deptName = deptName;
            }

            public void showDetails() {
                System.out.println("University: " + universityName);
                System.out.println("Department: " + deptName);
            }
        }

        // Static Nested Class
        static class ExamCell {
            public static void conductExam(String deptName) {
                System.out.println("Exam scheduled for Department: " + deptName);
            }
        }
    }

    public static void main(String[] args) {
        // Create outer class instance
        UniversityInnerClassDemo.University uni = new UniversityInnerClassDemo.University();

        // Create member inner class instance using outer instance
        University.Department dept = uni.new Department("Computer Science");
        dept.showDetails();

        // Access static nested class method directly
        University.ExamCell.conductExam("Computer Science");
    }
}
