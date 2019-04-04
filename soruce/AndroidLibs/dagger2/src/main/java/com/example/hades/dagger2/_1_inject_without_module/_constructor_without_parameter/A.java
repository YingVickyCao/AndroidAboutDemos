package com.example.hades.dagger2._1_inject_without_module._constructor_without_parameter;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class A {
    private static final String TAG = A.class.getSimpleName();

    @Inject
    public A() {
    }

    public String info() {
        return TAG + "@" + hashCode();
    }
}
