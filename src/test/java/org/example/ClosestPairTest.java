package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ClosestPairTest {

    @Test
    void testBasicPoints() {
        ClosestPair.Point[] pts = {
                new ClosestPair.Point(0, 0),
                new ClosestPair.Point(3, 4),
                new ClosestPair.Point(7, 1),
                new ClosestPair.Point(1, 1)
        };
        double result = ClosestPair.findClosestPair(pts);
        assertEquals(Math.sqrt(2), result, 1e-6); // расстояние между (0,0) и (1,1)
    }

    @Test
    void testTwoPoints() {
        ClosestPair.Point[] pts = {
                new ClosestPair.Point(1, 1),
                new ClosestPair.Point(2, 2)
        };
        double result = ClosestPair.findClosestPair(pts);
        assertEquals(Math.sqrt(2), result, 1e-6);
    }

    @Test
    void testCollinearPoints() {
        ClosestPair.Point[] pts = {
                new ClosestPair.Point(0, 0),
                new ClosestPair.Point(5, 0),
                new ClosestPair.Point(2, 0),
                new ClosestPair.Point(9, 0)
        };
        double result = ClosestPair.findClosestPair(pts);
        assertEquals(3.0, result, 1e-6); // минимальное расстояние между (2,0) и (5,0)
    }
}
