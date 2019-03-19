package com.hades.example.android.base;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ScrollView;

import com.hades.example.android.lib.R;

public abstract class BaseActivity extends AppCompatActivity {
    protected View mRoot;
    private View topic;
    private ScrollView mScrollView;
    private View mFragmentRoot;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (isNeedCheckPermission()) {
            requestPermission();
        }
    }

    protected void requestPermission() {

    }

    protected abstract boolean isNeedCheckPermission();

    public void setRoot(View root) {
        mRoot = root;
    }

    protected void startActivity(Class<?> cls) {
        startActivity(new Intent(this, cls));
    }

    protected void initViews() {
        topic = findViewById(R.id.topic);
        mScrollView = findViewById(R.id.scrollView);
        mFragmentRoot = findViewById(R.id.fragmentRoot);

        showCurrentTest();
    }

    protected void showBtns() {
        if (null != mScrollView) {
            mScrollView.setVisibility(View.VISIBLE);
        }

        if (null != topic) {
            topic.setVisibility(View.VISIBLE);
        }

        if (null != mFragmentRoot) {
            mFragmentRoot.setVisibility(View.GONE);
        }
    }

    protected void hideBtns() {
        if (null != mScrollView) {
            mScrollView.setVisibility(View.GONE);
        }
        if (null != topic) {
            topic.setVisibility(View.GONE);
        }
        if (null != mFragmentRoot) {
            mFragmentRoot.setVisibility(View.VISIBLE);
        }
    }

    protected void showCurrentTest() {
        hideBtns();
    }

    protected boolean isShowDetail() {
        return null != mFragmentRoot && mFragmentRoot.getVisibility() == View.VISIBLE;
    }

    protected void removeDetailFragment() {
        Fragment fragment = getFragmentManager().findFragmentById(R.id.fragmentRoot);
        getFragmentManager().beginTransaction().remove(fragment).commit();
    }

    protected void showFragment(Fragment fragment) {
        hideBtns();
        getFragmentManager().beginTransaction().replace(R.id.fragmentRoot, fragment, fragment.getClass().getSimpleName()).commit();
    }

    protected void showFragment(Fragment fragment, String tag) {
        hideBtns();
        getFragmentManager().beginTransaction().replace(R.id.fragmentRoot, fragment, tag).commit();
    }

    protected void showActivity(Class<?> dest) {
        startActivity(new Intent(this, dest));
    }

    @Override
    public void onBackPressed() {
        if (isIgnoreBack()) {
            super.onBackPressed();
            return;
        }

        if (isShowDetail()) {
            showBtns();
            removeDetailFragment();
        } else {
            super.onBackPressed();
        }
    }

    protected boolean isIgnoreBack() {
        return false;
    }
}