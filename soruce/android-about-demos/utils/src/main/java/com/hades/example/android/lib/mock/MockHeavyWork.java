package com.hades.example.android.lib.mock;

public class MockHeavyWork {
    public static long sum() {
        long returnResult = 0;
        long j = 98, i = 99, k = 100;
        for (int m = 0; m < 10000; m++) {
            returnResult = j * i * k * m;
        }
        return returnResult;
    }

    public static long sum(int upper) {
        long sum = 0;
        for (int i = 0; i < upper; i++) {
            sum += i;
        }
        return sum;
    }
}
