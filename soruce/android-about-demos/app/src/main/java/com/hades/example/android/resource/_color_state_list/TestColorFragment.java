package com.hades.example.android.resource._color_state_list;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.BaseFragment;

public class TestColorFragment extends BaseFragment {

    private int color_red_1 = 0xFFFF0000;
    private int color_red_2 = 0xFF0000;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.res_color, container, false);

        TextView color1 = view.findViewById(R.id.color1);
        TextView color2 = view.findViewById(R.id.color2);
        TextView color3 = view.findViewById(R.id.color3);

        color1.setBackgroundColor(color_red_1);
        color2.setBackgroundColor(color_red_2); // Not working. must 0xAARRGGBB
        color3.setBackgroundColor(Color.GREEN);
        return view;
    }
}