package com.hades.example.android;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hades.example.android.data_storage.database.TestSQLiteActivity;
import com.hades.example.android.lib.base.NoNeedPermissionActivity;

public class QAActivity extends NoNeedPermissionActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qa);

        initViews();

        findViewById(R.id.temp).setOnClickListener(v -> temp());
        findViewById(R.id.pageQAAboutView).setOnClickListener(v -> pageQAAboutView());
    }

    @Override
    protected boolean isNeedCheckPermission() {
        return false;
    }

    private void pageQAAboutView() {
        QAAboutFragment fragment = new QAAboutFragment();
        getFragmentManager().beginTransaction().replace(R.id.root, fragment, fragment.getClass().getSimpleName()).commit();
    }

    protected void showCurrentTest() {
        temp();
    }

    private void temp() {
        showActivity(TestSQLiteActivity.class);
    }
}