package com.hades.example.android.lib.base;

import android.app.TaskStackBuilder;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.hades.example.android.lib.R;
import com.hades.example.android.lib.mock.SFMock;

public class BaseActivity extends AppCompatActivity {
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

    protected void showActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (null == actionBar) {
            return;
        }
        actionBar.show();
    }

    protected void hideActionBar() {
        showToast("Hide");
        ActionBar actionBar = getSupportActionBar();
        if (null == actionBar) {
            return;
        }
        actionBar.hide();
    }

    protected void requestPermission() {

    }

    protected boolean isNeedCheckPermission() {
        return false;
    }

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
                && (null != getSupportFragmentManager().findFragmentById(R.id.fragmentRoot));
    }

    protected void removeDetailFragment() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragmentRoot);
        if (null != fragment) {
            getSupportFragmentManager().beginTransaction().remove(fragment).commit();
        }
    }

    protected void showFragment(Fragment fragment) {
        hideBtns();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentRoot, fragment, fragment.getClass().getSimpleName()).commit();
    }

    protected void showFragment(Fragment fragment, @IdRes int containerViewId) {
        hideBtns();
        getSupportFragmentManager().beginTransaction().replace(containerViewId, fragment, fragment.getClass().getSimpleName()).commit();
    }

    protected void showFragment(Fragment fragment, String tag) {
        hideBtns();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentRoot, fragment, tag).commit();
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


    protected void setLightTheme() {
        boolean isRedTheme = SFMock.getInstance().isRedTheme();
        if (isRedTheme) {
            return;
        }
        SFMock.getInstance().useRedTheme(true);

        applyTheme();
    }

    protected void setDarkTheme() {
        boolean isRedTheme = SFMock.getInstance().isRedTheme();
        if (!isRedTheme) {
            return;
        }
        SFMock.getInstance().useRedTheme(false);

        applyTheme();
    }

    protected void applyTheme() {
        finish();
        TaskStackBuilder.create(this).addNextIntent(getIntent()).startActivities();
    }

    protected void setTheme() {
        boolean isLightTheme = SFMock.getInstance().isRedTheme();
        if (isLightTheme) {
            setTheme(R.style.AppTheme_Light);
        } else {
            setTheme(R.style.AppTheme_Dark);
        }
    }
}