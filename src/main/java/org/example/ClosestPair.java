package org.example;

import java.util.Arrays;
import java.util.Comparator;

public class ClosestPair {
    public static class Point {
        double x, y;
        public Point(double x, double y) {
            this.x = x; this.y = y;
        }
    }

    public static double findClosestPair(Point[] points, Metrics metrics) {
        Point[] px = points.clone();
        Point[] py = points.clone();
        Arrays.sort(px, Comparator.comparingDouble(p -> p.x));
        Arrays.sort(py, Comparator.comparingDouble(p -> p.y));
        return closest(px, py, 0, points.length - 1, metrics);
    }

    private static double closest(Point[] px, Point[] py, int lo, int hi, Metrics metrics) {
        metrics.enterRecursion();
        if (hi - lo <= 3) {
            double min = Double.POSITIVE_INFINITY;
            for (int i = lo; i <= hi; i++) {
                for (int j = i + 1; j <= hi; j++) {
                    metrics.incrementComparisons();
                    min = Math.min(min, dist(px[i], px[j]));
                }
            }
            metrics.exitRecursion();
            return min;
        }
        int mid = (lo + hi) / 2;
        double midX = px[mid].x;

        Point[] pyl = Arrays.stream(py).filter(p -> p.x <= midX).toArray(Point[]::new);
        Point[] pyr = Arrays.stream(py).filter(p -> p.x > midX).toArray(Point[]::new);

        double d1 = closest(px, pyl, lo, mid, metrics);
        double d2 = closest(px, pyr, mid + 1, hi, metrics);
        double d = Math.min(d1, d2);

        double finalD = d;
        Point[] strip = Arrays.stream(py).filter(p -> Math.abs(p.x - midX) < finalD).toArray(Point[]::new);
        for (int i = 0; i < strip.length; i++) {
            for (int j = i + 1; j < strip.length && (strip[j].y - strip[i].y) < d; j++) {
                metrics.incrementComparisons();
                d = Math.min(d, dist(strip[i], strip[j]));
            }
        }
        metrics.exitRecursion();
        return d;
    }

    private static double dist(Point a, Point b) {
        double dx = a.x - b.x, dy = a.y - b.y;
        return Math.sqrt(dx * dx + dy * dy);
    }
}
