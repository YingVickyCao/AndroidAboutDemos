package com.hades.example.android.widget._layout.linearlayout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

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