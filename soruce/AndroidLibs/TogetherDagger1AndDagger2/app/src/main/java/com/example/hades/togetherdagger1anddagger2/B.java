package com.example.hades.togetherdagger1anddagger2;

public class B {
    private final Dagger1Module.A mA;

    public B(Dagger1Module.A a) {
        this.mA = a;
    }

    public String data() {
        return mA.randomValue();
    }
}