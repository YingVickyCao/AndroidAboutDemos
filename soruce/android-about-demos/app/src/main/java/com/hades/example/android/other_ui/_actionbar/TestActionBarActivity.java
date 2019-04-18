package com.hades.example.android.other_ui._actionbar;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hades.example.android.lib.base.NoNeedPermissionActivity;

public class TestActionBarActivity extends NoNeedPermissionActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getActionBar();
    }
}