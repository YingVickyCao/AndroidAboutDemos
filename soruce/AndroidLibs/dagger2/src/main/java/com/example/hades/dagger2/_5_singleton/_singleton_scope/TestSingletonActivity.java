package com.example.hades.dagger2._5_singleton._singleton_scope;

import com.example.hades.dagger2.Log;

import javax.inject.Inject;

/*
    TestSingletonActivity instance1
    TestSingletonActivity@220594159,onCreate: component@251853564
    click: Hello@152650370
    click: Hello@152650370

    TestSingletonActivity instance2
    TestSingletonActivity@73993195,onCreate: component@85843272
    click: Hello@121748649
    click: Hello@121748649
 */
public class TestSingletonActivity {
    private static final String TAG = TestSingletonActivity.class.getSimpleName();
    @Inject
    Hello mHello;

    @Inject
    Hello mHello2;

    protected void onCreate() {
        HelloComponent component = DaggerHelloComponent.builder().helloModule(new HelloModule()).build();

        Log.d(TAG,"=========================>");
        Log.d(TAG, TAG + "@" + this.hashCode() + ",onCreate: component@" + component.hashCode());
        component.inject(this);

        click();
        Log.d(TAG,"<=========================");
        System.out.println();
    }

    private void click() {
        Log.d(TAG, "click: " + mHello.hi());
        Log.d(TAG, "click: " + mHello2.hi());
    }
}