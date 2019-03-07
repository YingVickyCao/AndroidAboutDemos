package com.hades.android.example.android_about_demos._process_and_thread;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hades.android.example.android_about_demos.R;
import com.hades.android.example.android_about_demos._process_and_thread.threadPoolExecutor.ThreadPoolExecutorFragment;
import com.hades.android.example.android_about_demos.base.ver1.BaseActivity;

public class TestProcessAndThreadActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process_and_thread_layout);

        initViews();

        findViewById(R.id.page_threadPoolExecutor).setOnClickListener(v -> page_threadPoolExecutor());
    }

    @Override
    protected void showCurrentTest() {
        page_threadPoolExecutor();
    }

    private void page_threadPoolExecutor() {
        showFragment(new ThreadPoolExecutorFragment());
    }
}