package com.hades.example.android.a;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manifest_main_layout);
        findViewById(R.id.pageCrossAppAccess).setOnClickListener(v -> pageCrossAppAccess());
        findViewById(R.id.pageCustomPermission).setOnClickListener(v -> pageCustomPermission());
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
}