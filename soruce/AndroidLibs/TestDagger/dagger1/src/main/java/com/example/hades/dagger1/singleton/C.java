package com.example.hades.dagger1.singleton;

import android.util.Log;

public class C {
    private static final String TAG = C.class.getSimpleName();
    private A mA;
    private B mB;

    public C(A a, B b) {
        mA = a;
        mB = b;
    }

    public void sayABC() {
        Log.d(TAG, "C hashCode=: " + hashCode() + ",A hashCode=" + mA.hashCode() + ",B hashCode=" + mB.hashCode());
        sayA();
        sayB();
        sayC();
    }

    public String sayA() {
        return mA.sayA();
    }

    public String sayB() {
        return mB.sayB();
    }

    public String sayC() {
        return "I am C";
    }
}