import java.util.*;

public class Multiplication6to9 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] multiplicationResult = new int[4];

        //Prompted the user to give a number
        System.out.print("Enter a number :");
        int num = sc.nextInt();

        //This for loop is used to store the product into the array
        for( int i = 0; i < 4; i++){
            multiplicationResult[i] = num * (i+6); 
        }

        //This for loop is used to tranverse and display the table
        for( int i= 0; i < 4; i++){
            System.out.print("\n"+num +" * "+ (i + 6)+ " = "+ multiplicationResult[i]);
        }

        sc.close();
    }
}