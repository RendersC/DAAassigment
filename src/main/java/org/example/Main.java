package org.example;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] arr = {5, 2, 9, 1, 5, 6};


        MergeSort.sort(arr);
        System.out.println("MergeSort: " + Arrays.toString(arr));


        int[] arr2 = {10, -1, 3, 5, 0};
        QuickSort.sort(arr2);
        System.out.println("QuickSort: " + Arrays.toString(arr2));

        git rebase -i
        int[] arr3 = {7, 2, 1, 9, 5};
        int third = DeterministicSelect.select(arr3, 3);
        System.out.println("DeterministicSelect (3-й по величине): " + third);


        ClosestPair.Point[] pts = {
                new ClosestPair.Point(0, 0),
                new ClosestPair.Point(3, 4),
                new ClosestPair.Point(7, 1),
                new ClosestPair.Point(1, 1)
        };
        double dist = ClosestPair.findClosestPair(pts);
        System.out.println("ClosestPair: " + dist);
    }
}
