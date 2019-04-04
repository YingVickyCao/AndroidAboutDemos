package com.example.hades.dagger2._10_subgraphs._subclass._plus;

public class B {
    private static final String TAG = B.class.getSimpleName();
    private A mA;

    public B(A a) {
        mA = a;
    }

    public String info() {
        return TAG + "@" + hashCode() + ",A(A)@" + mA.hashCode();
    }
}
