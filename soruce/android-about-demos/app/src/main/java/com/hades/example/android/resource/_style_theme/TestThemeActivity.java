package com.hades.example.android.resource._style_theme;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.BaseActivity;

public class TestThemeActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTheme();

        setContentView(R.layout.res_theme);

        initViews();

        showFragment(new TestDeclareStyleableInThemeFragment());
    }

    @Override
    protected boolean isNeedCheckPermission() {
        return false;
    }
}
