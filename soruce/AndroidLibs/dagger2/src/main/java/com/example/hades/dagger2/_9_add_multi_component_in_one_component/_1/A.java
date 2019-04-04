package com.example.hades.dagger2._9_add_multi_component_in_one_component._1;

public class A {
    private static final String TAG = A.class.getSimpleName();

    public A() {
    }

    public String info() {
        return TAG + "@" + hashCode();
    }
}