package com.hades.example.android.po.security;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hades.example.android.R;
import com.hades.example.android.base.ver1.BaseActivity;

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
