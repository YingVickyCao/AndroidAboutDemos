package com.hades.android.example.android_about_demos.po.security.a;

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

        checkPermission("Request custom permission", "com.hades.android.example.android_about_demos.po.security.b.CUSTOM_PERMISSION_TEST");
        findViewById(R.id.jump).setOnClickListener(v -> jump());
    }

    @Override
    protected boolean isNeedCheckPermission() {
        return true;
    }

    private void jump() {
        ComponentName componentName = new ComponentName("com.hades.android.example.android_about_demos.po.security.b",
                "com.hades.android.example.android_about_demos.po.security.b.DActivity");
        Intent intent = new Intent();
        intent.setComponent(componentName);
        intent.putExtra("NUM", 100);
        startActivity(intent);
    }
}