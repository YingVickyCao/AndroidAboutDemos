package com.example.hades.dagger2._12_static_class_in_module;

import javax.inject.Inject;
import javax.inject.Singleton;

public class MainActivity {
    @Inject
    @Singleton
    B mB1;

    @Inject
    @Singleton
    B mB2;

    public void onCreate() {
        DaggerDataComponent.builder().build().inject(this);

        test();
    }

    private void test() {
        System.out.println("mB1=" + mB1.toString() + "," + mB1.printHashCode());
        System.out.println("mB2=" + mB2.toString() + "," + mB2.printHashCode());
    }
}
