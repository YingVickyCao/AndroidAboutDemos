package com.hades.example.android.po.security;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.NoNeedPermissionActivity;

public class SecurityActivity extends NoNeedPermissionActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_security);
        initViews();

        findViewById(R.id.pageAppChooser).setOnClickListener(v -> pageAppChooser());
    }

    private void pageAppChooser() {
        showFragment(new ShowAppChooserFragment());
    }
}
