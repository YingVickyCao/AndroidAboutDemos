package com.example.hades.androidpo.temp._11_enhanced_loop_4_list.type4;

import java.util.ArrayList;
import java.util.List;

public class Loop {
    private final static int ARRAY_SIZE = 100000000;
    private List<Foo> mArray = new ArrayList<>();

    /**
     * start=1527241268415,end=1527241268602,time = 187 s,sum=4999999950000000
     * start=1527241268602,end=1527241268785,time = 183 s,sum=4999999950000000
     * start=1527241268785,end=1527241268958,time = 173 s,sum=4999999950000000
     */
    public void test() {
        initArray();
        zero();
        one();
        two();
    }

    private void initArray() {
        for (int i = 0; i < ARRAY_SIZE; i++) {
            Foo foo = new Foo();
            foo.mSplat = i;
            mArray.add(i, foo);
        }
    }

    private void zero() {
        long startTime = System.currentTimeMillis();
        long sum = 0;
        for (int i = 0; i < mArray.size(); ++i) {
            sum += mArray.get(i).mSplat;

        }

        long endTime = System.currentTimeMillis();
        System.out.println("start=" + startTime + ",end=" + endTime + ",time = " + ((endTime - startTime)) + " s" + ",sum=" + sum);
    }

    private void one() {
        long startTime = System.currentTimeMillis();
        long sum = 0;
        List<Foo> localArray = mArray;
        int len = localArray.size();

        for (int i = 0; i < len; ++i) {
            sum += localArray.get(i).mSplat;
        }

        long endTime = System.currentTimeMillis();
        System.out.println("start=" + startTime + ",end=" + endTime + ",time = " + ((endTime - startTime)) + " s" + ",sum=" + sum);
    }

    private void two() {
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
