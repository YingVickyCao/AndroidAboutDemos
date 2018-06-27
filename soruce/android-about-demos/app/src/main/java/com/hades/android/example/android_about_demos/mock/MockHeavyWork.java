package com.hades.android.example.android_about_demos.mock;

public class MockHeavyWork {
    public long sum() {
        long returnResult = 0;
        long j = 98, i = 99, k = 100;
        for (int m = 0; m < 10000; m++) {
            returnResult = j * i * k * m;
        }
        return returnResult;
    }
}
