package com.example.matchmaker.misc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MinMaxAvgDoubleTest {

    @Test
    void testSum() {
        final MinMaxAvgDouble minMaxAvgDouble = new MinMaxAvgDouble(3, true);
        for (int i = 0; i < 10; i++) {
            minMaxAvgDouble.add(0, 0.1);
            minMaxAvgDouble.add(1, 0.1);
            minMaxAvgDouble.add(2, 0.1);
        }
        assertTrue(1.0 == minMaxAvgDouble.getSum(0));
        assertTrue(1.0 == minMaxAvgDouble.getSum(1));
        assertTrue(1.0 == minMaxAvgDouble.getSum(2));

        assertTrue(0.1 == minMaxAvgDouble.getAverage(0));
        assertTrue(0.1 == minMaxAvgDouble.getAverage(1));
        assertTrue(0.1 == minMaxAvgDouble.getAverage(2));
    }

    @Test
    void testMinMax() {
        final MinMaxAvgDouble minMaxAvgDouble = new MinMaxAvgDouble(1, true);
        for (int i = 0; i < 10; i++) {
            minMaxAvgDouble.add(0, i);
        }
        assertEquals(0, minMaxAvgDouble.getMin(0));
        assertEquals(9, minMaxAvgDouble.getMax(0));

    }

    @Test
    void testSimple() {
        final MinMaxAvgDouble minMaxAvgDouble = new MinMaxAvgDouble(1, false);
        for (int i = 0; i < 10; i++) {
            minMaxAvgDouble.add(0, 0.1);
        }
        System.out.println("minMaxAvgDouble = " + minMaxAvgDouble);
        assertTrue(Utils.equals(1.0, minMaxAvgDouble.getSum(0)));
    }
}