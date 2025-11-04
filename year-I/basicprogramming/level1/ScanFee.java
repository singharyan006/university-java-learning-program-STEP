import java.util.*;

public class ScanFee{
    public static void main(String[] args){

        //Using Scanner to receive the input from the user under tha alias sc 
        Scanner sc = new Scanner (System.in);

        // Prompting the user to enter the fee amount
        System.out.print("Enter the fee :");
        int fee = sc.nextInt();//taking the input as integer

        //Prompting the user to enter the discount percent 
        System.out.print("Enter the discount percent :");
        double discountPercent = sc.nextInt();//Storing it under double datatype

        // Calculating the discount
        double discount = (discountPercent/100) * fee;
        
        // Calculating the new fee
        double newFee = fee - discount;

        // Displaying the output
        System.out.printf("The Discount you avail %.2f and the new fee is %.2f",discount,newFee);
    }
}