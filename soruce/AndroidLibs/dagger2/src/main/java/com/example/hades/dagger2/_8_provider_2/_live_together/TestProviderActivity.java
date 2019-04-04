package com.example.hades.dagger2._8_provider_2._live_together;

import com.example.hades.dagger2.Log;
import dagger.ObjectGraph;

import javax.inject.Inject;
import javax.inject.Inject1;

public class TestProviderActivity {
    private static final String TAG = TestProviderActivity.class.getSimpleName();

    @Inject
    @Inject1
    Subject mSubject;

    protected void onCreate() {
        inject1();
        inject2();

        click();
    }

    private void inject1() {
        ObjectGraph.create(new TestProviderActivityModule()).inject(this);
    }

    private void inject2() {
        DaggerTestProviderActivityComponent4Dagger2.builder().build().inject(this);
    }

    private void click() {
        Log.d(TAG, mSubject.setNum(5));
        Log.d(TAG, mSubject.setNum(10));
    }
}