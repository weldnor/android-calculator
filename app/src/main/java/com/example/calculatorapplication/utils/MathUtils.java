package com.example.calculatorapplication.utils;

public class MathUtils {
    public static long addDigitToLong(long integer, int digit) {
        if (integer == 0)
            return digit;

        if (integer < 0)
            return integer * 10 - digit;

        return integer * 10 + digit;
    }
}
