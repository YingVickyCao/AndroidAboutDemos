package com.example.hades.dagger2._11_global_singleton;

public class Context {
    private static final String TAG = Context.class.getSimpleName();

    public void create() {

    }
    public void info() {
        System.out.println(TAG + "@" + hashCode());
    }
}
