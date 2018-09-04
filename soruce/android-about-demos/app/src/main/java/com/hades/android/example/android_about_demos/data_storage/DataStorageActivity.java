package com.hades.android.example.android_about_demos.data_storage;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hades.android.example.android_about_demos.R;
import com.hades.android.example.android_about_demos.app_component.activity.BaseActivity;
import com.hades.android.example.android_about_demos.data_storage.shared_preferences.TestSharedPreferencesFragment;

public class DataStorageActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_data_storage_layout);

        initViews();
    }

    private void jumpPageSharedPreferences() {
        showFragment(new TestSharedPreferencesFragment());
    }
}
