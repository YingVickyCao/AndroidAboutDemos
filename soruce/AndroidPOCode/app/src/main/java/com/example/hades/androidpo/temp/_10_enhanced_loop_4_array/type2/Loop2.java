package com.example.hades.androidpo.temp._10_enhanced_loop_4_array.type2;

import static com.example.hades.androidpo.temp._10_enhanced_loop_4_array.ArrayLoopFragment.ARRAY_SIZE;

public class Loop2 {
    private Foo[] mArray = new Foo[ARRAY_SIZE];

    public Loop2() {
        initArray();
    }

    public void test() {
        initArray();
        zero(); // 9s
        one();
        two(); // 7s
    }

    void initArray() {
        for (int i = 0; i < ARRAY_SIZE; i++) {
            Foo foo = new Foo();
            foo.mSplat = i;
            mArray[i] = foo;
        }
    }

    public void zero() {
        long startTime = System.currentTimeMillis();
        long sum = 0;
        for (int i = 0; i < mArray.length; ++i) {
            sum += mArray[i].mSplat;
        }

        long endTime = System.currentTimeMillis();
        System.out.println("start=" + startTime + ",end=" + endTime + ",time = " + ((endTime - startTime)) + " s" + ",sum=" + sum);
    }

    private void one() {
        long startTime = System.currentTimeMillis();
        long sum = 0;
        Foo[] localArray = mArray;
        int len = localArray.length;

        for (int i = 0; i < len; ++i) {
            sum += localArray[i].mSplat;
        }

        long endTime = System.currentTimeMillis();
        System.out.println("start=" + startTime + ",end=" + endTime + ",time = " + ((endTime - startTime)) + " s" + ",sum=" + sum);
    }

    public void two() {
        long startTime = System.currentTimeMillis();
        long sum = 0;
        for (Foo a : mArray) {
            sum += a.mSplat;
        }
        long endTime = System.currentTimeMillis();
        // start=1527220563606,end=1527220563645,time = 39 s,sum=4999999950000000
        System.out.println("start=" + startTime + ",end=" + endTime + ",time = " + ((endTime - startTime)) + " s" + ",sum=" + sum);
    }

}
