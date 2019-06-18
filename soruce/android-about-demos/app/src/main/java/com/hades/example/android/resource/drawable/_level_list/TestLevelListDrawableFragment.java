package com.hades.example.android.resource.drawable._level_list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.BaseFragment;

public class TestLevelListDrawableFragment extends BaseFragment {

    private TextView textView1_1;
    private TextView textView1_2;

    private TextView textView2_1;
    private TextView textView2_2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.res_drawable_level_iist, container, false);

        textView1_1 = view.findViewById(R.id.textView1_1);
        textView1_2 = view.findViewById(R.id.textView1_2);

        textView2_1 = view.findViewById(R.id.textView2_1);
        textView2_2 = view.findViewById(R.id.textView2_2);

        view.findViewById(R.id.setMaxLevel0).setOnClickListener(v -> setMaxLevel0());
        view.findViewById(R.id.setMaxLevel1).setOnClickListener(v -> setMaxLevel1());
        return view;
    }

    private void setMaxLevel0() {
        setLevel(0);
    }

    private void setMaxLevel1() {
        setLevel(1);
    }

    private void setLevel(int level) {
        textView1_1.getBackground().setLevel(level);
        textView1_2.getBackground().setLevel(level);

        textView2_1.getBackground().setLevel(level);
        textView2_2.getBackground().setLevel(level);
    }
}