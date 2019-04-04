package com.example.hades.dagger2._2_inject_with_module._constructor_without_parameter;


import com.example.hades.dagger2.Log;

import javax.inject.Inject;

public class AActivity {
    private static final String TAG = AActivity.class.getSimpleName();
    @Inject
    A mA;

    @Inject
    A mA2;


    void onCreate() {
        AActivityComponent component = DaggerAActivityComponent.builder().build();
        component.inject(this);

        test();
    }

    private void test() {
        Log.d(TAG, mA.info());
        Log.d(TAG, mA2.info());
    }
}
