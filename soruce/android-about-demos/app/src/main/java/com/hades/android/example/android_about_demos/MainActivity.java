package com.hades.android.example.android_about_demos;

import android.os.Bundle;

import com.hades.android.example.android_about_demos.base.ver1.BaseActivity;
import com.hades.android.example.android_about_demos.data_storage.DataStorageActivity;
import com.hades.android.example.android_about_demos.po.security.SecurityActivity;
import com.hades.android.example.android_about_demos.widget.WidgetActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        findViewById(R.id.pageSecurity).setOnClickListener(v -> pageSecurity());
        findViewById(R.id.pageWidget).setOnClickListener(v -> pageWidget());
        findViewById(R.id.pageDateStorage).setOnClickListener(v -> pageDateStorage());
        findViewById(R.id.pageQAAboutView).setOnClickListener(v -> pageQAAboutView());
    }

    @Override
    protected void showCurrentTest() {
        pageDateStorage();
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

    private void pageQAAboutView() {
        showActivity(QAActivity.class);
    }

}
