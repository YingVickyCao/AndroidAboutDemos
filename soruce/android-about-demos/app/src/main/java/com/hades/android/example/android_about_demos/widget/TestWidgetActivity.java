package com.hades.android.example.android_about_demos.widget;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ScrollView;

import com.hades.android.example.android_about_demos.R;
import com.hades.android.example.android_about_demos.widget.list.recyclerview.dag_reorder_list.DragReorderListFragment;
import com.hades.android.example.android_about_demos.widget.view_animator.ImageSwitcherFragment;
import com.hades.android.example.android_about_demos.widget.view_animator.RatingBarFragment;
import com.hades.android.example.android_about_demos.widget.view_animator.TextSwitcherFragment;
import com.hades.android.example.android_about_demos.widget.view_animator.ViewFlipperFragment;
import com.hades.android.example.android_about_demos.widget.view_animator.ViewSwitcherFragment;

public class TestWidgetActivity extends Activity implements View.OnClickListener {
    private ScrollView mScrollView;
    private View mFragmentRoot;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.widget_test_layout);

        mScrollView = findViewById(R.id.scrollView);
        mFragmentRoot = findViewById(R.id.fragmentRoot);
//        showBtns();

        findViewById(R.id.show).setOnClickListener(this);
        findViewById(R.id.hide).setOnClickListener(this);

        findViewById(R.id.jumpRatingBar).setOnClickListener(this);
        findViewById(R.id.jumpViewSwitcher).setOnClickListener(this);
        findViewById(R.id.jumpImageSwitcher).setOnClickListener(this);
        findViewById(R.id.jumpTextSwitcher).setOnClickListener(this);

        findViewById(R.id.recyclerView4DragReorderList).setOnClickListener(this);
        findViewById(R.id.testViewFlipper).setOnClickListener(this);

        showCurrentTest();
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

            case R.id.jumpViewSwitcher:
                jumpViewSwitcherFragment();
                break;

            case R.id.jumpImageSwitcher:
                jumpImageSwitcherFragment();
                break;

            case R.id.jumpTextSwitcher:
                jumpTextSwitcher();
                break;

            case R.id.recyclerView4DragReorderList:
                openRecyclerView4DragReorderListFragment();
                break;

            case R.id.testViewFlipper:
                testViewFlipper();
                break;
        }
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
        testViewFlipper();
    }

    private void showFragment(Fragment fragment, String tag) {
        hideBtns();
        getFragmentManager().beginTransaction().replace(R.id.fragmentRoot, fragment, tag).commit();
    }

    private void jumpRatingBarFragment() {
        showFragment(RatingBarFragment.newInstance(), RatingBarFragment.class.getSimpleName());
    }

    private void jumpViewSwitcherFragment() {
        showFragment(ViewSwitcherFragment.newInstance(), ViewSwitcherFragment.class.getSimpleName());
    }

    private void jumpImageSwitcherFragment() {
        showFragment(ImageSwitcherFragment.newInstance(), ImageSwitcherFragment.class.getSimpleName());
    }

    private void jumpTextSwitcher() {
        showFragment(TextSwitcherFragment.newInstance(), TextSwitcherFragment.class.getSimpleName());
    }

    private void openRecyclerView4DragReorderListFragment() {
        showFragment(DragReorderListFragment.newInstance(), DragReorderListFragment.class.getSimpleName());
    }

    private void testViewFlipper() {
        showFragment(ViewFlipperFragment.newInstance(), ViewFlipperFragment.class.getSimpleName());
    }
}