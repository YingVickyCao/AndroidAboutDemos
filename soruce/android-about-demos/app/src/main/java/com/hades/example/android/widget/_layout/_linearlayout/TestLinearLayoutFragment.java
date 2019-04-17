package com.hades.example.android.widget._layout._linearlayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.BaseFragment;

public class TestLinearLayoutFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.widget_layout_linearlayout_4_weight, container, false);
        return inflater.inflate(R.layout.widget_layout_linearlayout_4_divider, container, false);
    }
}