package com.example.hades.androidpo.temp._10_enhanced_loop_4_array.type1;

import com.example.hades.androidpo.temp._10_enhanced_loop_4_array.ArrayLoopFragment;

public class Loop1 {
    private int[] mArray = new int[ArrayLoopFragment.ARRAY_SIZE];

    public void test() {
        initArray();
        zero(); // 10s
        one();
        two(); // 9s
    }

    public void initArray() {
        for (int i = 0; i < ArrayLoopFragment.ARRAY_SIZE; i++) {
            mArray[i] = i;
        }
    }

    public void zero() {
        long startTime = System.currentTimeMillis();
        long sum = 0;
        for (int i = 0; i < mArray.length; ++i) {
            sum += mArray[i];
        }

        long endTime = System.currentTimeMillis();
        System.out.println("start=" + startTime + ",end=" + endTime + ",time = " + ((endTime - startTime)) + " s" + ",sum=" + sum);
    }

    public void one() {
        long startTime = System.currentTimeMillis();

        long sum = 0;
        int[] localArray = mArray;
        int len = localArray.length;

        for (int i = 0; i < len; ++i) {
            sum += localArray[i];
        }
        long endTime = System.currentTimeMillis();
        System.out.println("start=" + startTime + ",end=" + endTime + ",time = " + ((endTime - startTime)) + " s" + ",sum=" + sum);
    }

    public void two() {
        long startTime = System.currentTimeMillis();
        long sum = 0;
        for (int a : mArray) {
            sum += a;
        }
        long endTime = System.currentTimeMillis();
        // start=1527220563606,end=1527220563645,time = 39 s,sum=4999999950000000
        System.out.println("start=" + startTime + ",end=" + endTime + ",time = " + ((endTime - startTime)) + " s" + ",sum=" + sum);
    }

}
