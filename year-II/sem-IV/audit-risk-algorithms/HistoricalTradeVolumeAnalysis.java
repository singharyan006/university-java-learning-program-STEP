import java.util.Arrays;

public class HistoricalTradeVolumeAnalysis {

    static class Trade {
        final String id;
        final int volume;

        Trade(String id, int volume) {
            this.id = id;
            this.volume = volume;
        }

        @Override
        public String toString() {
            return id + ":" + volume;
        }
    }

    public static void mergeSortByVolumeAsc(Trade[] trades) {
        if (trades.length < 2) {
            return;
        }
        Trade[] temp = new Trade[trades.length];
        mergeSort(trades, temp, 0, trades.length - 1);
    }

    private static void mergeSort(Trade[] arr, Trade[] temp, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSort(arr, temp, left, mid);
        mergeSort(arr, temp, mid + 1, right);
        merge(arr, temp, left, mid, right);
    }

    private static void merge(Trade[] arr, Trade[] temp, int left, int mid, int right) {
        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {
            if (arr[i].volume <= arr[j].volume) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        while (i <= mid) {
            temp[k++] = arr[i++];
        }
        while (j <= right) {
            temp[k++] = arr[j++];
        }

        for (int x = left; x <= right; x++) {
            arr[x] = temp[x];
        }
    }

    public static void quickSortByVolumeDesc(Trade[] trades) {
        quickSort(trades, 0, trades.length - 1);
    }

    private static void quickSort(Trade[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partitionDesc(arr, low, high);
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    private static int partitionDesc(Trade[] arr, int low, int high) {
        int pivot = arr[high].volume;
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j].volume >= pivot) {
                i++;
                Trade temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        Trade temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    public static Trade[] mergeTwoSortedAscLists(Trade[] morning, Trade[] afternoon) {
        Trade[] merged = new Trade[morning.length + afternoon.length];
        int i = 0;
        int j = 0;
        int k = 0;

        while (i < morning.length && j < afternoon.length) {
            if (morning[i].volume <= afternoon[j].volume) {
                merged[k++] = morning[i++];
            } else {
                merged[k++] = afternoon[j++];
            }
        }

        while (i < morning.length) {
            merged[k++] = morning[i++];
        }
        while (j < afternoon.length) {
            merged[k++] = afternoon[j++];
        }
        return merged;
    }

    public static long totalVolume(Trade[] trades) {
        long total = 0;
        for (Trade trade : trades) {
            total += trade.volume;
        }
        return total;
    }

    public static void main(String[] args) {
        Trade[] input = {
            new Trade("trade3", 500),
            new Trade("trade1", 100),
            new Trade("trade2", 300)
        };

        Trade[] mergeInput = input.clone();
        mergeSortByVolumeAsc(mergeInput);
        System.out.println("MergeSort asc: " + Arrays.toString(mergeInput));

        Trade[] quickInput = input.clone();
        quickSortByVolumeDesc(quickInput);
        System.out.println("QuickSort desc: " + Arrays.toString(quickInput));

        Trade[] morning = {
            new Trade("m1", 100), new Trade("m2", 300)
        };
        Trade[] afternoon = {
            new Trade("a1", 200), new Trade("a2", 400)
        };
        Trade[] merged = mergeTwoSortedAscLists(morning, afternoon);
        System.out.println("Merged sessions: " + Arrays.toString(merged));
        System.out.println("Total volume: " + totalVolume(merged));
    }
}
