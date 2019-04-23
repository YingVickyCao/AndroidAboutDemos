package com.hades.example.android.resource.drawable._level_list;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hades.example.android.R;

public class TestLeveListDrawabeFragment extends Fragment {

    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.res_level_iist, container, false);

        textView1 = view.findViewById(R.id.textView1);
        textView2 = view.findViewById(R.id.textView2);
        textView3 = view.findViewById(R.id.textView3);
        textView4 = view.findViewById(R.id.textView4);

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
        textView1.getBackground().setLevel(level);
        textView2.getBackground().setLevel(level);
        textView3.getBackground().setLevel(level);
        textView4.getBackground().setLevel(level);
    }
}
