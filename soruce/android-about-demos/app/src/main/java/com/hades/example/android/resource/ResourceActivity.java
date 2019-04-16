package com.hades.example.android.resource;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.PermissionActivity;
import com.hades.example.android.resource.adapter_screen.DisableRotateActivity;
import com.hades.example.android.resource.adapter_screen.ScreenSizeActivity;
import com.hades.example.android.resource.array.ArrayFragment;
import com.hades.example.android.resource.drawable.bitmap.BitmapDrawableFragment;
import com.hades.example.android.resource.drawable.bitmap.DecodeSampledBitmapFragment;
import com.hades.example.android.resource.drawable.bitmap.ManagingBitmapMemoryFragment;
import com.hades.example.android.resource.drawable.bitmap.three_level_cache.ImageGridActivity;
import com.hades.example.android.resource.drawable.shape.TestShapeDrawableFragment;
import com.hades.example.android.resource.drawable.vector.MeasureVectorDrawableFragment;
import com.hades.example.android.resource.drawable.vector.VectorDrawableFragment;
import com.hades.example.android.resource.i18n.InternationalizationFragment;
import com.hades.example.android.resource.material.MaterialFragment;
import com.hades.example.android.resource.theme.apply_theme.ThemeChoosePageAActivity;
import com.hades.example.android.resource.xml.ParseXMLFragment;

/**
 * https://www.cnblogs.com/andriod-html5/archive/2012/04/30/2539419.html
 */
public class ResourceActivity extends PermissionActivity {
    private static final String TAG = ResourceActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources);
        initViews();

        findViewById(R.id.parseMaterial).setOnClickListener(v -> parseXml());
        findViewById(R.id.pageTheme).setOnClickListener(v -> pageTheme());
        findViewById(R.id.pageInternationalization).setOnClickListener(v -> pageInternationalization());
        findViewById(R.id.pageArray).setOnClickListener(v -> pageArray());
        findViewById(R.id.pageParseXML).setOnClickListener(v -> pageParseXML());
        findViewById(R.id.pageScreenOrientation).setOnClickListener(v -> pageScreenOrientation());
        findViewById(R.id.pageScreenSize).setOnClickListener(v -> pageScreenSize());
        findViewById(R.id.pageDrawableAndScreenDensity).setOnClickListener(v -> pageDrawableAndScreenDensity());
        findViewById(R.id.pageVectorDrawable).setOnClickListener(v -> pageVectorDrawable());
        findViewById(R.id.pageCheckVectorRenderingTime).setOnClickListener(v -> pageMeasureVectorRenderingTime());
        findViewById(R.id.pageManagingBitmapMemory).setOnClickListener(v -> pageManagingBitmapMemory());
        findViewById(R.id.pageLoadBitmapPo).setOnClickListener(v -> pageLoadBitmapPo());
        findViewById(R.id.pageMemoryCacheBitmap).setOnClickListener(v -> pageBitmapThreeLevelCache());
        findViewById(R.id.pageShapeDrawable).setOnClickListener(v -> pageShapeDrawable());
    }

    @Override
    protected void requestPermission() {
        checkPermission("Request permission for operate storage", Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    private void parseXml() {
        showFragment(new MaterialFragment());
    }

    private void pageTheme() {
        showActivity(ThemeChoosePageAActivity.class);
    }

    private void pageInternationalization() {
        showFragment(new InternationalizationFragment());
    }

    private void pageArray() {
        showFragment(new ArrayFragment());
    }

    private void pageParseXML() {
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

    private void pageLoadBitmapPo() {
        showFragment(new DecodeSampledBitmapFragment());
    }

    private void pageBitmapThreeLevelCache() {
        showActivity(ImageGridActivity.class);
    }

    private void pageShapeDrawable() {
        showFragment(new TestShapeDrawableFragment());
    }
}