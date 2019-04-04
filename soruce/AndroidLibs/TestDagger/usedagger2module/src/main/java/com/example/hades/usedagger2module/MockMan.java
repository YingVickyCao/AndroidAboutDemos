package com.example.hades.usedagger2module;

public class MockMan {
    private static Man mMan;

    public static Man getMan() {
        if (null == mMan) {
            mMan = new Man(new Car());
        }
        return mMan;
    }
}