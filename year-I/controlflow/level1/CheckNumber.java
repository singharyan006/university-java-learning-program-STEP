import java.util.Scanner; 

public class CheckNumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); 
        
        // Get input from user
        System.out.print("Enter a number: ");
        int number = sc.nextInt(); 
        
        if (number > 0) {
            System.out.println("The number is positive.");
        } else if (number < 0) {
            System.out.println("The number is negative.");
        } else {
            System.out.println("The number is zero.");
        }
        
        sc.close();
    }
}
