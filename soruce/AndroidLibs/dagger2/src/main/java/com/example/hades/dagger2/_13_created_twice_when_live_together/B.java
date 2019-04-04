package com.example.hades.dagger2._13_created_twice_when_live_together;

public class B {
    A mA;

    public B(A mA) {
        this.mA = mA;
    }

    public String getInfo() {
        return "B@" + hashCode() + "," + mA.getInfo();
    }
}