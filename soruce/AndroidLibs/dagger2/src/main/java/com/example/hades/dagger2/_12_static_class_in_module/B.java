package com.example.hades.dagger2._12_static_class_in_module;

public class B {
    private A mA;

    public B(A mA) {
        this.mA = mA;
    }

    @Override
    public String toString() {
        return "A{" +
                "mA=" + mA.toString() +
                '}';
    }

    public String printHashCode() {
        return "A@" + hashCode() + ",A@" + mA.hashCode();
    }
}