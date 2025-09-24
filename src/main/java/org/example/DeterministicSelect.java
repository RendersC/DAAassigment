package org.example;

public class DeterministicSelect {
    public static int select(int[] arr, int k, Metrics metrics) {
        return select(arr, 0, arr.length - 1, k, metrics);
    }

    private static int select(int[] arr, int lo, int hi, int k, Metrics metrics) {
        while (true) {
            if (lo == hi) return arr[lo];
            int pivot = medianOfMedians(arr, lo, hi, metrics);
            int pivotIndex = partition(arr, lo, hi, pivot, metrics);
            int length = pivotIndex - lo + 1;
            if (k == length) return arr[pivotIndex];
            else if (k < length) hi = pivotIndex - 1;
            else {
                k -= length;
                lo = pivotIndex + 1;
            }
        }
    }

    private static int partition(int[] arr, int lo, int hi, int pivot, Metrics metrics) {
        int i = lo;
        for (int j = lo; j <= hi; j++) {
            metrics.incrementComparisons();
            if (arr[j] < pivot) {
                swap(arr, i, j);
                i++;
            }
        }
        for (int j = lo; j <= hi; j++) {
            metrics.incrementComparisons();
            if (arr[j] == pivot) {
                swap(arr, i, j);
                return i;
            }
        }
        return i;
    }

    private static int medianOfMedians(int[] arr, int lo, int hi, Metrics metrics) {
        int n = hi - lo + 1;
        if (n < 5) {
            insertionSort(arr, lo, hi, metrics);
            return arr[lo + n / 2];
        }
        int numMedians = 0;
        for (int i = lo; i <= hi; i += 5) {
            int subHi = Math.min(i + 4, hi);
            insertionSort(arr, i, subHi, metrics);
            int median = i + (subHi - i) / 2;
            swap(arr, lo + numMedians, median);
            numMedians++;
        }
        return medianOfMedians(arr, lo, lo + numMedians - 1, metrics);
    }

    private static void insertionSort(int[] arr, int lo, int hi, Metrics metrics) {
        for (int i = lo + 1; i <= hi; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= lo) {
                metrics.incrementComparisons();
                if (arr[j] > key) {
                    arr[j + 1] = arr[j];
                    j--;
                } else break;
            }
            arr[j + 1] = key;
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
