package com.hades.example.android._bg_tasks;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hades.example.android.R;
import com.hades.example.android._bg_tasks._asynctask.TestAsyncTaskTestFragment;
import com.hades.example.android._bg_tasks.threadPoolExecutor.ThreadPoolExecutorFragment;
import com.hades.example.android.lib.base.NoNeedPermissionActivity;

public class TestBackgroundTasksActivityActivity extends NoNeedPermissionActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bckground_tasks_layout);

        initViews();

        findViewById(R.id.pageThreadPoolExecutor).setOnClickListener(v -> pageThreadPoolExecutor());
        findViewById(R.id.pageAsyncTask).setOnClickListener(v -> pageAsyncTask());
    }

    @Override
    protected void showCurrentTest() {
        pageAsyncTask();
    }

    private void pageThreadPoolExecutor() {
        showFragment(new ThreadPoolExecutorFragment());
    }

    private void pageAsyncTask() {
        showFragment(new TestAsyncTaskTestFragment());
    }
}