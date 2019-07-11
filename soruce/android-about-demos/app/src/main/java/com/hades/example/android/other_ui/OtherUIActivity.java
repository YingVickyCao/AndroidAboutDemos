package com.hades.example.android.other_ui;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.NoNeedPermissionActivity;
import com.hades.example.android.other_ui._actionbar.TestActionBarActivity;
import com.hades.example.android.other_ui._dialog.TestDialogActivity;
import com.hades.example.android.other_ui._notification.TestNotificationFragment;
import com.hades.example.android.other_ui._popup_window.TestPopupWindowFragment;
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
        findViewById(R.id.pageDialog).setOnClickListener(v -> pageDialog());
    }

    @Override
    protected void showCurrentTest() {
        pagePopupWindow();
    }

    private void pageNotification() {
        showFragment(new TestNotificationFragment());
    }

    private void pageToast() {
        showFragment(new TestToastFragment());
    }

    private void pageActionBar() {
        showActivity(TestActionBarActivity.class);
    }

    private void pagePopupWindow() {
        showFragment(new TestPopupWindowFragment());
    }

    private void pageDialog() {
        showActivity(TestDialogActivity.class);
    }
}