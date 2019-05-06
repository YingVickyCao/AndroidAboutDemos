package com.hades.example.android.other_ui;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.NoNeedPermissionActivity;
import com.hades.example.android.other_ui._actionbar.TestActionBarActivity;
import com.hades.example.android.other_ui._notification.TestNotificationFragment;
import com.hades.example.android.other_ui._toast.TestToastFragment;

public class OtherUIActivity extends NoNeedPermissionActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_ui_layout);

        initViews();

        findViewById(R.id.pageNotification).setOnClickListener(v -> pageNotification());
        findViewById(R.id.pageToast).setOnClickListener(v -> pageToast());
        findViewById(R.id.pageActionBar).setOnClickListener(v -> pageActionBar());
        findViewById(R.id.pagePopupWindow).setOnClickListener(v -> pagePopupWindow());
    }

    @Override
    protected void showCurrentTest() {
        pageToast();
    }

    private void pageNotification() {
        showFragment(new TestNotificationFragment());
    }

    private void pageToast() {
        showFragment(TestToastFragment.newInstance(), TestToastFragment.class.getSimpleName());
    }

    private void pageActionBar() {
        showActivity(TestActionBarActivity.class);
    }


    private void pagePopupWindow() {
        showFragment(new TestPopupWindowFragment());
    }

}