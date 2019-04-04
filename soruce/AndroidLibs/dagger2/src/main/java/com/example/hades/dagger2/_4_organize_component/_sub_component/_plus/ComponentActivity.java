package com.example.hades.dagger2._4_organize_component._sub_component._plus;

import com.example.hades.dagger2.Log;

import javax.inject.Inject;

public class ComponentActivity {
    private static final String TAG = ComponentActivity.class.getSimpleName();

    @Inject
    A mA;

    @Inject
    A mA2;

    private ComponentActivityComponent component;

    private static ComponentActivity mActivity;

    public static ComponentActivity getActivity() {
        if (null == mActivity) {
            mActivity = new ComponentActivity();
        }
        return mActivity;
    }

    protected void onCreate() {
        component = DaggerComponentActivityComponent.builder()
                .componentActivityModule(new ComponentActivityModule())
                .build();
        component.inject(this);
        Log.d(TAG, TAG + "@" + hashCode()
                + ",onCreate:"
                + ","
                + component.getClass().getSimpleName() + "@" + component.hashCode());

        click();

        System.out.println();
        openFragment();
    }

    private void click() {
        Log.d(TAG, "click: " + mA.info());
        Log.d(TAG, "click: " + mA2.info());
    }

    private void openFragment() {
        new SubFragment().onCreateView();
        System.out.println();

        new SubFragment().onCreateView();
    }

    public ComponentActivityComponent getActivityComponent() {
        return component;
    }
}
