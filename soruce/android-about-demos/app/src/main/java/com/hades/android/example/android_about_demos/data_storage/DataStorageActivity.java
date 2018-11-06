package com.hades.android.example.android_about_demos.data_storage;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hades.android.example.android_about_demos.R;
import com.hades.android.example.android_about_demos.base.BaseActivity;
import com.hades.android.example.android_about_demos.data_storage.db.TestDBFragment;
import com.hades.android.example.android_about_demos.data_storage.io.TestIOFragment;
import com.hades.android.example.android_about_demos.data_storage.shared_preferences.IOSharedPreferencesFragment;

public class DataStorageActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_data_storage_layout);

        checkPermission("Request SD card permission", Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        initViews();

        findViewById(R.id.pageSharedPreferences).setOnClickListener(v -> pageSharedPreferences());
        findViewById(R.id.pageDatabase).setOnClickListener(v -> pageDatabase());
        findViewById(R.id.pageIO).setOnClickListener(v -> pageIO());
    }

    @Override
    protected boolean isNeedCheckPermission() {
        return true;
    }

    @Override
    protected void showCurrentTest() {
        pageSharedPreferences();
    }

    private void pageSharedPreferences() {
        showFragment(new IOSharedPreferencesFragment());
    }

    private void pageDatabase() {
        showFragment(new TestDBFragment());
    }

    private void pageIO() {
        showFragment(new TestIOFragment());
    }
}
