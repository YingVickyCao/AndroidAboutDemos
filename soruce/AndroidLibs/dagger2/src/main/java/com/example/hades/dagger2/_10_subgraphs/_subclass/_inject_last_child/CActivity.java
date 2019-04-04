package com.example.hades.dagger2._10_subgraphs._subclass._inject_last_child;

import com.example.hades.dagger2.Log;

import javax.inject.Inject;

public class CActivity extends FActivity {
    private static final String TAG = CActivity.class.getSimpleName();

    @Inject
    B mB;

    @Inject
    C mC;

    @Override
    public void onCreate() {
        System.out.println("START_CActivity_onCreate");
        System.out.println();

        super.onCreate();
        DaggerCActivityComponent.builder().build().inject(this);

        System.out.println();
        System.out.println("END_CActivity_onCreate");
        System.out.println();
    }

    public void testC() {
//        Log.d(TAG, "testC," + mA.info() + "," + mB.info());
        Log.d(TAG, "testC," + mA.info() + "," + mB.info2() + "," + mC.info2());
    }
}
