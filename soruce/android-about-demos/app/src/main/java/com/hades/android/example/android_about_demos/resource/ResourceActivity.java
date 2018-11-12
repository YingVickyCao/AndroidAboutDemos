package com.hades.android.example.android_about_demos.resource;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hades.android.example.android_about_demos.R;
import com.hades.android.example.android_about_demos.base.ver1.BaseActivity;
import com.hades.android.example.android_about_demos.resource.adapter_screen.DisableRotateActivity;
import com.hades.android.example.android_about_demos.resource.adapter_screen.ScreenSizeActivity;
import com.hades.android.example.android_about_demos.resource.array.ArrayFragment;
import com.hades.android.example.android_about_demos.resource.drawable.bitmap.BitmapDrawableFragment;
import com.hades.android.example.android_about_demos.resource.drawable.bitmap.ManagingBitmapMemoryFragment;
import com.hades.android.example.android_about_demos.resource.drawable.vector.MeasureVectorDrawableFragment;
import com.hades.android.example.android_about_demos.resource.drawable.vector.VectorDrawableFragment;
import com.hades.android.example.android_about_demos.resource.i18n.InternationalizationFragment;
import com.hades.android.example.android_about_demos.resource.material.MaterialFragment;
import com.hades.android.example.android_about_demos.resource.theme.apply_theme.ThemeChoosePageAActivity;
import com.hades.android.example.android_about_demos.resource.xml.ParseXMLFragment;

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
        findViewById(R.id.parseXML).setOnClickListener(v -> parseXML());
        findViewById(R.id.pageScreenOrientation).setOnClickListener(v -> pageScreenOrientation());
        findViewById(R.id.pageScreenSize).setOnClickListener(v -> pageScreenSize());
        findViewById(R.id.pageDrawableAndScreenDensity).setOnClickListener(v -> pageDrawableAndScreenDensity());
        findViewById(R.id.pageVectorDrawable).setOnClickListener(v -> pageVectorDrawable());
        findViewById(R.id.pageCheckVectorRenderingTime).setOnClickListener(v -> pageMeasureVectorRenderingTime());
        findViewById(R.id.pageManagingBitmapMemory).setOnClickListener(v -> pageManagingBitmapMemory());
    }

    @Override
    protected void showCurrentTest() {
        pageManagingBitmapMemory();
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

    private void parseXML() {
        showFragment(new ParseXMLFragment());
    }

    private void pageScreenOrientation() {
        showActivity(DisableRotateActivity.class);
    }

    private void pageScreenSize() {
        showActivity(ScreenSizeActivity.class);
    }

    private void pageDrawableAndScreenDensity() {
        showFragment(new BitmapDrawableFragment());
    }

    private void pageVectorDrawable() {
        showFragment(new VectorDrawableFragment());
    }

    private void pageMeasureVectorRenderingTime() {
        showFragment(new MeasureVectorDrawableFragment());
    }

    private void pageManagingBitmapMemory() {
        showFragment(new ManagingBitmapMemoryFragment());
    }
}
