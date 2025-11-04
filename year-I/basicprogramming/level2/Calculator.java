import java.util.*;

public class Calculator{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the first number :");
        float num1 = sc.nextFloat();

        System.out.print("Enter the second number :");
        float num2 = sc.nextFloat();

        float sum = num1 + num2;
        float difference = num1 - num2;
        float product = num1 * num2;
        float quotient = num1 / num2;

        System.out.printf("The addition, subtraction, multiplication, and division value of 2 numbers is %.2f, %.2f, %.2f, %.2f ",sum,difference,product,quotient);      
    }
}