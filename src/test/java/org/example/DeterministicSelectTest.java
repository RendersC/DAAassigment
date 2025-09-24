package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DeterministicSelectTest {

    @Test
    void testSelectMiddle() {
        int[] arr = {3, 1, 2, 5, 4};
        int result = DeterministicSelect.select(arr, 3); // 3-й по величине
        assertEquals(3, result);
    }

    @Test
    void testSelectMin() {
        int[] arr = {10, 7, 3, 5, 1};
        int result = DeterministicSelect.select(arr, 1); // минимум
        assertEquals(1, result);
    }

    @Test
    void testSelectMax() {
        int[] arr = {10, 7, 3, 5, 1};
        int result = DeterministicSelect.select(arr, arr.length); // максимум
        assertEquals(10, result);
    }
}
