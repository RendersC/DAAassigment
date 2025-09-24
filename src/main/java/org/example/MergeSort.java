package org.example;

public class MergeSort {
    private static final int CUTOFF = 16;

    public static void sort(int[] arr, Metrics metrics) {
        if (arr == null || arr.length <= 1) return;
        metrics.incrementAllocations();
        int[] aux = new int[arr.length];
        sort(arr, aux, 0, arr.length - 1, metrics);
    }

    private static void sort(int[] arr, int[] aux, int lo, int hi, Metrics metrics) {
        metrics.enterRecursion();
        if (hi - lo <= CUTOFF) {
            insertionSort(arr, lo, hi, metrics);
            metrics.exitRecursion();
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(arr, aux, lo, mid, metrics);
        sort(arr, aux, mid + 1, hi, metrics);
        merge(arr, aux, lo, mid, hi, metrics);
        metrics.exitRecursion();
    }

    private static void merge(int[] arr, int[] aux, int lo, int mid, int hi, Metrics metrics) {
        System.arraycopy(arr, lo, aux, lo, hi - lo + 1);
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            metrics.incrementComparisons();
            if (i > mid) arr[k] = aux[j++];
            else if (j > hi) arr[k] = aux[i++];
            else if (aux[j] < aux[i]) arr[k] = aux[j++];
            else arr[k] = aux[i++];
        }
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
}
