import java.util.*;

public class BiggestOfThreeNumbers{
	public static void main(String[] args){
		Scanner sc = new Scanner( System.in);
		
		// Taking Inputs from the User
		 System.out.print("Enter the first number : ");
		 int num1 = sc.nextInt();
		 
		 System.out.print("Enter the second number : ");
		 int num2 = sc.nextInt();
		 
		 System.out.print("Enter the third number : ");
		 int num3 = sc.nextInt();
		 
		 if( num1 > num2 && num1 > num3 ){
			 System.out.println("Is the first number the largest? YES");
			 System.out.println("Is the second number the largest? NO");
			 System.out.println("Is the third number the largest? NO");
		 }
		 else if (num2 > num1 && num2 > num3){
			 System.out.println("Is the first number the largest? NO");
			 System.out.println("Is the second number the largest? YES");
			 System.out.println("Is the third number the largest? NO");
		 }
		 else if (num3 > num1 && num3 > num2){
			 System.out.println("Is the first number the largest? NO");
			 System.out.println("Is the second number the largest? NO");
			 System.out.println("Is the third number the largest? YES");
		 }
		 
		 sc.close();
	}
}
			 