import java.util.*;
 public class IsFirstSmallest{
	 public static void main(String[] args){
		 Scanner sc = new Scanner(System.in);
		 
		 // Taking Inputs from the User
		 System.out.print("Enter the first number : ");
		 int num1 = sc.nextInt();
		 
		 System.out.print("Enter the second number : ");
		 int num2 = sc.nextInt();
		 
		 System.out.print("Enter the third number : ");
		 int num3 = sc.nextInt();
		 
		 if ( num1 < num2 && num1 < num3){
			 System.out.print("Is the first number the smallest? \n YES");
		 }
		 else{
			 System.out.print("Is the first number the smallest? \n NO");
		 }
		 sc.close();
	 }
 }
		 
		 

		 
		 