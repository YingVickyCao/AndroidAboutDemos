package com.hades.android.example.android_about_demos.app_component.activity;

import android.app.Fragment;
import android.content.Intent;
import android.view.View;
import android.widget.ScrollView;

import com.hades.android.example.android_about_demos.R;

public class BaseActivityWithFragment extends BaseActivity {
    private static final String TAG = BaseActivityWithFragment.class.getSimpleName();

    private ScrollView mScrollView;
    private View mFragmentRoot;

    protected void initViews() {
        mScrollView = findViewById(R.id.scrollView);
        mFragmentRoot = findViewById(R.id.fragmentRoot);

//      showDefaultFragment();
        showBtns();
    }

    protected void showBtns() {
        mScrollView.setVisibility(View.VISIBLE);
        mFragmentRoot.setVisibility(View.GONE);
    }

    protected void hideBtns() {
        mScrollView.setVisibility(View.GONE);
        mFragmentRoot.setVisibility(View.VISIBLE);
    }

    protected void showCurrentTest() {

    }

    private void showDefaultFragment() {
        hideBtns();
        showCurrentTest();
    }

    protected boolean isShowDetail() {
        return mFragmentRoot.getVisibility() == View.VISIBLE;
    }

    protected void removeDetailFragment() {
        Fragment fragment = getFragmentManager().findFragmentById(R.id.fragmentRoot);
        getFragmentManager().beginTransaction().remove(fragment).commit();
    }

    protected void showFragment(Fragment fragment) {
        hideBtns();
        getFragmentManager().beginTransaction().replace(R.id.fragmentRoot, fragment, fragment.getClass().getSimpleName()).commit();
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