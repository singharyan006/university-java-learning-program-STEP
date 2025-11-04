import java.util.*;

public class MeanHeightCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double[] heights = new double[11];
        double sum = 0.0;
        for (int i = 0; i < heights.length; i++) {
            System.out.print("Enter the height of player (in cm) " + (i + 1) + ": ");
            heights[i] = sc.nextDouble();
            sum += heights[i];
        }
        double meanHeight = sum / heights.length;
        System.out.println("The mean height of the football team is: " + meanHeight+" cm");
        sc.close();
    }
}
