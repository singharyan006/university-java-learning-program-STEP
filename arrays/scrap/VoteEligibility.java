import java.util.*;

public class VoteEligibility {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] studArray = new int[10];

        for (int i = 0; i < 10; i++){
            System.out.println("Enter the age of Student "+(i+1)+ ":");
            studArray[i] = sc.nextInt();
        }

        for (int i = 0; i < 10; i++){
            if( studArray[i] < 0){
                System.out.println("!!Invalid Age!!");
            }else if(studArray[i] >= 18){
                System.out.println("The student with age "+studArray[i]+ "years is eligible to vote.");
            }
            else{
                System.out.println("The student with age "+studArray[i]+ "years is eligible to vote.");
            }
        }

        sc.close();
    }
}