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

//        pageQAAboutView();
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
        showActivity(TestSQLiteActivity.class);
    }
}