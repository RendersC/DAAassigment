package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuickSortTest {

    @Test
    void testBasicSort() {
        int[] arr = {10, -1, 3, 5, 0};
        QuickSort.sort(arr);
        assertArrayEquals(new int[]{-1, 0, 3, 5, 10}, arr);
    }

    @Test
    void testWithDuplicates() {
        int[] arr = {5, 5, 5, 5};
        QuickSort.sort(arr);
        assertArrayEquals(new int[]{5, 5, 5, 5}, arr);
    }

    @Test
    void testEmptyArray() {
        int[] arr = {};
        QuickSort.sort(arr);
        assertArrayEquals(new int[]{}, arr);
    }
}
