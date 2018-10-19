package com.hades.android.example.android_about_demos.app_component.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hades.android.example.android_about_demos.R;
import com.hades.android.example.android_about_demos.app_component.activity._sate.TestSaveAndRestoreStateActivity;
import com.hades.android.example.android_about_demos.app_component.activity.lifecycle.A;
import com.hades.android.example.android_about_demos.base.BaseActivity;

public class ActivityTestHomeActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_test_home_layout);
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