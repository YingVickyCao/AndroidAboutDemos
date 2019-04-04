package com.example.hades.dagger2._10_subgraphs._subclass._inject_parent_and_child;

public class B {
    private static final String TAG = B.class.getSimpleName();

    private A mA;

    public B() {
    }


    public B(A mA) {
        this.mA = mA;
    }

    public String info() {
        return TAG + "@" + hashCode();
    }

    public String info2() {
        return TAG + "@" + hashCode() + ",B(A)@" + mA.hashCode();
    }
}