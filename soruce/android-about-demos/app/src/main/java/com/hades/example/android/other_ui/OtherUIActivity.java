package com.hades.example.android.other_ui;

import android.os.Bundle;
import androidx.annotation.Nullable;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.NoNeedPermissionActivity;
import com.hades.example.android.other_ui._actionbar.TestActionBarActivity;
import com.hades.example.android.other_ui._actionbar.TestUseActionBarsShowMenuItemActivity;
import com.hades.example.android.other_ui._toast.ToastFragment;
import com.hades.example.android.other_ui._notification.TestNotificationFragment;

public class OtherUIActivity extends NoNeedPermissionActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_ui_layout);

        initViews();

        findViewById(R.id.pageNotification).setOnClickListener(v -> pageNotification());
        findViewById(R.id.pageToast).setOnClickListener(v -> pageToast());
        findViewById(R.id.pageActionBar).setOnClickListener(v -> pageActionBar());
        findViewById(R.id.pageActionBarShowMenu).setOnClickListener(v -> pageActionBarShowMenu());
    }

    private void pageNotification() {
        showFragment(new TestNotificationFragment());
    }

    private void pageToast() {
        showFragment(ToastFragment.newInstance(), ToastFragment.class.getSimpleName());
    }

    private void pageActionBar() {
        showActivity(TestActionBarActivity.class);
    }

    private void pageActionBarShowMenu() {
        showActivity(TestUseActionBarsShowMenuItemActivity.class);
    }

}