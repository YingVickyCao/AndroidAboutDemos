package com.example.hades.dagger2._10_subgraphs._subclass._plus_4_interface;

import com.example.hades.dagger2.Log;

import javax.inject.Inject;

public class DebugApp extends App {
    private static final String TAG = DebugApp.class.getSimpleName();

    @Inject
    IA mA;

    @Inject
    IA mA2;

    // ERROR:If no @Singleton, A is not Singleton
    @Inject
//    @Singleton
            B mB;

    @Inject
//    @Singleton
            B mB2;

    public void create() {
        System.out.println("============ DebugApp start =============");

        super.create();

//        mAppComponent.plus(new DebugAppModule());
        mAppComponent.inject(this);

        test();
        System.out.println("============ DebugApp end =============");

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

    protected boolean isParent() {
        return false;
    }
}