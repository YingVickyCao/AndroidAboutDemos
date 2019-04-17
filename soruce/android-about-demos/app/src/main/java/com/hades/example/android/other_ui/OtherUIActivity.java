package com.hades.example.android.other_ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.NoNeedPermissionActivity;
import com.hades.example.android.other_ui.notifiaction.TestNotificationFragment;

public class OtherUIActivity extends NoNeedPermissionActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_ui_layout);

        initViews();

        findViewById(R.id.testNotification).setOnClickListener(v -> testNotification());
    }

    private void testNotification() {
        showFragment(new TestNotificationFragment());
    }
}
