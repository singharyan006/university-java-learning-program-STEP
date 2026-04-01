import java.util.Arrays;

public class AccountIdLookupInTransactionLogs {

    static class SearchResult {
        final int index;
        final int comparisons;

        SearchResult(int index, int comparisons) {
            this.index = index;
            this.comparisons = comparisons;
        }

        @Override
        public String toString() {
            return "index=" + index + ", comparisons=" + comparisons;
        }
    }

    public static SearchResult linearFirstOccurrence(String[] logs, String target) {
        int comparisons = 0;
        for (int i = 0; i < logs.length; i++) {
            comparisons++;
            if (logs[i].equals(target)) {
                return new SearchResult(i, comparisons);
            }
        }
        return new SearchResult(-1, comparisons);
    }

    public static SearchResult linearLastOccurrence(String[] logs, String target) {
        int comparisons = 0;
        for (int i = logs.length - 1; i >= 0; i--) {
            comparisons++;
            if (logs[i].equals(target)) {
                return new SearchResult(i, comparisons);
            }
        }
        return new SearchResult(-1, comparisons);
    }

    public static SearchResult binaryAnyOccurrence(String[] sortedLogs, String target) {
        int low = 0;
        int high = sortedLogs.length - 1;
        int comparisons = 0;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            comparisons++;
            int cmp = sortedLogs[mid].compareTo(target);

            if (cmp == 0) {
                return new SearchResult(mid, comparisons);
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return new SearchResult(-1, comparisons);
    }

    public static int countOccurrencesBinary(String[] sortedLogs, String target) {
        int first = lowerBound(sortedLogs, target);
        if (first == sortedLogs.length || !sortedLogs[first].equals(target)) {
            return 0;
        }
        int upper = upperBound(sortedLogs, target);
        return upper - first;
    }

    private static int lowerBound(String[] arr, String target) {
        int low = 0;
        int high = arr.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (arr[mid].compareTo(target) < 0) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    private static int upperBound(String[] arr, String target) {
        int low = 0;
        int high = arr.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (arr[mid].compareTo(target) <= 0) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    public static void main(String[] args) {
        String[] logs = {"accB", "accA", "accB", "accC"};

        SearchResult linearFirst = linearFirstOccurrence(logs, "accB");
        SearchResult linearLast = linearLastOccurrence(logs, "accB");
        System.out.println("Linear first accB: " + linearFirst);
        System.out.println("Linear last accB: " + linearLast);

        String[] sorted = logs.clone();
        Arrays.sort(sorted);
        System.out.println("Sorted logs: " + Arrays.toString(sorted));

        SearchResult binary = binaryAnyOccurrence(sorted, "accB");
        int count = countOccurrencesBinary(sorted, "accB");
        System.out.println("Binary accB: " + binary + ", count=" + count);
    }
}
