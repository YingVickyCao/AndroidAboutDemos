package com.example.hades.dagger2._10_subgraphs._subclass._inject_parent;

import com.example.hades.dagger2.Log;

public class CActivity extends FActivity {
    private static final String TAG = CActivity.class.getSimpleName();

    public void testC() {
        System.out.println("-----testC----->");
        Log.d(TAG, mA.getM());
        System.out.println("<-----testC-----");

    }
}