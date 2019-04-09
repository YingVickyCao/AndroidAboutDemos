package com.hades.example.android;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hades.example.android.lib.base.NoNeedPermissionActivity;
import com.hades.example.android.resource.theme.apply_theme.ThemeChoosePageAActivity;
import com.hades.example.android.widget.button.TestButtonActivity;
import com.hades.example.android.widget.layout.linearlayout.TestLinearLayoutFragment;

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

    @Override
    protected boolean isShowDetail() {
        return true;
    }

    protected void showCurrentTest() {
        temp();
    }

    private void temp() {
//        showActivity(ThemeChoosePageAActivity.class);
        showActivity(TestButtonActivity.class);
//        showFragment(new TestSpinnerFragment());
        showFragment(new TestLinearLayoutFragment());
//        showActivity(ThemeChoosePageAActivity.class);
    }
}