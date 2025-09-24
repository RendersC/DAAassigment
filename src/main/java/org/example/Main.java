package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random rand = new Random();
        Metrics metrics = new Metrics();

        int[] sizes = {1000, 5000, 10000, 20000};

        try (FileWriter writer = new FileWriter("metrics.csv")) {
            writer.write("algorithmName;runTime;counter;depth;allocation;extra\n");

            for (int n : sizes) {
                // MergeSort
                int[] arr1 = rand.ints(n, 0, 100000).toArray();
                metrics.reset();
                long start = System.nanoTime();
                MergeSort.sort(arr1, metrics);
                long end = System.nanoTime();
                writer.write(String.format("MergeSort;%d;%.3f;%d;%d;%d;\n",
                        n, (end - start) / 1e6, metrics.getComparisons(),
                        metrics.getRecursionDepth(), metrics.getAllocations()));

                // QuickSort
                int[] arr2 = rand.ints(n, 0, 100000).toArray();
                metrics.reset();
                start = System.nanoTime();
                QuickSort.sort(arr2, metrics);
                end = System.nanoTime();
                writer.write(String.format("QuickSort;%d;%.3f;%d;%d;%d;\n",
                        n, (end - start) / 1e6, metrics.getComparisons(),
                        metrics.getRecursionDepth(), metrics.getAllocations()));

                // DeterministicSelect
                int[] arr3 = rand.ints(n, 0, 100000).toArray();
                int k = n / 2;
                metrics.reset();
                start = System.nanoTime();
                int kth = DeterministicSelect.select(arr3, k, metrics);
                end = System.nanoTime();
                writer.write(String.format("DeterministicSelect;%d;%.3f;%d;%d;0;k=%d;result=%d\n",
                        n, (end - start) / 1e6, metrics.getComparisons(),
                        metrics.getRecursionDepth(), k, kth));

                // ClosestPair
                ClosestPair.Point[] pts = new ClosestPair.Point[n];
                for (int i = 0; i < n; i++) {
                    pts[i] = new ClosestPair.Point(rand.nextDouble() * 10000, rand.nextDouble() * 10000);
                }
                metrics.reset();
                start = System.nanoTime();
                double dist = ClosestPair.findClosestPair(pts, metrics);
                end = System.nanoTime();
                writer.write(String.format("ClosestPair;%d;%.3f;%d;%d;0;dist=%.3f\n",
                        n, (end - start) / 1e6, metrics.getComparisons(),
                        metrics.getRecursionDepth(), dist));
            }
            System.out.println("✅ Метрики записаны в metrics.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
