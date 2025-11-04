// Student Grade Management System 
// Topic: Static vs Instance Members and Data Processing 
// Problem Statement: Create a comprehensive student grade management system for a school. 
// Requirements: 
// ● Create a Student class with attributes: studentId (String), studentName (String), 
// className (String), subjects (String array), marks (double 2D array), gpa (double) 
// ● Include static variables: totalStudents (int), schoolName (String), gradingScale 
// (String array), passPercentage (double) 
// ● Create a Subject class with: subjectCode (String), subjectName (String), credits 
// (int), instructor (String) 
// ● Implement methods: addMarks(String subject, double marks), 
// calculateGPA(), generateReportCard(), checkPromotionEligibility() 
// ● Create static methods: setGradingScale(), 
// calculateClassAverage(Student[] students), 
// getTopPerformers(Student[] students, int count), 
// generateSchoolReport() 
// ● Include grade categorization (A, B, C, D, F) based on percentage ranges 
// ● Create a system to handle multiple classes and generate comparative reports 
// Deliverables: Complete grade management system with statistical analysis and reporting 
// capabilities for multiple students and subjects.



import java.util.*;

public class StudentGradeManagementSystem {

    static class Subject {
        String subjectCode;
        String subjectName;
        int credits;
        String instructor;

        public Subject(String subjectCode, String subjectName, int credits, String instructor) {
            this.subjectCode = subjectCode;
            this.subjectName = subjectName;
            this.credits = credits;
            this.instructor = instructor;
        }
    }

    static class Student {
        private String studentId;
        private String studentName;
        private String className;
        private String[] subjects;
        private double[][] marks; // [subject][marks]
        private double gpa;

        private static int totalStudents = 0;
        private static String schoolName = "Greenfield High";
        private static String[] gradingScale = {"A", "B", "C", "D", "F"};
        private static double passPercentage = 40.0;

        public Student(String studentName, String className, String[] subjects) {
            this.studentName = studentName;
            this.className = className;
            this.subjects = subjects;
            this.studentId = generateStudentId();
            this.marks = new double[subjects.length][];
            totalStudents++;
        }

        public static String generateStudentId() {
            return String.format("S%03d", totalStudents + 1);
        }

        public void addMarks(String subject, double[] subjectMarks) {
            for (int i = 0; i < subjects.length; i++) {
                if (subjects[i].equalsIgnoreCase(subject)) {
                    marks[i] = subjectMarks;
                    return;
                }
            }
            System.out.println("Subject not found for student " + studentName);
        }

        public void calculateGPA() {
            double total = 0;
            int count = 0;
            for (double[] subjectMarks : marks) {
                if (subjectMarks != null) {
                    double avg = Arrays.stream(subjectMarks).average().orElse(0);
                    total += avg;
                    count++;
                }
            }
            gpa = count > 0 ? total / count : 0;
        }

        public void generateReportCard() {
            System.out.println("=== Report Card ===");
            System.out.println("Student ID   : " + studentId);
            System.out.println("Name         : " + studentName);
            System.out.println("Class        : " + className);
            for (int i = 0; i < subjects.length; i++) {
                if (marks[i] != null) {
                    double avg = Arrays.stream(marks[i]).average().orElse(0);
                    String grade = getGrade(avg);
                    System.out.println(subjects[i] + " - Avg: " + String.format("%.2f", avg) + " Grade: " + grade);
                } else {
                    System.out.println(subjects[i] + " - No marks entered");
                }
            }
            System.out.println("GPA          : " + String.format("%.2f", gpa));
            System.out.println("Promotion    : " + (checkPromotionEligibility() ? "Eligible" : "Not Eligible"));
            System.out.println("====================\n");
        }

        public boolean checkPromotionEligibility() {
            for (double[] subjectMarks : marks) {
                if (subjectMarks != null) {
                    double avg = Arrays.stream(subjectMarks).average().orElse(0);
                    if (avg < passPercentage) return false;
                }
            }
            return true;
        }

        private String getGrade(double percentage) {
            if (percentage >= 90) return gradingScale[0]; // A
            else if (percentage >= 75) return gradingScale[1]; // B
            else if (percentage >= 60) return gradingScale[2]; // C
            else if (percentage >= 40) return gradingScale[3]; // D
            else return gradingScale[4]; // F
        }

        public static void setGradingScale(String[] scale) {
            gradingScale = scale;
        }

        public static double calculateClassAverage(Student[] students) {
            double total = 0;
            int count = 0;
            for (Student s : students) {
                s.calculateGPA();
                total += s.gpa;
                count++;
            }
            return count > 0 ? total / count : 0;
        }

        public static Student[] getTopPerformers(Student[] students, int count) {
            Arrays.sort(students, (a, b) -> Double.compare(b.gpa, a.gpa));
            return Arrays.copyOfRange(students, 0, Math.min(count, students.length));
        }

        public static void generateSchoolReport(Student[] students) {
            System.out.println("=== School Report ===");
            System.out.println("School Name       : " + schoolName);
            System.out.println("Total Students    : " + totalStudents);
            System.out.println("Class Average GPA : " + String.format("%.2f", calculateClassAverage(students)));
            Student[] toppers = getTopPerformers(students, 3);
            System.out.println("Top Performers:");
            for (Student s : toppers) {
                System.out.println(s.studentName + " - GPA: " + String.format("%.2f", s.gpa));
            }
            System.out.println("======================\n");
        }
    }

    public static void main(String[] args) {
        String[] subjects = {"Math", "Science", "English"};

        Student s1 = new Student("Alice", "10A", subjects);
        Student s2 = new Student("Bob", "10A", subjects);
        Student s3 = new Student("Charlie", "10A", subjects);

        s1.addMarks("Math", new double[]{85, 90});
        s1.addMarks("Science", new double[]{78, 82});
        s1.addMarks("English", new double[]{88, 91});

        s2.addMarks("Math", new double[]{65, 70});
        s2.addMarks("Science", new double[]{60, 62});
        s2.addMarks("English", new double[]{75, 80});

        s3.addMarks("Math", new double[]{95, 98});
        s3.addMarks("Science", new double[]{92, 94});
        s3.addMarks("English", new double[]{89, 90});

        Student[] students = {s1, s2, s3};

        for (Student s : students) {
            s.calculateGPA();
            s.generateReportCard();
        }

        Student.generateSchoolReport(students);
    }
}
