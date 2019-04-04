package com.example.hades.dagger2._2_inject_with_module._constructor_without_parameter;

public class A {
    private static final String TAG = A.class.getSimpleName();

    public A() {
    }

    public String info() {
        return TAG + "@" + hashCode();
    }
}