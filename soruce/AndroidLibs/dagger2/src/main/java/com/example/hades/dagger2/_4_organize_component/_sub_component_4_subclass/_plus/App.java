package com.example.hades.dagger2._4_organize_component._sub_component_4_subclass._plus;

import com.example.hades.dagger2.Log;

import javax.inject.Inject;

public class App {
    private static final String TAG = App.class.getSimpleName();

    @Inject
    A mA;

    @Inject
    A mA2;

    protected AppComponent mAppComponent;

    protected void create() {
        mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
        mAppComponent.inject(this);

        printA();
    }

    private void printA() {
        Log.d(TAG, mA.info() + "," + mA2.info());
    }
}