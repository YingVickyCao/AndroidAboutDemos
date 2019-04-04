package com.example.hades.androidpo._4_stability_op;

import android.os.Bundle;

import com.example.hades.androidpo.BaseActivity;
import com.example.hades.androidpo.R;
import com.example.hades.androidpo._4_stability_op.anr.StrictModeFragment;

public class StabilityOPActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_op_stability);

        initViews();
        findViewById(R.id.pageStrictMode).setOnClickListener(v -> pageStrictMode());
    }

    @Override
    protected void showCurrentTest() {
        pageStrictMode();
    }

    private void pageStrictMode() {
        showFragment(new StrictModeFragment());
    }
}
