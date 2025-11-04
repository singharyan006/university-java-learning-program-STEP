import java.util.*;

public class StudentGrades1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter the number of students: ");
        int n = sc.nextInt();
        
        int[][] marks = new int[n][3];
        double[] percentages = new double[n];
        String[] grades = new String[n];
        
        for (int i = 0; i < n; i++) {
            System.out.println("Enter marks for student " + (i + 1) + ":");
            
            for (int j = 0; j < 3; j++) {
                String subject = (j == 0) ? "Physics" : (j == 1) ? "Chemistry" : "Maths";
                do {
                    System.out.print(subject + " marks: ");
                    marks[i][j] = sc.nextInt();
                    if (marks[i][j] < 0) {
                        System.out.println("Please enter a positive value.");
                    }
                } while (marks[i][j] < 0);
            }
            
            int totalMarks = marks[i][0] + marks[i][1] + marks[i][2];
            percentages[i] = (totalMarks / 3.0);
            
            if (percentages[i] >= 90) grades[i] = "A";
            else if (percentages[i] >= 80) grades[i] = "B";
            else if (percentages[i] >= 70) grades[i] = "C";
            else if (percentages[i] >= 60) grades[i] = "D";
            else grades[i] = "F";
        }
        
        System.out.println("\nPhysics | Chemistry | Maths | Percentage | Grade");
        System.out.println("---------------------------------------------------");
        for (int i = 0; i < n; i++) {
            System.out.printf("%7d | %9d | %5d | %10.2f | %s\n", marks[i][0], marks[i][1], marks[i][2], percentages[i], grades[i]);
        }
        
        sc.close();
    }
}