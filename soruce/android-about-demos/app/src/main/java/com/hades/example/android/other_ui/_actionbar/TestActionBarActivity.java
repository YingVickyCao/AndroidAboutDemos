package com.hades.example.android.other_ui._actionbar;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.NoNeedPermissionActivity;

public class TestActionBarActivity extends NoNeedPermissionActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.other_ui_action_bar);

        findViewById(R.id.show).setOnClickListener(v -> show());
        findViewById(R.id.hide).setOnClickListener(v -> hide());
    }

    private void show() {
        ActionBar actionBar = getSupportActionBar();
        if (null == actionBar) {
            return;
        }
        actionBar.show();
    }

    private void hide() {
        showToast("Hide");
        ActionBar actionBar = getSupportActionBar();
        if (null == actionBar) {
            return;
        }
        actionBar.hide();
    }
}