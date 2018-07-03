package com.hades.android.example.android_about_demos.resource;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ScrollView;

import com.hades.android.example.android_about_demos.R;
import com.hades.android.example.android_about_demos.resource.array.ArrayFragment;
import com.hades.android.example.android_about_demos.resource.i18n.InternationalizationFragment;
import com.hades.android.example.android_about_demos.resource.material.MaterialFragment;
import com.hades.android.example.android_about_demos.resource.theme.apply_theme.ThemeChoosePageAActivity;
import com.hades.android.example.android_about_demos.resource.xml.ParseXmlFragment;

/**
 * https://www.cnblogs.com/andriod-html5/archive/2012/04/30/2539419.html
 */
public class ResourceActivity extends Activity {
    private static final String TAG = ResourceActivity.class.getSimpleName();

    private ScrollView mScrollView;
    private View mFragmentRoot;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources);

        mScrollView = findViewById(R.id.scrollView);
        mFragmentRoot = findViewById(R.id.fragmentRoot);

        findViewById(R.id.parseXml).setOnClickListener(v -> parseXml());
        findViewById(R.id.theme).setOnClickListener(v -> theme());
        findViewById(R.id.play_Raw_asserts).setOnClickListener(v -> play_Raw_asserts());
        findViewById(R.id.internationalization).setOnClickListener(v -> internationalization());
        findViewById(R.id.array).setOnClickListener(v -> array());

//        hideBtns();
        showBtns();
//        showCurrentTest();
    }

    private void showBtns() {
        mScrollView.setVisibility(View.VISIBLE);
        mFragmentRoot.setVisibility(View.GONE);
    }

    private void hideBtns() {
        mScrollView.setVisibility(View.GONE);
        mFragmentRoot.setVisibility(View.VISIBLE);
    }

    private void showCurrentTest() {
        parseXml();
    }

    private boolean isShowDetail() {
        return mFragmentRoot.getVisibility() == View.VISIBLE;
    }

    private void removeDetailFragment() {
        Fragment fragment = getFragmentManager().findFragmentById(R.id.fragmentRoot);
        getFragmentManager().beginTransaction().remove(fragment).commit();
    }

    private void showFragment(Fragment fragment) {
        hideBtns();
        getFragmentManager().beginTransaction().replace(R.id.fragmentRoot, fragment, fragment.getClass().getSimpleName()).commit();
    }

    private void showActivity(Class<?> dest) {
        startActivity(new Intent(this, dest));
    }

    @Override
    public void onBackPressed() {
        if (isShowDetail()) {
            showBtns();
            removeDetailFragment();
        } else {
            super.onBackPressed();
        }
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
