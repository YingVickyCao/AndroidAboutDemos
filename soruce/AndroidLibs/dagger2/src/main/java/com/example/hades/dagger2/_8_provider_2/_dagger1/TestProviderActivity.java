package com.example.hades.dagger2._8_provider_2._dagger1;

import com.example.hades.dagger2.Log;
import dagger.ObjectGraph;

import javax.inject.Inject1;

public class TestProviderActivity {
    private static final String TAG = TestProviderActivity.class.getSimpleName();

    @Inject1
    Subject mSubject;

    protected void onCreate() {
        inject1();
        click();
    }

    private void inject1() {
        ObjectGraph.create(new TestProviderActivityModule()).inject(this);
    }

    private void click() {
        Log.d(TAG, mSubject.setNum(5));
        Log.d(TAG, mSubject.setNum(10));
    }
}