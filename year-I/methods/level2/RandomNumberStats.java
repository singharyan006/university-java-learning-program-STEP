import java.util.Random;
import java.util.Arrays;

public class RandomNumberStats {
    public static int[] generate4DigitRandomArray(int size) {
        Random random = new Random();
        int[] numbers = new int[size];
        for (int i = 0; i < size; i++) {
            numbers[i] = 1000 + random.nextInt(9000); // Generates a 4-digit number
        }
        return numbers;
    }

    public static double[] findAverageMinMax(int[] numbers) {
        int sum = Arrays.stream(numbers).sum();
        double average = (double) sum / numbers.length;
        int min = Arrays.stream(numbers).min().orElse(0);
        int max = Arrays.stream(numbers).max().orElse(0);
        return new double[]{average, min, max};
    }

    public static void main(String[] args) {
        int[] randomNumbers = generate4DigitRandomArray(5);
        System.out.println("Generated Numbers: " + Arrays.toString(randomNumbers));
        
        double[] results = findAverageMinMax(randomNumbers);
        System.out.println("Average: " + results[0]);
        System.out.println("Minimum: " + results[1]);
        System.out.println("Maximum: " + results[2]);
    }
}
