package com.example.hades.androidpo._2_memory_op.sparse_array;

import android.util.ArrayMap;
import android.util.SparseLongArray;

/**
 * Create by Vicky on 31/08/2018.
 **/
public class TestLongSparseLongArray {
    SparseLongArray mSparseLongArray = new SparseLongArray();
//    LongSparseArray;
    ArrayMap<String, Integer> mArrayMap;

    public void test() {
        mSparseLongArray.put(10, 1000L);
    }
}
