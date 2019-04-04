package com.example.hades.dagger2._1_inject_without_module._constructor_without_parameter;

import com.example.hades.dagger2.Log;

import javax.inject.Inject;

public class TestActivity {
    private static final String TAG = TestActivity.class.getSimpleName();
    @Inject
    A mA;

    @Inject
    A mA2;

    protected void onCreate() {
        DaggerTestActivityComponent.builder().build().inject(this);

        test();
    }

    private void test() {
        Log.d(TAG, mA.info());
        Log.d(TAG, mA2.info());
    }

}
