package com.hades.example.android.resource._style_theme;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.BaseActivity;

public class TestStyleActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTheme();

        setContentView(R.layout.widget_custom_view_activity_layout);

        initViews();

        showFragment(new TestCustomViewFragment());
    }

    @Override
    protected boolean isNeedCheckPermission() {
        return false;
    }
}
