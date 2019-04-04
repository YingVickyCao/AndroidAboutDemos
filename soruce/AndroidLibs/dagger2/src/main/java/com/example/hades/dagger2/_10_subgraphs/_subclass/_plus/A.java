package com.example.hades.dagger2._10_subgraphs._subclass._plus;

import com.example.hades.dagger2.Log;

public class A {
    private static final String TAG = A.class.getSimpleName();

    public A() {
        Log.d(TAG, "A()@" + hashCode());

    }

    public String info() {
        return TAG + "@" + hashCode();
    }
}