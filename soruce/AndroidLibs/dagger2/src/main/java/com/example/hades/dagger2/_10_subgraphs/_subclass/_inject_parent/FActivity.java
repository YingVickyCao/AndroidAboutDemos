package com.example.hades.dagger2._10_subgraphs._subclass._inject_parent;

import com.example.hades.dagger2.Log;

import javax.inject.Inject;

public class FActivity {
    private static final String TAG = FActivity.class.getSimpleName();

    @Inject
    protected A mA;

    public void onCreate() {
        System.out.println("FActivity onCreate called. start ");
        DaggerFActivityComponent.builder().build().inject(this);
        System.out.println("FActivity onCreate called. end ");
    }

    public void testF() {
        System.out.println("-----testF----->");
        Log.d(TAG, mA.getM());
        System.out.println("<-----testF-----");
    }
}