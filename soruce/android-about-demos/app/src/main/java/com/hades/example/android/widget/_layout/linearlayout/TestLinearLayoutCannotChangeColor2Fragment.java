package com.hades.example.android.widget._layout.linearlayout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.BaseFragment;

public class TestLinearLayoutCannotChangeColor2Fragment extends BaseFragment {
    private MyLinearLayout mTabLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.widget_layout_linearlayout_4_add_view2, container, false);
        mTabLayout = view.findViewById(R.id.linearLayoutContainer);

        addView(view);
        view.findViewById(R.id.btn1).setOnClickListener(v -> btn1());
        view.findViewById(R.id.btn2).setOnClickListener(v -> btn2());
        view.findViewById(R.id.btn3).setOnClickListener(v -> btn3());
        return view;
    }

    /**
     * <selector>
     * Text Color
     * Bg color
     * <p>
     * View.setSelected
     */
    private void btn1() {
        btn(0);
    }

    private void btn2() {
        btn(1);
    }

    private void btn3() {
        btn(2);
    }

    private void btn(int index) {
        mTabLayout.btn(index);
    }

    private void addView(View view) {
        mTabLayout.addView();
    }

}