import java.util.*;

public class NaturalNumber{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter the number : ");
		int num = sc.nextInt();
		
		if ( num > 0 ){
			int sumNatural  = (num * ( num + 1))/2;
			System.out.printf("The sum of %d natural numbers is %d ",num,sumNatural);
		}
		else {
			System.out.printf("The number %d is not a natural number",num);
		}
		sc.close();
	}
}