package com.hades.android.example.android_about_demos;

import android.os.Bundle;

import com.hades.android.example.android_about_demos.base.ver1.BaseActivity;
import com.hades.android.example.android_about_demos.security.SecurityActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        findViewById(R.id.pageSecurity).setOnClickListener(v -> pageSecurity());
    }

    @Override
    protected void showCurrentTest() {
        pageSecurity();
    }

    private void pageSecurity() {
        showActivity(SecurityActivity.class);
    }
}
