package com.example.matchmaker.misc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UtilsTest {

    @Test
    void test() {
        double d1 = 1.0;
        double d2 = 0.1 + 0.1 + 0.1 + 0.1 + 0.1 + 0.1 + 0.1 + 0.1 + 0.1 + 0.1;
        assertTrue(Utils.equals(d1, d2));
        assertFalse(d1 == d2);
        assertTrue(1.0 == Utils.kahanSum(0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1));

    }


}