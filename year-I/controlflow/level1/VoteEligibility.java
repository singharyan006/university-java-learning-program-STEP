import java.util.*;

public class VoteEligibility{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter you age : ");
		int age = sc.nextInt();
		
		if ( age >= 18){
			System.out.printf("The person's age is %d and can vote.",age);
		}
		else{
			System.out.printf("The person's age is %d and can't vote.",age);
		}
		sc.close();
	}
}