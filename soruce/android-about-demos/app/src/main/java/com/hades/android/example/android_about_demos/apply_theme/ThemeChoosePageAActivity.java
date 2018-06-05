package com.hades.android.example.android_about_demos.apply_theme;

import android.app.Activity;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hades.android.example.android_about_demos.R;

public class ThemeChoosePageAActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme();
        setContentView(R.layout.theme_choose_page);

        findViewById(R.id.setRedTheme).setOnClickListener(v -> doSetRedThemeBtnClick());
        findViewById(R.id.setGreenTheme).setOnClickListener(v -> doSetGreenThemeBtnClick());
        findViewById(R.id.jumpB).setOnClickListener(v -> jumpB());
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
    }

    protected void doSetRedThemeBtnClick() {
        boolean isRedTheme = SFMock.getInstance().isRedTheme();
        if (isRedTheme) {
            return;
        }
        SFMock.getInstance().useRedTheme(true);

        applyTheme();
    }

    void doSetGreenThemeBtnClick() {
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

    private void jumpB() {
        startActivity(new Intent(this, ThemeChoosePageBActivity.class));
    }

    void setTheme() {
        boolean isRedTheme = SFMock.getInstance().isRedTheme();
        if (isRedTheme) {
            setTheme(R.style.AppTheme_Red);
        } else {
            setTheme(R.style.AppTheme_Green);
        }
    }
}
