package com.example.hades.dagger2._10_subgraphs._subclass._inject_last_child;

public class C {
    private static final String TAG = C.class.getSimpleName();

    int n = 2;

    private A mA;

    public C() {
    }

    public C(A mA) {
        this.mA = mA;
    }

    public String info() {
        return TAG + "@" + hashCode();
    }

    public String info2() {
        return TAG + "@" + hashCode() + ",B(A)@" + mA.hashCode();
    }

    public String getN() {
        return info() + ",n=" + String.valueOf(n);
    }
}