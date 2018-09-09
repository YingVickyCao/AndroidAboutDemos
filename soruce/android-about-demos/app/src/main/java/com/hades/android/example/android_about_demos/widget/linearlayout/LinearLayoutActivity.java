package com.hades.android.example.android_about_demos.widget.linearlayout;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hades.android.example.android_about_demos.R;
import com.hades.android.example.android_about_demos.base.BaseActivity;

public class LinearLayoutActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linearlayout_home_layout);

        initViews();

        findViewById(R.id.linearlayout1).setOnClickListener(v -> linearlayout1());
        findViewById(R.id.linearlayout2).setOnClickListener(v -> linearlayout2());
        findViewById(R.id.linearlayout3).setOnClickListener(v -> linearlayout3());
    }

    private void linearlayout1() {
        showFragment(new LinearLayout1Fragment());
    }


    private void linearlayout2() {
        showFragment(new LinearLayout2Fragment());
    }

    private void linearlayout3() {
        showFragment(new LinearLayout3Fragment());
    }
}
