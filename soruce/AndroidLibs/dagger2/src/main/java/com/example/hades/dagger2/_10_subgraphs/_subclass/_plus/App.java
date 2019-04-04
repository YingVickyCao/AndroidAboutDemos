package com.example.hades.dagger2._10_subgraphs._subclass._plus;

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
        System.out.println("============ App start =============");

        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))// this:app
//                .app(this)// this:app
                .build();
        mAppComponent.inject(this);

        printA();
        System.out.println("============ App end =============");
    }

    private void printA() {
        Log.d(TAG, mA.info() + "," + mA2.info());
    }
}