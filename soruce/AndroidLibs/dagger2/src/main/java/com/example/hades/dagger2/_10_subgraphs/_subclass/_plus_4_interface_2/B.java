package com.example.hades.dagger2._10_subgraphs._subclass._plus_4_interface_2;

public class B {
    private static final String TAG = B.class.getSimpleName();
    private IA mA;

    public B(IA a) {
        mA = a;
    }

    public String info() {
        return TAG + "@" + hashCode() + "," + mA.info();
    }
}
