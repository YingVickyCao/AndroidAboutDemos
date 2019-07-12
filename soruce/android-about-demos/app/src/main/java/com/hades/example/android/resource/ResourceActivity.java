package com.hades.example.android.resource;

import android.Manifest;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.PermissionActivity;
import com.hades.example.android.resource._array.TestStringIntegerArrayFragment;
import com.hades.example.android.resource._color_state_list.TestColorFragment;
import com.hades.example.android.resource._color_state_list.TestColorStateListFragment;
import com.hades.example.android.resource._style_theme.ThemeChoosePageAActivity;
import com.hades.example.android.resource.adapter_screen.ScreenSizeActivity;
import com.hades.example.android.resource.adapter_screen.TestConfigurationActivity;
import com.hades.example.android.resource.anim.TestTweenAnimationFragment;
import com.hades.example.android.resource.dimension.TestDimensionFragment;
import com.hades.example.android.resource.drawable.TestDrawableFolderFragment;
import com.hades.example.android.resource.drawable.TestDrawableFragment;
import com.hades.example.android.resource.drawable._bitmap.TestBitmapFragment;
import com.hades.example.android.resource.drawable._bitmap.TestBitmapMemoryAndScreenDensityFragment;
import com.hades.example.android.resource.drawable._bitmap.TestDecodeSampledBitmapFragment;
import com.hades.example.android.resource.drawable._bitmap.three_level_cache.ImageGridActivity;
import com.hades.example.android.resource.drawable._level_list.TestLevelListDrawableFragment;
import com.hades.example.android.resource.drawable.clip.TestClipDrawableFragment;
import com.hades.example.android.resource.drawable.layer.TestLayerDrawableFragment;
import com.hades.example.android.resource.drawable.shape.TestShapeDrawableFragment;
import com.hades.example.android.resource.drawable.state.TestStateDrawableFragment;
import com.hades.example.android.resource.drawable.vector.TestVectorDrawableFragment;
import com.hades.example.android.resource.i18n.InternationalizationFragment;
import com.hades.example.android.resource.material.MaterialFragment;
import com.hades.example.android.resource.xml.ParseXMLFragment;

/**
 * https://www.cnblogs.com/andriod-html5/archive/2012/04/30/2539419.html
 */
public class ResourceActivity extends PermissionActivity {
    private static final String TAG = ResourceActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme_Light);
        setContentView(R.layout.activity_resources);
        initViews();

        findViewById(R.id.parseMaterial).setOnClickListener(v -> parseXml());
        findViewById(R.id.pageDimension).setOnClickListener(v -> pageDimension());
        findViewById(R.id.pageThemeChoose).setOnClickListener(v -> pageThemeChoose());
        findViewById(R.id.pageInternationalization).setOnClickListener(v -> pageInternationalization());
        findViewById(R.id.pageStringIntegerArray).setOnClickListener(v -> pageStringIntegerArray());
        findViewById(R.id.pageParseXML).setOnClickListener(v -> pageParseXML());
        findViewById(R.id.pageScreenOrientation).setOnClickListener(v -> pageScreenOrientation());
        findViewById(R.id.pageScreenSize).setOnClickListener(v -> pageScreenSize());
        findViewById(R.id.pageDrawableAndScreenDensity).setOnClickListener(v -> pageDrawableAndScreenDensity());

        findViewById(R.id.pageDrawable).setOnClickListener(v -> pageDrawable());
        findViewById(R.id.pageShapeDrawable).setOnClickListener(v -> pageShapeDrawable());
        findViewById(R.id.pageLevelListDrawable).setOnClickListener(v -> pageLevelListDrawable());
        findViewById(R.id.pageLayerListDrawable).setOnClickListener(v -> pageLayerListDrawable());
        findViewById(R.id.pageStateDrawable).setOnClickListener(v -> pageStateDrawable());
        findViewById(R.id.pageVectorDrawable).setOnClickListener(v -> pageVectorDrawable());
        findViewById(R.id.pageClipDrawable).setOnClickListener(v -> pageClipDrawable());

        findViewById(R.id.pageDrawableFolder).setOnClickListener(v -> pageDrawableFolder());

        findViewById(R.id.pageBitmap).setOnClickListener(v -> pageBitmap());
        findViewById(R.id.pageLoadBitmapPo).setOnClickListener(v -> pageLoadBitmapPo());
        findViewById(R.id.pageMemoryCacheBitmap).setOnClickListener(v -> pageBitmapThreeLevelCache());

        findViewById(R.id.pageColor).setOnClickListener(v -> pageColor());
        findViewById(R.id.pageColorStateListResource).setOnClickListener(v -> pageColorStateListResource());

        findViewById(R.id.pageTweenAnimation).setOnClickListener(v -> pageTweenAnimation());

    }

    @Override
    protected void requestPermission() {
        checkPermission("Request permission for operate storage", Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    @Override
    protected void showCurrentTest() {
        pageTweenAnimation();
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

    private void pageDimension() {
        showFragment(new TestDimensionFragment());
    }

    private void pageThemeChoose() {
        showActivity(ThemeChoosePageAActivity.class);
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

    private void pageDrawable() {
        showFragment(new TestDrawableFragment());
    }

    private void pageStateDrawable() {
        showFragment(new TestStateDrawableFragment());
    }

    private void pageVectorDrawable() {
        showFragment(new TestVectorDrawableFragment());
    }

    private void pageClipDrawable() {
        showFragment(new TestClipDrawableFragment());
    }

    private void pageLoadBitmapPo() {
        showFragment(new TestDecodeSampledBitmapFragment());
    }

    private void pageBitmapThreeLevelCache() {
        showActivity(ImageGridActivity.class);
    }

    private void pageBitmap() {
        showFragment(new TestBitmapFragment());
    }

    private void pageShapeDrawable() {
        showFragment(new TestShapeDrawableFragment());
    }

    private void pageLevelListDrawable() {
        showFragment(new TestLevelListDrawableFragment());
    }

    private void pageLayerListDrawable() {
        showFragment(new TestLayerDrawableFragment());
    }

    private void pageDrawableFolder() {
        showFragment(new TestDrawableFolderFragment());
    }

    private void pageTweenAnimation() {
        showFragment(new TestTweenAnimationFragment());
    }
}