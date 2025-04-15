import java.util.*;

public class StudentGrades {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter the number of students: ");
        int n = sc.nextInt();
        
        int[] physics = new int[n];
        int[] chemistry = new int[n];
        int[] maths = new int[n];
        double[] percentages = new double[n];
        String[] grades = new String[n];
        
        for (int i = 0; i < n; i++) {
            System.out.println("Enter marks for student " + (i + 1) + ":");
            
            do {
                System.out.print("Physics marks: ");
                physics[i] = sc.nextInt();
                if (physics[i] < 0) {
                    System.out.println("Please enter a positive value.");
                }
            } while (physics[i] < 0);
            
            do {
                System.out.print("Chemistry marks: ");
                chemistry[i] = sc.nextInt();
                if (chemistry[i] < 0) {
                    System.out.println("Please enter a positive value.");
                }
            } while (chemistry[i] < 0);
            
            do {
                System.out.print("Maths marks: ");
                maths[i] = sc.nextInt();
                if (maths[i] < 0) {
                    System.out.println("Please enter a positive value.");
                }
            } while (maths[i] < 0);
            
            int totalMarks = physics[i] + chemistry[i] + maths[i];
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
            System.out.printf("%7d | %9d | %5d | %10.2f | %s\n", physics[i], chemistry[i], maths[i], percentages[i], grades[i]);
        }
        
        sc.close();
    }
}