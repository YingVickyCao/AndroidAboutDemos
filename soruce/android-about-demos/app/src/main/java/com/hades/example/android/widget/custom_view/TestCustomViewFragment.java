package com.hades.example.android.widget.custom_view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.ThemeBaseFragment;

public class TestCustomViewFragment extends ThemeBaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.widget_custom_view, container, false);
        view.findViewById(R.id.setLightTheme).setOnClickListener(v -> setLightTheme());
        view.findViewById(R.id.setDarkTheme).setOnClickListener(v -> setDarkTheme());
        return view;
    }
}
