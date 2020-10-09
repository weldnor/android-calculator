package com.example.calculatorapplication.utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MathUtilsTest {

    @Test
    public void addition_isCorrect() {
        assertEquals(6, MathUtils.addDigitToLong(0, 6));
        assertEquals(1034, MathUtils.addDigitToLong(103, 4));
        assertEquals(-152, MathUtils.addDigitToLong(-15, 2));
    }
}