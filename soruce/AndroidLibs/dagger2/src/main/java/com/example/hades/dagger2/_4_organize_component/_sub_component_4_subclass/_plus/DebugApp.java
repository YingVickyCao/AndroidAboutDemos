package com.example.hades.dagger2._4_organize_component._sub_component_4_subclass._plus;

import com.example.hades.dagger2.Log;

import javax.inject.Inject;

public class DebugApp extends App {
    private static final String TAG = DebugApp.class.getSimpleName();

    @Inject
    A mA;

    @Inject
    A mA2;

    @Inject
    B mB;

    @Inject
    B mB2;

    DebugAppComponent debugAppComponent;

    public void create() {
        super.create();

        debugAppComponent = mAppComponent.plus(new DebugAppModule());
        debugAppComponent.inject(this);

        test();
    }

    private void test() {
        printA();
        printB();
    }

    private void printA() {
        Log.d(TAG, mA.info() + "," + mA2.info());
    }

    private void printB() {
        Log.d(TAG, mB.info() + "," + mB2.info());
    }
}