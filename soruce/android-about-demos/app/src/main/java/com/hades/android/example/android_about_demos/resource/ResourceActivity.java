package com.hades.android.example.android_about_demos.resource;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hades.android.example.android_about_demos.R;
import com.hades.android.example.android_about_demos.base.BaseActivity;
import com.hades.android.example.android_about_demos.resource.array.ArrayFragment;
import com.hades.android.example.android_about_demos.resource.drawable.DrawableFragment;
import com.hades.android.example.android_about_demos.resource.i18n.InternationalizationFragment;
import com.hades.android.example.android_about_demos.resource.material.MaterialFragment;
import com.hades.android.example.android_about_demos.resource.theme.apply_theme.ThemeChoosePageAActivity;

/**
 * https://www.cnblogs.com/andriod-html5/archive/2012/04/30/2539419.html
 */
public class ResourceActivity extends BaseActivity {
    private static final String TAG = ResourceActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources);
        initViews();

        findViewById(R.id.parseMaterial).setOnClickListener(v -> parseXml());
        findViewById(R.id.theme).setOnClickListener(v -> theme());
        findViewById(R.id.internationalization).setOnClickListener(v -> internationalization());
        findViewById(R.id.array).setOnClickListener(v -> array());
        findViewById(R.id.pageDrawable).setOnClickListener(v -> pageDrawable());
    }

    @Override
    protected void showCurrentTest() {
        array();
    }

    private void parseXml() {
        showFragment(new MaterialFragment());
    }

    private void theme() {
        showActivity(ThemeChoosePageAActivity.class);
    }

    private void internationalization() {
        showFragment(new InternationalizationFragment());
    }

    private void array() {
        showFragment(new ArrayFragment());
    }

    private void pageDrawable() {
        showFragment(new DrawableFragment());
    }
}
