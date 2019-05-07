package com.hades.example.android.resource;

import android.Manifest;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.PermissionActivity;
import com.hades.example.android.resource._array.TestStringIntegerArrayFragment;
import com.hades.example.android.resource._color_state_list.TestColorFragment;
import com.hades.example.android.resource._color_state_list.TestColorStateListFragment;
import com.hades.example.android.resource.adapter_screen.TestConfigurationActivity;
import com.hades.example.android.resource.adapter_screen.ScreenSizeActivity;
import com.hades.example.android.resource.drawable._level_list.TestLevelListDrawableFragment;
import com.hades.example.android.resource.drawable.bitmap.TestBitmapMemoryAndScreenDensityFragment;
import com.hades.example.android.resource.drawable.bitmap.TestDecodeSampledBitmapFragment;
import com.hades.example.android.resource.drawable.bitmap.TestDrawableFolderFragment;
import com.hades.example.android.resource.drawable.bitmap.three_level_cache.ImageGridActivity;
import com.hades.example.android.resource.drawable.shape.TestShapeDrawableFragment;
import com.hades.example.android.resource.drawable.vector.MeasureVectorDrawableFragment;
import com.hades.example.android.resource.drawable.vector.VectorDrawableFragment;
import com.hades.example.android.resource.i18n.InternationalizationFragment;
import com.hades.example.android.resource.material.MaterialFragment;
import com.hades.example.android.resource._style_theme.TestAttrInThemeFragment;
import com.hades.example.android.resource._style_theme.apply_theme.ThemeChoosePageAActivity;
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
        findViewById(R.id.pageColor).setOnClickListener(v -> pageColor());
        findViewById(R.id.pageColorStateListResource).setOnClickListener(v -> pageColorStateListResource());
        findViewById(R.id.pageSwitchColorInTheme).setOnClickListener(v -> pageSwitchColorInTheme());
        findViewById(R.id.pageThemeAndStyle).setOnClickListener(v -> pageThemeAndStyle());
        findViewById(R.id.pageInternationalization).setOnClickListener(v -> pageInternationalization());
        findViewById(R.id.pageStringIntegerArray).setOnClickListener(v -> pageStringIntegerArray());
        findViewById(R.id.pageParseXML).setOnClickListener(v -> pageParseXML());
        findViewById(R.id.pageScreenOrientation).setOnClickListener(v -> pageScreenOrientation());
        findViewById(R.id.pageScreenSize).setOnClickListener(v -> pageScreenSize());
        findViewById(R.id.pageDrawableAndScreenDensity).setOnClickListener(v -> pageDrawableAndScreenDensity());
        findViewById(R.id.pageVectorDrawable).setOnClickListener(v -> pageVectorDrawable());
        findViewById(R.id.pageCheckVectorRenderingTime).setOnClickListener(v -> pageMeasureVectorRenderingTime());
        findViewById(R.id.pageLoadBitmapPo).setOnClickListener(v -> pageLoadBitmapPo());
        findViewById(R.id.pageMemoryCacheBitmap).setOnClickListener(v -> pageBitmapThreeLevelCache());

        findViewById(R.id.pageShapeDrawable).setOnClickListener(v -> pageShapeDrawable());
        findViewById(R.id.pageLevelListDrawable).setOnClickListener(v -> pageLevelListDrawable());
        findViewById(R.id.pageDrawableFolder).setOnClickListener(v -> pageDrawableFolder());
    }

    @Override
    protected void requestPermission() {
        checkPermission("Request permission for operate storage", Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    @Override
    protected void showCurrentTest() {
        pageDrawableAndScreenDensity();
    }

    private void pageColor() {
        showFragment(new TestColorFragment());
    }

    private void pageColorStateListResource() {
        showFragment(new TestColorStateListFragment());
    }

    private void parseXml() {
        showFragment(new MaterialFragment());
    }

    private void pageSwitchColorInTheme() {
        showActivity(ThemeChoosePageAActivity.class);
    }

    private void pageThemeAndStyle() {
        showFragment(new TestAttrInThemeFragment());
    }

    private void pageInternationalization() {
        showFragment(new InternationalizationFragment());
    }

    private void pageStringIntegerArray() {
        showFragment(new TestStringIntegerArrayFragment());
    }

    private void pageParseXML() {
        showFragment(new ParseXMLFragment());
    }

    private void pageScreenOrientation() {
        showActivity(TestConfigurationActivity.class);
    }

    private void pageScreenSize() {
        showActivity(ScreenSizeActivity.class);
    }

    private void pageDrawableAndScreenDensity() {
        showFragment(new TestBitmapMemoryAndScreenDensityFragment());
    }

    private void pageVectorDrawable() {
        showFragment(new VectorDrawableFragment());
    }

    private void pageMeasureVectorRenderingTime() {
        showFragment(new MeasureVectorDrawableFragment());
    }
    private void pageLoadBitmapPo() {
        showFragment(new TestDecodeSampledBitmapFragment());
    }

    private void pageBitmapThreeLevelCache() {
        showActivity(ImageGridActivity.class);
    }

    private void pageShapeDrawable() {
        showFragment(new TestShapeDrawableFragment());
    }

    private void pageLevelListDrawable() {
        showFragment(new TestLevelListDrawableFragment());
    }

    private void pageDrawableFolder() {
        showFragment(new TestDrawableFolderFragment());
    }
}