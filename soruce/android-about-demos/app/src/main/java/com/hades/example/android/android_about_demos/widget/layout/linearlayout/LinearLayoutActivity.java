package com.hades.example.android.android_about_demos.widget.layout.linearlayout;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hades.example.android.android_about_demos.R;
import com.hades.example.android.android_about_demos.base.ver1.BaseActivity;

public class LinearLayoutActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linearlayout_home_layout);
        initViews();
    }
}
