import java.util.*;

public class MultiplicationTable {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the required Number : ");
        int num = sc.nextInt();
        if(num >= 6 && num <= 9){
            for (int i = 1; i <= 10; i++){
                System.out.println(num + " * " + i+ " = "+ num*i);
            }
        }
        else{
            System.out.print("Restricted to print "+num+ "'s table.");
        }
    }
}