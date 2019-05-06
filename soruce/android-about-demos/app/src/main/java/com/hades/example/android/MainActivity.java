package com.hades.example.android;

import android.os.Bundle;
import android.view.View;

import com.hades.example.android.data_storage.DataStorageActivity;
import com.hades.example.android.lib.base.NoNeedPermissionActivity;
import com.hades.example.android.po.security.SecurityActivity;
import com.hades.example.android.resource.ResourceActivity;
import com.hades.example.android.widget.WidgetActivity;

public class MainActivity extends NoNeedPermissionActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        findViewById(R.id.pageSecurity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pageSecurity();
            }
        });
        findViewById(R.id.pageWidget).setOnClickListener(v -> pageWidget());
        findViewById(R.id.pageDateStorage).setOnClickListener(v -> pageDateStorage());
        findViewById(R.id.pageResource).setOnClickListener(v -> pageResource());
        findViewById(R.id.pageQAAboutView).setOnClickListener(v -> pageQAAboutView());
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
