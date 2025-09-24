package org.example;

import java.util.Arrays;
import java.util.Comparator;

public class ClosestPair {
    static class Point {
        double x, y;
        Point(double x, double y) { this.x = x; this.y = y; }
    }

    public static double findClosestPair(Point[] points) {
        Point[] px = points.clone();
        Arrays.sort(px, Comparator.comparingDouble(p -> p.x));
        Point[] py = points.clone();
        Arrays.sort(py, Comparator.comparingDouble(p -> p.y));
        return closest(px, py);
    }

    private static double closest(Point[] px, Point[] py) {
        int n = px.length;
        if (n <= 3) return bruteForce(px);

        int mid = n / 2;
        Point midPoint = px[mid];

        Point[] pyl = Arrays.stream(py).filter(p -> p.x <= midPoint.x).toArray(Point[]::new);
        Point[] pyr = Arrays.stream(py).filter(p -> p.x > midPoint.x).toArray(Point[]::new);

        double dl = closest(Arrays.copyOfRange(px, 0, mid), pyl);
        double dr = closest(Arrays.copyOfRange(px, mid, n), pyr);
        double d = Math.min(dl, dr);

        return Math.min(d, stripClosest(py, midPoint.x, d));
    }

    private static double bruteForce(Point[] pts) {
        double min = Double.MAX_VALUE;
        for (int i = 0; i < pts.length; i++) {
            for (int j = i + 1; j < pts.length; j++) {
                double dist = dist(pts[i], pts[j]);
                if (dist < min) min = dist;
            }
        }
        return min;
    }

    private static double stripClosest(Point[] py, double midX, double d) {
        Point[] strip = Arrays.stream(py)
                .filter(p -> Math.abs(p.x - midX) < d)
                .toArray(Point[]::new);

        double min = d;
        for (int i = 0; i < strip.length; i++) {
            for (int j = i + 1; j < strip.length && (strip[j].y - strip[i].y) < min; j++) {
                double dist = dist(strip[i], strip[j]);
                if (dist < min) min = dist;
            }
        }
        return min;
    }

    private static double dist(Point p1, Point p2) {
        return Math.hypot(p1.x - p2.x, p1.y - p2.y);
    }
}
