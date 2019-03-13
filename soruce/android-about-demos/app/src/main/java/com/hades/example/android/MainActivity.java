package com.hades.example.android;

import android.os.Bundle;

import com.hades.example.android.android_about_demos.R;
import com.hades.example.android.base.ver1.BaseActivity;
import com.hades.example.android.data_storage.DataStorageActivity;
import com.hades.example.android.po.security.SecurityActivity;
import com.hades.example.android.resource.ResourceActivity;
import com.hades.example.android.widget.WidgetActivity;

public class MainActivity extends BaseActivity {

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
        pageResource();
    }

    private void pageSecurity() {
        showActivity(SecurityActivity.class);
    }

    private void pageWidget() {
        showActivity(WidgetActivity.class);
    }

    private void pageDateStorage() {
        showActivity(DataStorageActivity.class);
    }

    private void pageResource() {
        showActivity(ResourceActivity.class);
    }

    private void pageQAAboutView() {
        showActivity(QAActivity.class);
    }

}
