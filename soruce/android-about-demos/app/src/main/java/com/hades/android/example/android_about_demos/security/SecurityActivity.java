package com.hades.android.example.android_about_demos.security;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hades.android.example.android_about_demos.R;
import com.hades.android.example.android_about_demos.base.BaseActivity;

public class SecurityActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_security);
        initViews();

        findViewById(R.id.pageAppChooser).setOnClickListener(v -> pageAppChooser());
    }

    @Override
    protected void showCurrentTest() {
        pageAppChooser();
    }

    private void pageAppChooser() {
        showFragment(new ShowAppChooserFragment());
    }
}
