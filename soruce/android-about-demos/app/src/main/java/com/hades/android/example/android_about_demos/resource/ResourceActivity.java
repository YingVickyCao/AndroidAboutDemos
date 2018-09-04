package com.hades.android.example.android_about_demos.resource;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hades.android.example.android_about_demos.R;
import com.hades.android.example.android_about_demos.app_component.activity.BaseActivity;
import com.hades.android.example.android_about_demos.resource.array.ArrayFragment;
import com.hades.android.example.android_about_demos.resource.i18n.InternationalizationFragment;
import com.hades.android.example.android_about_demos.resource.material.MaterialFragment;
import com.hades.android.example.android_about_demos.resource.theme.apply_theme.ThemeChoosePageAActivity;
import com.hades.android.example.android_about_demos.resource.xml.ParseXmlFragment;

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

        findViewById(R.id.parseXml).setOnClickListener(v -> parseXml());
        findViewById(R.id.theme).setOnClickListener(v -> theme());
        findViewById(R.id.play_Raw_asserts).setOnClickListener(v -> play_Raw_asserts());
        findViewById(R.id.internationalization).setOnClickListener(v -> internationalization());
        findViewById(R.id.array).setOnClickListener(v -> array());
    }

    @Override
    protected void showCurrentTest() {
        array();
    }

    private void parseXml() {
        showFragment(new ParseXmlFragment());
    }

    private void theme() {
        showActivity(ThemeChoosePageAActivity.class);
    }

    private void play_Raw_asserts() {
        showFragment(new MaterialFragment());
    }

    private void internationalization() {
        showFragment(new InternationalizationFragment());
    }

    private void array() {
        showFragment(new ArrayFragment());
    }
}
