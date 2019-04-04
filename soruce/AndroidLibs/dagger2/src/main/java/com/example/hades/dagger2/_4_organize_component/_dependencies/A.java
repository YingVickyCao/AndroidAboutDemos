package com.example.hades.dagger2._4_organize_component._dependencies;

public class A {
    private static final String TAG = A.class.getSimpleName();

//    @Inject
    public A() {
    }

    public String a() {
        return "A@" + hashCode();
    }
}
