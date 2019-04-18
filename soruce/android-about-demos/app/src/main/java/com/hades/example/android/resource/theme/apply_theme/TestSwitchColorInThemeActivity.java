package com.hades.example.android.resource.theme.apply_theme;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.content.res.AppCompatResources;
import android.widget.TextView;

import com.hades.example.android.R;

public class TestSwitchColorInThemeActivity extends ThemeChoosePageAActivity {
    TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme();

        setContentView(R.layout.res_theme_switch_color);

        textView = findViewById(R.id.textView);

        findViewById(R.id.setRedTheme).setOnClickListener(v -> doSetRedThemeBtnClick());
        findViewById(R.id.setGreenTheme).setOnClickListener(v -> doSetGreenThemeBtnClick());

        findViewById(R.id.colorOne).setOnClickListener(v -> colorOne());
        findViewById(R.id.colorTwo).setOnClickListener(v -> colorTwo());
        findViewById(R.id.toggleEnable).setOnClickListener(v -> toggleEnable());
    }

    private void colorOne() {
        ColorStateList colorStateList = AppCompatResources.getColorStateList(this, R.color.color_v1_one);
        textView.setTextColor(colorStateList);
    }

    private void colorTwo() {
        ColorStateList colorStateList = AppCompatResources.getColorStateList(this, R.color.color_v1_two);
        textView.setTextColor(colorStateList);
    }

    private void toggleEnable() {
        textView.setSelected(!textView.isSelected());
    }
}