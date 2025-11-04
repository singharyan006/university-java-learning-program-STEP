import java.util.Random;
import java.util.Arrays;

public class FootballTeamStats {
    public static int[] generateHeights(int size) {
        Random random = new Random();
        int[] heights = new int[size];
        for (int i = 0; i < size; i++) {
            heights[i] = 150 + random.nextInt(101); // Generates height between 150cm and 250cm
        }
        return heights;
    }

    public static double findMeanHeight(int[] heights) {
        int sum = Arrays.stream(heights).sum();
        return (double) sum / heights.length;
    }

    public static int findShortestHeight(int[] heights) {
        return Arrays.stream(heights).min().orElse(0);
    }

    public static int findTallestHeight(int[] heights) {
        return Arrays.stream(heights).max().orElse(0);
    }

    public static void main(String[] args) {
        int[] playerHeights = generateHeights(11);
        System.out.println("Player Heights: " + Arrays.toString(playerHeights));
        
        double meanHeight = findMeanHeight(playerHeights);
        int shortest = findShortestHeight(playerHeights);
        int tallest = findTallestHeight(playerHeights);
        
        System.out.println("Mean Height: " + meanHeight + " cm");
        System.out.println("Shortest Height: " + shortest + " cm");
        System.out.println("Tallest Height: " + tallest + " cm");
    }
}