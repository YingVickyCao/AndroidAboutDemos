package com.hades.android.example.android_about_demos.widget;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ScrollView;

import com.hades.android.example.android_about_demos.R;

public class TestWidgetActivity extends Activity implements View.OnClickListener {
    private ScrollView mScrollView;
    private View mFragmentRoot;
    private View mShowBtn;
    private View mHideBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.widget_test_layout);

        mScrollView = findViewById(R.id.scrollView);
        mFragmentRoot = findViewById(R.id.fragmentRoot);
        mShowBtn = findViewById(R.id.show);
        mHideBtn = findViewById(R.id.hide);

        findViewById(R.id.show).setOnClickListener(this);
        findViewById(R.id.hide).setOnClickListener(this);

        findViewById(R.id.jumpRatingBar).setOnClickListener(this);
        showBtns();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.show:
                showBtns();
                break;

            case R.id.hide:
                hideBtns();
                break;

            case R.id.jumpRatingBar:
                jumpRatingBarFragment();
                break;

        }
    }

    private void showBtns() {
        mScrollView.setVisibility(View.VISIBLE);
        mFragmentRoot.setVisibility(View.GONE);
//        mShowBtn.setVisibility(View.GONE);
//        mHideBtn.setVisibility(View.VISIBLE);
    }

    private void hideBtns() {
        mScrollView.setVisibility(View.GONE);
        mFragmentRoot.setVisibility(View.VISIBLE);
//        mShowBtn.setVisibility(View.VISIBLE);
//        mHideBtn.setVisibility(View.GONE);
    }

    private void jumpRatingBarFragment() {
        showFragment(RatingBarFragment.newInstance(), RatingBarFragment.class.getSimpleName());
    }

    private void showFragment(Fragment fragment, String tag) {
        hideBtns();
        getFragmentManager().beginTransaction().replace(R.id.fragmentRoot, fragment, tag).commit();
    }
}