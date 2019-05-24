package com.hades.example.android.app_component._activity;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.hades.example.android.R;
import com.hades.example.android.app_component._activity._children.TestLauncherActivity;
import com.hades.example.android.app_component._intent_and_intent_filter._launch_mode.D;
import com.hades.example.android.app_component._activity._start_close.AActivity;
import com.hades.example.android.lib.base.NoNeedPermissionActivity;
import com.hades.example.android.resource.adapter_screen.TestSaveAndRestoreStateActivity;

public class TestActivityActivity extends NoNeedPermissionActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_layout);
        initViews();

        findViewById(R.id.pageActivityLifecycle).setOnClickListener(v -> pageActivityLifecycle());
        findViewById(R.id.pageGetViewSizeInActivityOncreate).setOnClickListener(v -> pageGetViewSizeInActivityOnCreate());
        findViewById(R.id.pageSaveAndRestoreState).setOnClickListener(v -> pageSaveAndRestoreState());
        findViewById(R.id.pageLauncherActivity).setOnClickListener(v -> pageLauncherActivity());
        findViewById(R.id.pageLaunchMode).setOnClickListener(v -> pageLaunchMode());
    }

    private void pageActivityLifecycle() {
        showActivity(AActivity.class);
    }

    private void pageGetViewSizeInActivityOnCreate() {
        showActivity(GetViewSizeInOncreateActivity.class);
    }

    private void pageSaveAndRestoreState() {
        showActivity(TestSaveAndRestoreStateActivity.class);
    }

    private void pageLauncherActivity() {
        showActivity(TestLauncherActivity.class);
    }

    private void pageLaunchMode() {
        showActivity(D.class);
    }
}