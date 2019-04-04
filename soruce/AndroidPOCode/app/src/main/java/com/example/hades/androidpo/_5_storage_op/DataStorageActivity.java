package com.example.hades.androidpo._5_storage_op;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.hades.androidpo.BaseActivity;
import com.example.hades.androidpo.R;
import com.example.hades.androidpo._5_storage_op.shared_prefs.TestSharedPreferencesFragment;

public class DataStorageActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_op_storage_layout);

        initViews();

        findViewById(R.id.pageSharedPreferences).setOnClickListener(v -> pageSharedPreferences());
    }

    private void pageSharedPreferences() {
        showFragment(new TestSharedPreferencesFragment());
    }
}