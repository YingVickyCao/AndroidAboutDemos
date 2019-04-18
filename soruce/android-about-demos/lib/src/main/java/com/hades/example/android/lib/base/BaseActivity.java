package com.hades.example.android.lib.base;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ScrollView;
import android.widget.Toast;

import com.hades.example.android.lib.R;

public abstract class BaseActivity extends AppCompatActivity {
    private View topic;
    private ScrollView mScrollView;
    protected View mFragmentRoot;

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

    protected void initViews() {
        topic = findViewById(R.id.topic);
        mScrollView = findViewById(R.id.scrollView);
        mFragmentRoot = findViewById(R.id.fragmentRoot);
        showCurrentTest();
    }

    protected void showCurrentTest() {
        showBtns();
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

    protected void showFragmentRoot() {
        if (null != mFragmentRoot) {
            mFragmentRoot.setVisibility(View.VISIBLE);
        }
    }

    protected boolean isShowDetail() {
        return (null != mFragmentRoot)
                && (mFragmentRoot.getVisibility() == View.VISIBLE)
                && (null != getFragmentManager().findFragmentById(R.id.fragmentRoot));
    }

    protected void removeDetailFragment() {
        Fragment fragment = getFragmentManager().findFragmentById(R.id.fragmentRoot);
        if (null != fragment) {
            getFragmentManager().beginTransaction().remove(fragment).commit();
        }
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

    protected void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}