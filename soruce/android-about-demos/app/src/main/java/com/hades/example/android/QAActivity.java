package com.hades.example.android;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hades.example.android.app_component.service.unbounservice.StartServiceTest1Activity;
import com.hades.example.android.lib.base.NoNeedPermissionActivity;

public class QAActivity extends NoNeedPermissionActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qa);

        initViews();

        findViewById(R.id.temp).setOnClickListener(v -> temp());
    }

    @Override
    protected boolean isNeedCheckPermission() {
        return false;
    }

    @Override
    protected boolean isShowDetail() {
        return true;
    }

    protected void showCurrentTest() {
        temp();
    }

    private void temp() {
        showActivity(StartServiceTest1Activity.class);
    }
}