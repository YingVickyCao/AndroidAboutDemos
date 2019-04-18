package com.hades.example.android.resource.theme.apply_theme;

import android.app.TaskStackBuilder;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.NoNeedPermissionActivity;
import com.hades.example.android.lib.mock.SFMock;

public class ThemeChoosePageAActivity extends NoNeedPermissionActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme();
        setContentView(R.layout.res_theme_choose_page);

        findViewById(R.id.setRedTheme).setOnClickListener(v -> doSetRedThemeBtnClick());
        findViewById(R.id.setGreenTheme).setOnClickListener(v -> doSetGreenThemeBtnClick());
        findViewById(R.id.jumpB).setOnClickListener(v -> jumpB());
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
