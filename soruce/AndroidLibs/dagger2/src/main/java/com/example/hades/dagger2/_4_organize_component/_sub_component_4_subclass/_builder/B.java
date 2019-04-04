package com.example.hades.dagger2._4_organize_component._sub_component_4_subclass._builder;

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
