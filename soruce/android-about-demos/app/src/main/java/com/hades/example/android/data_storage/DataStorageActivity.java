package com.hades.example.android.data_storage;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hades.example.android.R;
import com.hades.example.android.base.ver1.BaseActivity;
import com.hades.example.android.data_storage.database.TestSQLiteActivity;
import com.hades.example.android.data_storage.io.TestIOFragment;
import com.hades.example.android.data_storage.shared_preferences.TestSharedPreferencesFragment;

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
