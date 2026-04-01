import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionFeeSorting {

    static class Transaction {
        final String id;
        final double fee;
        final LocalTime timestamp;

        Transaction(String id, double fee, LocalTime timestamp) {
            this.id = id;
            this.fee = fee;
            this.timestamp = timestamp;
        }

        @Override
        public String toString() {
            return id + ":" + fee + "@" + timestamp;
        }
    }

    static class BubbleStats {
        int passes;
        int swaps;
    }

    static class InsertionStats {
        int passes;
        int shifts;
    }

    public static BubbleStats bubbleSortByFeeAscending(List<Transaction> transactions) {
        BubbleStats stats = new BubbleStats();
        int n = transactions.size();

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            stats.passes++;
            for (int j = 0; j < n - i - 1; j++) {
                if (transactions.get(j).fee > transactions.get(j + 1).fee) {
                    Transaction temp = transactions.get(j);
                    transactions.set(j, transactions.get(j + 1));
                    transactions.set(j + 1, temp);
                    stats.swaps++;
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }

        return stats;
    }

    public static InsertionStats insertionSortByFeeThenTimestamp(List<Transaction> transactions) {
        InsertionStats stats = new InsertionStats();

        for (int i = 1; i < transactions.size(); i++) {
            Transaction key = transactions.get(i);
            int j = i - 1;
            stats.passes++;

            while (j >= 0 && compareFeeThenTimestamp(transactions.get(j), key) > 0) {
                transactions.set(j + 1, transactions.get(j));
                stats.shifts++;
                j--;
            }
            transactions.set(j + 1, key);
        }

        return stats;
    }

    private static int compareFeeThenTimestamp(Transaction a, Transaction b) {
        int byFee = Double.compare(a.fee, b.fee);
        if (byFee != 0) {
            return byFee;
        }
        return a.timestamp.compareTo(b.timestamp);
    }

    public static List<Transaction> highFeeOutliers(List<Transaction> transactions, double threshold) {
        List<Transaction> outliers = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.fee > threshold) {
                outliers.add(transaction);
            }
        }
        return outliers;
    }

    public static void main(String[] args) {
        List<Transaction> input = new ArrayList<>();
        input.add(new Transaction("id1", 10.5, LocalTime.of(10, 0)));
        input.add(new Transaction("id2", 25.0, LocalTime.of(9, 30)));
        input.add(new Transaction("id3", 5.0, LocalTime.of(10, 15)));

        List<Transaction> smallBatch = new ArrayList<>(input);
        BubbleStats bubbleStats = bubbleSortByFeeAscending(smallBatch);

        List<Transaction> mediumBatch = new ArrayList<>(input);
        InsertionStats insertionStats = insertionSortByFeeThenTimestamp(mediumBatch);

        List<Transaction> outliers = highFeeOutliers(input, 50.0);

        System.out.println("BubbleSort (fees asc): " + smallBatch);
        System.out.println("passes=" + bubbleStats.passes + ", swaps=" + bubbleStats.swaps);

        System.out.println("InsertionSort (fee+timestamp asc): " + mediumBatch);
        System.out.println("passes=" + insertionStats.passes + ", shifts=" + insertionStats.shifts);

        System.out.println("High-fee outliers (>50): " + (outliers.isEmpty() ? "none" : outliers));
    }
}
