package org.example;

import java.util.Arrays;

public class DeterministicSelect {
    public static int select(int[] arr, int k) {
        if (arr.length == 1) return arr[0];
        return select(arr, 0, arr.length - 1, k);
    }

    private static int select(int[] arr, int lo, int hi, int k) {
        if (lo == hi) return arr[lo];

        int pivot = medianOfMedians(arr, lo, hi);
        int pivotIndex = partition(arr, lo, hi, pivot);

        int length = pivotIndex - lo + 1;
        if (k == length) return arr[pivotIndex];
        else if (k < length) return select(arr, lo, pivotIndex - 1, k);
        else return select(arr, pivotIndex + 1, hi, k - length);
    }

    private static int medianOfMedians(int[] arr, int lo, int hi) {
        int n = hi - lo + 1;
        if (n < 5) {
            Arrays.sort(arr, lo, hi + 1);
            return arr[lo + n / 2];
        }
        int[] medians = new int[(n + 4) / 5];
        for (int i = 0; i < medians.length; i++) {
            int subLo = lo + i * 5;
            int subHi = Math.min(subLo + 4, hi);
            Arrays.sort(arr, subLo, subHi + 1);
            medians[i] = arr[subLo + (subHi - subLo) / 2];
        }
        return medianOfMedians(medians, 0, medians.length - 1);
    }

    private static int partition(int[] arr, int lo, int hi, int pivot) {
        while (lo <= hi) {
            while (arr[lo] < pivot) lo++;
            while (arr[hi] > pivot) hi--;
            if (lo <= hi) {
                int t = arr[lo];
                arr[lo] = arr[hi];
                arr[hi] = t;
                lo++;
                hi--;
            }
        }
        return lo - 1;
    }
}
