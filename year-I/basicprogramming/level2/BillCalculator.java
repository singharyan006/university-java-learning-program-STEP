import java.util.*;

public class BillCalculator{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the price for 1 unit :");
        int unitPrice = sc.nextInt();

        System.out.print("Enter the Quantity :");
        int quantity = sc.nextInt();

        int totalBill = unitPrice * quantity;
        System.out.printf("The total purchase price is INR %d if the quantity %d and unit price is INR %d",totalBill,quantity,unitPrice);

    }
}