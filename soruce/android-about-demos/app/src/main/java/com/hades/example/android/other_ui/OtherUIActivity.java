package com.hades.example.android.other_ui;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.NoNeedPermissionActivity;
import com.hades.example.android.other_ui._actionbar.TestActionBarActivity;
import com.hades.example.android.other_ui._notification.TestNotificationFragment;
import com.hades.example.android.other_ui._popup_window.TestPopupWindowFragment;
import com.hades.example.android.other_ui._toast.TestToastFragment;
import com.hades.example.android.other_ui._dialog.TestAlertDialogFragment;
import com.hades.example.android.other_ui._dialog.DateTimePickerDialogFragment;
import com.hades.example.android.other_ui._dialog.DialogStyleActivity;
import com.hades.example.android.other_ui._dialog.ProgressDialogFragment;
import com.hades.example.android.other_ui._dialog.TimePickerDialogFragment;

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
        findViewById(R.id.pageAlertDialog).setOnClickListener(v -> pageAlertDialog());
        findViewById(R.id.pageDialogStyleActivity).setOnClickListener(v -> pageDialogStyleActivity());
        findViewById(R.id.pageProgressDialog).setOnClickListener(v -> pageProgressDialog());
        findViewById(R.id.pageDatePickerDialog).setOnClickListener(v -> pageDatePickerDialog());
        findViewById(R.id.pageTimePickerDialog).setOnClickListener(v -> pageTimePickerDialog());
    }

    @Override
    protected void showCurrentTest() {
        pageAlertDialog();
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

    private void pageAlertDialog() {
        showFragment(new TestAlertDialogFragment());
    }

    private void pageDialogStyleActivity() {
        showActivity(DialogStyleActivity.class);
    }

    private void pageProgressDialog() {
        showFragment(new ProgressDialogFragment());
    }

    private void pageDatePickerDialog() {
        showFragment(new DateTimePickerDialogFragment());
    }

    private void pageTimePickerDialog() {
        showFragment(new TimePickerDialogFragment());
    }
}