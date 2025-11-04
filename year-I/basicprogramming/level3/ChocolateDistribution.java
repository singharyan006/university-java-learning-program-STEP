import java.util.*;

public class ChocolateDistribution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Taking Inputs
        System.out.print("Enter number of chocolates: ");
        int numberOfChocolates = scanner.nextInt();

        System.out.print("Enter number of children: ");
        int numberOfChildren = scanner.nextInt();
        
        int chocolatesPerChild = numberOfChocolates / numberOfChildren; // Calculating for choclates per child
        int remainingChocolates = numberOfChocolates % numberOfChildren; // Calculating for remaining chocolates

        // Displaying the output
        System.out.println("The number of chocolates each child gets is " + chocolatesPerChild + " and the number of remaining chocolates are " + remainingChocolates + ".");
    }
}
