package com.example.hades.dagger2._9_add_multi_component_in_one_component._2;

import com.example.hades.dagger2.Log;

import javax.inject.Inject;

public class App {
    private static final String TAG = App.class.getSimpleName();
    @Inject
    A mA;

    @Inject
    B mB;

    public void create() {
        DaggerAppComponent.builder().build().inject(this);
        printA();
        printB();
    }

    public void printA() {
        Log.d(TAG, mA.info());
    }

    public void printB() {
        Log.d(TAG, mB.info());
    }
}