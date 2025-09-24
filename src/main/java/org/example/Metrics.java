package org.example;

public class Metrics {
    private long comparisons = 0;
    private long recursionDepth = 0;
    private long currentDepth = 0;
    private long allocations = 0;

    // 📌 учёт сравнений
    public void incrementComparisons() {
        comparisons++;
    }

    // 📌 учёт глубины рекурсии
    public void enterRecursion() {
        currentDepth++;
        recursionDepth = Math.max(recursionDepth, currentDepth);
    }

    public void exitRecursion() {
        currentDepth--;
    }

    // 📌 учёт выделений памяти
    public void incrementAllocations() {
        allocations++;
    }

    // 📌 геттеры
    public long getComparisons() {
        return comparisons;
    }

    public long getRecursionDepth() {
        return recursionDepth;
    }

    public long getAllocations() {
        return allocations;
    }

    // 📌 сброс
    public void reset() {
        comparisons = 0;
        recursionDepth = 0;
        currentDepth = 0;
        allocations = 0;
    }
}
