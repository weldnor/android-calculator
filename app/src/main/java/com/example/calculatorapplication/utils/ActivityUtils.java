package com.example.calculatorapplication.utils;

import com.example.calculatorapplication.R;

import java.util.Arrays;
import java.util.List;

public class ActivityUtils {
    public static List<Integer> getNumberIds() {
        return Arrays.asList(
                R.id.nine,
                R.id.eight,
                R.id.seven,
                R.id.six,
                R.id.five,
                R.id.four,
                R.id.three,
                R.id.two,
                R.id.one,
                R.id.zero);
    }

    public static List<Integer> getOperationsIds() {
        return Arrays.asList(
                R.id.ac,
                R.id.delSymb,
                R.id.proc,
                R.id.sign,
                R.id.point,
                R.id.div,
                R.id.multiply,
                R.id.minus,
                R.id.plus,
                R.id.plus,
                R.id.equals
        );
    }

    public static int getDigitByNumberId(int numberId) {
        switch (numberId) {
            case R.id.zero:
                return 0;
            case R.id.one:
                return 1;
            case R.id.two:
                return 2;
            case R.id.three:
                return 3;
            case R.id.four:
                return 4;
            case R.id.five:
                return 5;
            case R.id.six:
                return 6;
            case R.id.seven:
                return 7;
            case R.id.eight:
                return 8;
            case R.id.nine:
                return 9;
        }
        return -1;
    }
}
