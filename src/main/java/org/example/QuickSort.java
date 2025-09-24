package org.example;

import java.util.Random;

public class QuickSort {
    private static final Random rand = new Random();

    public static void sort(int[] arr, Metrics metrics) {
        if (arr == null || arr.length <= 1) return;
        sort(arr, 0, arr.length - 1, metrics);
    }

    private static void sort(int[] arr, int lo, int hi, Metrics metrics) {
        while (lo < hi) {
            metrics.enterRecursion();
            int pivotIndex = partition(arr, lo, hi, metrics);
            if (pivotIndex - lo < hi - pivotIndex) {
                sort(arr, lo, pivotIndex - 1, metrics);
                lo = pivotIndex + 1;
            } else {
                sort(arr, pivotIndex + 1, hi, metrics);
                hi = pivotIndex - 1;
            }
            metrics.exitRecursion();
        }
    }

    private static int partition(int[] arr, int lo, int hi, Metrics metrics) {
        int pivotIndex = lo + rand.nextInt(hi - lo + 1);
        int pivot = arr[pivotIndex];
        swap(arr, pivotIndex, hi);
        int i = lo;
        for (int j = lo; j < hi; j++) {
            metrics.incrementComparisons();
            if (arr[j] <= pivot) {
                swap(arr, i, j);
                i++;
            }
        }
        swap(arr, i, hi);
        return i;
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
