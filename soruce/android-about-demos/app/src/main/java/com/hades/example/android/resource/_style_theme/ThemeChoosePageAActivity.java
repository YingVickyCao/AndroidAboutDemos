package com.hades.example.android.resource._style_theme;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.NoNeedPermissionActivity;

public class ThemeChoosePageAActivity extends NoNeedPermissionActivity {

    TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme();
        setContentView(R.layout.res_theme_choose_page);

        initViews();

        textView = findViewById(R.id.textView);

        findViewById(R.id.setLightTheme).setOnClickListener(v -> setLightTheme());
        findViewById(R.id.setDarkTheme).setOnClickListener(v -> setDarkTheme());
        findViewById(R.id.jumpB).setOnClickListener(v -> jumpB());

        findViewById(R.id.color1).setOnClickListener(v -> colorOne());
        findViewById(R.id.color2).setOnClickListener(v -> colorTwo());
        findViewById(R.id.toggleEnable).setOnClickListener(v -> toggleEnable());
        findViewById(R.id.pageThemeAndStyle).setOnClickListener(v -> pageThemeAndStyle());
        findViewById(R.id.pageDeclareStyleable).setOnClickListener(v -> pageDeclareStyleable());
        findViewById(R.id.pageColorFilter).setOnClickListener(v -> pageColorFilter());
        findViewById(R.id.pageTint).setOnClickListener(v -> pageTint());
    }

    private void colorOne() {
        ColorStateList colorStateList = AppCompatResources.getColorStateList(this, R.color.color_v1_1);
        textView.setTextColor(colorStateList);
    }

    private void colorTwo() {
        ColorStateList colorStateList = AppCompatResources.getColorStateList(this, R.color.color_v1_2);
        textView.setTextColor(colorStateList);
    }

    private void toggleEnable() {
        textView.setSelected(!textView.isSelected());
    }

    private void jumpB() {
        startActivity(new Intent(this, ThemeChoosePageBActivity.class));
    }

    private void pageThemeAndStyle() {
        showFragment(new TestAttrInThemeFragment());
    }

    private void pageDeclareStyleable() {
        showFragment(new TestDeclareStyleableInThemeFragment());
    }

    private void pageColorFilter() {
        showFragment(new TestColorFilterFragment());
    }

    private void pageTint() {
        showFragment(new TestTintFragment());
    }
}
