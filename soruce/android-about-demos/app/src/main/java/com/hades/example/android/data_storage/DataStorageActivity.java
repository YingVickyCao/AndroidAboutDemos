package com.hades.example.android.data_storage;

import android.Manifest;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.hades.example.android.R;
import com.hades.example.android.data_storage.database.TestSQLiteActivity;
import com.hades.example.android.data_storage.io.TestIOFragment;
import com.hades.example.android.data_storage.shared_preferences.TestSharedPreferencesFragment;
import com.hades.example.android.lib.base.PermissionActivity;

public class DataStorageActivity extends PermissionActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_data_storage_layout);

        initViews();

        findViewById(R.id.pageSharedPreferences).setOnClickListener(v -> pageSharedPreferences());
        findViewById(R.id.pageDatabase).setOnClickListener(v -> pageDatabase());
        findViewById(R.id.pageIO).setOnClickListener(v -> pageIO());
    }

    @Override
    protected void requestPermission() {
        super.requestPermission();

        checkPermission("Request SD card permission", Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    @Override
    protected void showCurrentTest() {
        pageDatabase();
    }

    private void pageSharedPreferences() {
        showFragment(new TestSharedPreferencesFragment());
    }

    private void pageDatabase() {
//        showFragment(new TestDBFragment());
        showActivity(TestSQLiteActivity.class);
    }

    private void pageIO() {
        showFragment(new TestIOFragment());
    }
}
