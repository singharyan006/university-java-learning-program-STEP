import java.util.*;

public class YoungestAndTallest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        String[] names = {"Amar", "Akbar", "Anthony"};
        int[] ages = new int[3];
        double[] heights = new double[3];
   
        for (int i = 0; i < 3; i++) {
            System.out.println("Enter details for " + names[i] + ":");
            
            while (true) {
                System.out.print("Enter age: ");
                ages[i] = sc.nextInt();
                if (ages[i] > 0) break;
                System.out.println("Invalid age! Please enter a positive value.");
            }
            
            while (true) {
                System.out.print("Enter height (in cm): ");
                heights[i] = sc.nextDouble();
                if (heights[i] > 0) break;
                System.out.println("Invalid height! Please enter a positive value.");
            }
        }

        int minAge = ages[0];
        String youngest = names[0];
        for (int i = 1; i < 3; i++) {
            if (ages[i] < minAge) {
                minAge = ages[i];
                youngest = names[i];
            }
        }
 
        double maxHeight = heights[0];
        String tallest = names[0];
        for (int i = 1; i < 3; i++) {
            if (heights[i] > maxHeight) {
                maxHeight = heights[i];
                tallest = names[i];
            }
        }
 
        System.out.println("\nThe youngest friend is: " + youngest + " (" + minAge + " years)");
        System.out.println("The tallest friend is: " + tallest + " (" + maxHeight + " cm)");
        
        sc.close();
    }
}
