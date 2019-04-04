package com.example.hades.dagger2._4_organize_component._sub_component_4_activity;


import com.example.hades.dagger2.Log;

import javax.inject.Inject;

public class ChildActivity {
    private static final String TAG = ChildActivity.class.getSimpleName();

    @Inject
    Child mB;

    @Inject
    Child mB2;


    protected void onCreate() {

        FatherComponent fatherComponent = DaggerFatherComponent.create();
        Log.d(TAG, "onCreate: fatherComponent@" + fatherComponent.hashCode());
        fatherComponent.buildChildComponent().build().inject(this);
        click();
    }

    private void click() {
        Log.d(TAG, "click: " + mB.info());
        Log.d(TAG, "click: " + mB2.info());
    }
}
