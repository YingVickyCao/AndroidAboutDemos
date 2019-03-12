package com.hades.example.android.a;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hades.example.android.a.app_component.Intent_and_intent_filter.TestAccessRemoteActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manifest_main_layout);
        findViewById(R.id.pageCrossAppAccess).setOnClickListener(v -> pageCrossAppAccess());
        findViewById(R.id.pageCustomPermission).setOnClickListener(v -> pageCustomPermission());
        findViewById(R.id.pageAccessRemoteActivityUseImplicitIntent).setOnClickListener(v -> pageAccessRemoteActivityUseImplicitIntent());
    }

    private void pageCrossAppAccess() {
        openActivity(AActivity.class);
    }

    private void pageCustomPermission() {
        openActivity(TestCustomPermissionActivity.class);
    }

    private void openActivity(Class<?> cls) {
        startActivity(new Intent(this, cls));
    }

    private void pageAccessRemoteActivityUseImplicitIntent() {
        startActivity(new Intent(this, TestAccessRemoteActivity.class));
    }
}