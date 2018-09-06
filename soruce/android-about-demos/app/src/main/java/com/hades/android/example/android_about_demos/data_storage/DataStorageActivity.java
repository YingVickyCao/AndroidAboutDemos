package com.hades.android.example.android_about_demos.data_storage;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hades.android.example.android_about_demos.R;
import com.hades.android.example.android_about_demos.app_component.activity.BaseActivity;
import com.hades.android.example.android_about_demos.data_storage.database.test.TestDBFragment;
import com.hades.android.example.android_about_demos.data_storage.io.TestIOFragment;
import com.hades.android.example.android_about_demos.data_storage.shared_preferences.TestSharedPreferencesFragment;

public class DataStorageActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_data_storage_layout);

        initViews();

        findViewById(R.id.pageSharedPreferences).setOnClickListener(v -> pageSharedPreferences());
        findViewById(R.id.pageDatabase).setOnClickListener(v -> pageDatabase());
        findViewById(R.id.pageIO).setOnClickListener(v -> pageIO());
    }

    private void pageSharedPreferences() {
        showFragment(new TestSharedPreferencesFragment());
    }

    private void pageDatabase() {
        showFragment(new TestDBFragment());
    }

    private void pageIO() {
        showFragment(new TestIOFragment());
    }
}
