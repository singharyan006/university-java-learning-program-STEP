import java.util.Scanner;

public class StudentScorecard {

    // Method to generate random marks for all students
    public static int[][] generateMarks(int studentCount) {
        int[][] marks = new int[studentCount][3];
        for (int i = 0; i < studentCount; i++) {
            for (int j = 0; j < 3; j++) {
                marks[i][j] = (int) (Math.random() * 41 + 60); // Range: 60 to 100
            }
        }
        return marks;
    }

    // Method to calculate total, average and percentage
    public static double[][] calculateScoreStats(int[][] marks) {
        int studentCount = marks.length;
        double[][] stats = new double[studentCount][3]; // total, average, percentage
        for (int i = 0; i < studentCount; i++) {
            int total = marks[i][0] + marks[i][1] + marks[i][2];
            double avg = total / 3.0;
            double percent = (total / 300.0) * 100;

            stats[i][0] = total;
            stats[i][1] = Math.round(avg * 100.0) / 100.0;
            stats[i][2] = Math.round(percent * 100.0) / 100.0;
        }
        return stats;
    }

    // Method to assign grades
    public static String[] assignGrades(double[][] stats) {
        String[] grades = new String[stats.length];
        for (int i = 0; i < stats.length; i++) {
            double percent = stats[i][2];
            if (percent >= 90) grades[i] = "A+";
            else if (percent >= 80) grades[i] = "A";
            else if (percent >= 70) grades[i] = "B";
            else if (percent >= 60) grades[i] = "C";
            else if (percent >= 50) grades[i] = "D";
            else grades[i] = "F";
        }
        return grades;
    }

    // Method to display everything
    public static void displayScorecard(int[][] marks, double[][] stats, String[] grades) {
        System.out.printf("%-10s %-10s %-10s %-10s %-10s %-10s %-12s %-8s%n",
                "Student", "Physics", "Chemistry", "Maths", "Total", "Average", "Percentage", "Grade");
        System.out.println("-----------------------------------------------------------------------------------------");

        for (int i = 0; i < marks.length; i++) {
            System.out.printf("Student%-3d %-10d %-10d %-10d %-10.0f %-10.2f %-12.2f %-8s%n",
                    (i + 1), marks[i][0], marks[i][1], marks[i][2],
                    stats[i][0], stats[i][1], stats[i][2], grades[i]);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of students: ");
        int studentCount = scanner.nextInt();

        int[][] marks = generateMarks(studentCount);
        double[][] stats = calculateScoreStats(marks);
        String[] grades = assignGrades(stats);

        displayScorecard(marks, stats, grades);
    }
}
