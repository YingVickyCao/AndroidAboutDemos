package com.example.hades.dagger2._10_subgraphs._subclass._inject_last_child;

public class A {
    private static final String TAG = A.class.getSimpleName();

    int m = 2;

    public A() {
    }

    public String info() {
        return TAG + "@" + hashCode();
    }

    public String getM() {
        return info() + ",m=" + String.valueOf(m);
    }
}