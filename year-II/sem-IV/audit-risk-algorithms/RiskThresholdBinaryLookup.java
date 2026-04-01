import java.util.Arrays;

public class RiskThresholdBinaryLookup {

    static class LinearResult {
        final int index;
        final int comparisons;

        LinearResult(int index, int comparisons) {
            this.index = index;
            this.comparisons = comparisons;
        }
    }

    public static LinearResult linearSearch(int[] unsortedBands, int target) {
        int comparisons = 0;
        for (int i = 0; i < unsortedBands.length; i++) {
            comparisons++;
            if (unsortedBands[i] == target) {
                return new LinearResult(i, comparisons);
            }
        }
        return new LinearResult(-1, comparisons);
    }

    public static int insertionPoint(int[] sortedBands, int target) {
        int low = 0;
        int high = sortedBands.length;

        while (low < high) {
            int mid = low + (high - low) / 2;
            if (sortedBands[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    public static Integer floorValue(int[] sortedBands, int target) {
        int idx = insertionPoint(sortedBands, target);
        if (idx < sortedBands.length && sortedBands[idx] == target) {
            return sortedBands[idx];
        }
        return idx == 0 ? null : sortedBands[idx - 1];
    }

    public static Integer ceilingValue(int[] sortedBands, int target) {
        int idx = insertionPoint(sortedBands, target);
        return idx == sortedBands.length ? null : sortedBands[idx];
    }

    public static void main(String[] args) {
        int[] unsorted = {50, 10, 100, 25};
        int target = 30;

        LinearResult linear = linearSearch(unsorted, target);
        System.out.println("Linear threshold=30 -> index=" + linear.index + ", comparisons=" + linear.comparisons);

        int[] sorted = unsorted.clone();
        Arrays.sort(sorted);
        System.out.println("Sorted risks: " + Arrays.toString(sorted));

        int insertPoint = insertionPoint(sorted, target);
        Integer floor = floorValue(sorted, target);
        Integer ceiling = ceilingValue(sorted, target);

        System.out.println("Insertion point for 30: " + insertPoint);
        System.out.println("Floor(30): " + (floor == null ? "none" : floor));
        System.out.println("Ceiling(30): " + (ceiling == null ? "none" : ceiling));
    }
}
