package com.hades.example.android.a;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;

// <permission>
public class TestCustomPermissionActivity extends BaseActivity4Permission {
    private static final String TAG = TestCustomPermissionActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manifest_cusotom_permission_layout);

        checkPermission("Request custom permission", "com.hades.example.android.b.CUSTOM_PERMISSION_TEST");
        findViewById(R.id.jump).setOnClickListener(v -> jump());
    }

    @Override
    protected boolean isNeedCheckPermission() {
        return true;
    }

    private void jump() {
        ComponentName componentName = new ComponentName("com.hades.example.android.b",
                "com.hades.example.android.b.DActivity");
        Intent intent = new Intent();
        intent.setComponent(componentName);
        intent.putExtra("NUM", 100);
        startActivity(intent);
    }
}