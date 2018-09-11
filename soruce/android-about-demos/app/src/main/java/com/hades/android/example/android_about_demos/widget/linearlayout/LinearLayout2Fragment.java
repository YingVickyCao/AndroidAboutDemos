package com.hades.android.example.android_about_demos.widget.linearlayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hades.android.example.android_about_demos.R;
import com.hades.android.example.android_about_demos.base.BaseFragment;

public class LinearLayout2Fragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.widget_linearlayout_2_layout, container, false);
        return view;
    }
}
