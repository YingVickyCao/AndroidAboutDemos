package com.hades.example.android.resource.theme.apply_theme;

import android.app.TaskStackBuilder;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.content.res.AppCompatResources;
import android.widget.TextView;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.NoNeedPermissionActivity;
import com.hades.example.android.lib.mock.SFMock;

public class ThemeChoosePageAActivity extends NoNeedPermissionActivity {
    TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme();
        setContentView(R.layout.res_theme_choose_page);

        textView = findViewById(R.id.textView);

        findViewById(R.id.setRedTheme).setOnClickListener(v -> setRedTheme());
        findViewById(R.id.setGreenTheme).setOnClickListener(v -> setGreenTheme());
        findViewById(R.id.jumpB).setOnClickListener(v -> jumpB());

        findViewById(R.id.selectTab1).setOnClickListener(v -> colorOne());
        findViewById(R.id.selectTab2).setOnClickListener(v -> colorTwo());
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

    protected void setRedTheme() {
        boolean isRedTheme = SFMock.getInstance().isRedTheme();
        if (isRedTheme) {
            return;
        }
        SFMock.getInstance().useRedTheme(true);

        applyTheme();
    }

    void setGreenTheme() {
        boolean isRedTheme = SFMock.getInstance().isRedTheme();
        if (!isRedTheme) {
            return;
        }
        SFMock.getInstance().useRedTheme(false);

        applyTheme();
    }

    private void applyTheme() {
        finish();
        TaskStackBuilder.create(this).addNextIntent(getIntent()).startActivities();
    }

    void setTheme() {
        boolean isRedTheme = SFMock.getInstance().isRedTheme();
        if (isRedTheme) {
            setTheme(R.style.AppTheme_Red);
        } else {
            setTheme(R.style.AppTheme_Green);
        }
    }

    private void jumpB() {
        startActivity(new Intent(this, ThemeChoosePageBActivity.class));
    }
}
