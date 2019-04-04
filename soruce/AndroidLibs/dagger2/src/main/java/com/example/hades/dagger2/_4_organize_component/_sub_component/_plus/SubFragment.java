package com.example.hades.dagger2._4_organize_component._sub_component._plus;


import com.example.hades.dagger2.Log;

import javax.inject.Inject;

public class SubFragment {
    public static final String TAG = SubFragment.class.getSimpleName();

    @Inject
    A mA1;

    @Inject
    A mA2;

    @Inject
    B mB1;

    @Inject
    B mB2;

    public void onCreateView() {
        /**
         SubFragmentComponent component = ((ComponentActivity) getActivity()).getActivityComponent().getMySubComponent();
         component.inject(this);
         Log.d(TAG, TAG + "@" + hashCode() + ",onCreate:" + SubFragmentComponent.class.getSimpleName() + "@" + component.hashCode());
         */

        SubFragmentComponent component = ComponentActivity.getActivity().getActivityComponent().plus(new SubFragmentModule());
        Log.d(TAG, "SubFragmentComponent@" + component.hashCode());
        component.inject(this);

        click();
    }


    private void click() {
        Log.d(TAG, "click:mA1=" + mA1.info() + ",mA2=" + mA2.info());
        Log.d(TAG, "click:mB1=" + mB1.info() + ",mB2=" + mB2.info());
    }
}
