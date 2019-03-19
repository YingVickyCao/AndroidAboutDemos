package com.hades.example.android;

import android.os.Bundle;

import com.hades.example.android.base.ver1.HasPermissionActivity;
import com.hades.example.android.data_storage.DataStorageActivityActivity;
import com.hades.example.android.po.security.SecurityActivityActivity;
import com.hades.example.android.resource.ResourceActivityActivity;
import com.hades.example.android.widget.WidgetActivityActivity;

public class MainActivity extends HasPermissionActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        findViewById(R.id.pageSecurity).setOnClickListener(v -> pageSecurity());
        findViewById(R.id.pageWidget).setOnClickListener(v -> pageWidget());
        findViewById(R.id.pageDateStorage).setOnClickListener(v -> pageDateStorage());
        findViewById(R.id.pageResource).setOnClickListener(v -> pageResource());
        findViewById(R.id.pageQAAboutView).setOnClickListener(v -> pageQAAboutView());
    }

    @Override
    protected void showCurrentTest() {
//        pageResource();
    }

    private void pageSecurity() {
        showActivity(SecurityActivityActivity.class);
    }

    private void pageWidget() {
        showActivity(WidgetActivityActivity.class);
    }

    private void pageDateStorage() {
        showActivity(DataStorageActivityActivity.class);
    }

    private void pageResource() {
        showActivity(ResourceActivityActivity.class);
    }

    private void pageQAAboutView() {
        showActivity(QAActivity.class);
    }

}
