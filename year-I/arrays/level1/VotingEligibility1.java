import java.util.*;

public class VotingEligibility1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] ages = new int[10];
        for (int i = 0; i < ages.length; i++) {
            System.out.print("Enter the age of student " + (i + 1) + ": ");
            ages[i] = sc.nextInt();
            if (ages[i] < 5 || ages[i] > 25) {
                System.out.println("Invalid age for a student: " + ages[i]);
                i--;
            }
        }
        for (int i = 0; i < ages.length; i++) {
            int age = ages[i];
            if (age < 0) {
                System.out.println("Invalid age: " + age);
            } else if (age >= 18) {
                System.out.println("The student " + (i + 1) + " with the age " + age + " can vote.");
            } else {
                System.out.println("The student " + (i + 1) + " with the age " + age + " cannot vote.");
            }
        }
        sc.close();
    }
}
