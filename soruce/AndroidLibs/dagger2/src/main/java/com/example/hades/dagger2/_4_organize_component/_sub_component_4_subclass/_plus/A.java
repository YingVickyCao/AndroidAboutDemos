package com.example.hades.dagger2._4_organize_component._sub_component_4_subclass._plus;

public class A {
    private static final String TAG = A.class.getSimpleName();

    public A() {
    }

    public String info() {
        return TAG + "@" + hashCode();
    }
}