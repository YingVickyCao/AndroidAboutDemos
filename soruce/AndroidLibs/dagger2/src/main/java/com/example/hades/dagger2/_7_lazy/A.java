package com.example.hades.dagger2._7_lazy;


import com.example.hades.dagger2.Log;

public class A {
    private static final String TAG = A.class.getSimpleName();

    public A() {
        Log.d(TAG, "A: " + info());
    }

    public String info() {
        return TAG + "@" + hashCode();
    }
}
