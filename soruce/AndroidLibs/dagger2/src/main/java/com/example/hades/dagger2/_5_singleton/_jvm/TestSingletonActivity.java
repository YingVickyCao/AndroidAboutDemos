package com.example.hades.dagger2._5_singleton._jvm;

import com.example.hades.dagger2.Log;

import javax.inject.Inject;

/*
  TestSingletonActivity instance1
  TestSingletonActivity@38108914,onCreate: component@91289923
  click: World@74663169
  click: World@74663169

  TestSingletonActivity instance2
  TestSingletonActivity@131407999,onCreate: component@91289923
  click: World@74663169
  click: World@74663169
 */
public class TestSingletonActivity {
    private static final String TAG = TestSingletonActivity.class.getSimpleName();
    @Inject
    World mWorld;

    @Inject
    World mWorld2;

    protected void onCreate() {
        /**
         * Component是单例
         */
        WorldComponent component = DaggerWorldComponent.getInstance();
        Log.d(TAG,"=========================>");
        Log.d(TAG, "TestSingletonActivity@" + this.hashCode() + ",onCreate: component@" + component.hashCode());
        component.inject(this);

        click();
        Log.d(TAG,"<=========================");
        System.out.println();
    }

    private void click() {
        Log.d(TAG, "click: " + mWorld.hi());
        Log.d(TAG, "click: " + mWorld2.hi());
    }
}