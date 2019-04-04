package com.example.hades.dagger2._10_subgraphs._subclass._inject_parent;

import com.example.hades.dagger2.Log;

public class A {
    private static final String TAG = A.class.getSimpleName();

    int m = 2;

    public A() {
        Log.d(TAG, info());
    }

    private String info() {
        return TAG + "@" + hashCode();
    }

    public String getM() {
        return info() + ",m=" + String.valueOf(m);
    }
}