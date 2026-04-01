import java.util.Arrays;
import java.util.Random;

public class PortfolioReturnSorting {

    enum PivotStrategy {
        RANDOM,
        MEDIAN_OF_THREE
    }

    static class Asset {
        final String symbol;
        final double returnRate;
        final double volatility;

        Asset(String symbol, double returnRate, double volatility) {
            this.symbol = symbol;
            this.returnRate = returnRate;
            this.volatility = volatility;
        }

        @Override
        public String toString() {
            return symbol + ":" + returnRate + "% (vol=" + volatility + ")";
        }
    }

    public static void mergeSortByReturnAscStable(Asset[] assets) {
        if (assets.length < 2) {
            return;
        }
        Asset[] temp = new Asset[assets.length];
        mergeSort(assets, temp, 0, assets.length - 1);
    }

    private static void mergeSort(Asset[] arr, Asset[] temp, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSort(arr, temp, left, mid);
        mergeSort(arr, temp, mid + 1, right);
        merge(arr, temp, left, mid, right);
    }

    private static void merge(Asset[] arr, Asset[] temp, int left, int mid, int right) {
        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {
            if (arr[i].returnRate <= arr[j].returnRate) {
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

        for (int idx = left; idx <= right; idx++) {
            arr[idx] = temp[idx];
        }
    }

    public static void quickSortByReturnDescThenVolAsc(Asset[] assets, PivotStrategy strategy) {
        quickSort(assets, 0, assets.length - 1, strategy);
    }

    private static void quickSort(Asset[] arr, int low, int high, PivotStrategy strategy) {
        if (low < high) {
            int pivotIndex = choosePivot(arr, low, high, strategy);
            swap(arr, pivotIndex, high);
            int p = partition(arr, low, high);
            quickSort(arr, low, p - 1, strategy);
            quickSort(arr, p + 1, high, strategy);
        }
    }

    private static int choosePivot(Asset[] arr, int low, int high, PivotStrategy strategy) {
        if (strategy == PivotStrategy.RANDOM) {
            return low + new Random().nextInt(high - low + 1);
        }
        int mid = low + (high - low) / 2;
        Asset a = arr[low];
        Asset b = arr[mid];
        Asset c = arr[high];

        if (compareForDesc(a, b) <= 0) {
            if (compareForDesc(b, c) <= 0) {
                return mid;
            }
            return compareForDesc(a, c) <= 0 ? high : low;
        } else {
            if (compareForDesc(a, c) <= 0) {
                return low;
            }
            return compareForDesc(b, c) <= 0 ? high : mid;
        }
    }

    private static int partition(Asset[] arr, int low, int high) {
        Asset pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (compareForDesc(arr[j], pivot) <= 0) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);
        return i + 1;
    }

    private static int compareForDesc(Asset a, Asset b) {
        int byReturnDesc = Double.compare(b.returnRate, a.returnRate);
        if (byReturnDesc != 0) {
            return byReturnDesc;
        }
        return Double.compare(a.volatility, b.volatility);
    }

    private static void swap(Asset[] arr, int i, int j) {
        Asset temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        Asset[] input = {
            new Asset("AAPL", 12.0, 0.30),
            new Asset("TSLA", 8.0, 0.60),
            new Asset("GOOG", 15.0, 0.25)
        };

        Asset[] mergeInput = input.clone();
        mergeSortByReturnAscStable(mergeInput);
        System.out.println("Merge asc: " + Arrays.toString(mergeInput));

        Asset[] quickInput = input.clone();
        quickSortByReturnDescThenVolAsc(quickInput, PivotStrategy.MEDIAN_OF_THREE);
        System.out.println("Quick desc: " + Arrays.toString(quickInput));
    }
}
