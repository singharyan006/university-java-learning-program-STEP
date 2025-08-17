import java.util.Scanner;
import java.util.Random;

public class VotingEligibilityChecker {

    public static int[] generateRandomAges(int n) {
        Random rand = new Random();
        int[] ages = new int[n];
        for (int i = 0; i < n; i++) {
            ages[i] = rand.nextInt(90) + 10; // generates a random 2-digit number (10–99)
        }
        return ages;
    }

    public static String[][] checkVotingEligibility(int[] ages) {
        String[][] result = new String[ages.length][2];

        for (int i = 0; i < ages.length; i++) {
            int age = ages[i];
            result[i][0] = String.valueOf(age);

            if (age < 0) {
                result[i][1] = "false";
            } else if (age >= 18) {
                result[i][1] = "true";
            } else {
                result[i][1] = "false";
            }
        }

        return result;
    }

    public static void displayTable(String[][] data) {
        System.out.printf("%-10s %-15s%n", "Age", "Can Vote");
        System.out.println("------------------------");

        for (String[] row : data) {
            System.out.printf("%-10s %-15s%n", row[0], row[1]);
        }
    }

    public static void main(String[] args) {
        int numStudents = 10;

        int[] studentAges = generateRandomAges(numStudents);
        String[][] votingData = checkVotingEligibility(studentAges);

        displayTable(votingData);
    }
}
