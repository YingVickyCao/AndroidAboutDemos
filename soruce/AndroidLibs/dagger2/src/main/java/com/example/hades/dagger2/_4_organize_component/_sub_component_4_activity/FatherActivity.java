package com.example.hades.dagger2._4_organize_component._sub_component_4_activity;

import com.example.hades.dagger2.Log;

import javax.inject.Inject;

public class FatherActivity {
    private static final String TAG = FatherActivity.class.getSimpleName();

    @Inject
    Father mFather;

    @Inject
    Father mFather2;

    protected void onCreate() {
        FatherComponent fatherComponent = DaggerFatherComponent.builder().fatherModule(new FatherModule()).build();
        Log.d(TAG, "onCreate: fatherComponent@" + fatherComponent.hashCode());
        fatherComponent.inject(this);
        click();

        System.out.println();
        openChild();
    }

    private void click() {
        Log.d(TAG, "click: " + mFather.info());
        Log.d(TAG, "click: " + mFather2.info());
    }

    private void openChild() {
        new ChildActivity().onCreate();
    }
}
