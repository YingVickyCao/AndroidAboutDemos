package com.hades.example.android.app_component.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.hades.example.android.R;
import com.hades.example.android.app_component.activity._life_cycle.A;
import com.hades.example.android.resource.adapter_screen.TestSaveAndRestoreStateActivity;
import com.hades.example.android.lib.base.NoNeedPermissionActivity;

public class ActivityTestHomeActivity extends NoNeedPermissionActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_home_layout);
        initViews();

        findViewById(R.id.pageActivityLifecycle).setOnClickListener(v -> pageActivityLifecycle());
        findViewById(R.id.pageGetViewSizeInActivityOncreate).setOnClickListener(v -> pageGetViewSizeInActivityOncreate());
        findViewById(R.id.pageSaveAndRestoreState).setOnClickListener(v -> pageSaveAndRestoreState());
    }

    private void pageActivityLifecycle() {
        showActivity(A.class);
    }

    private void pageGetViewSizeInActivityOncreate() {
        showActivity(GetViewSizeInOncreateActivity.class);
    }

    private void pageSaveAndRestoreState() {
        showActivity(TestSaveAndRestoreStateActivity.class);
    }
}