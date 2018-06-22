package com.hades.android.example.android_about_demos.app_component.fragment.qa_swipe_whole_page;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.hades.android.example.android_about_demos.R;

public class QA4WrongSwipeWhoPageInPageActivity extends Activity implements View.OnClickListener {
    View mContent;
    View mFragmentRoot;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fragment_qa_swipe_whole_page);
        setContentView(R.layout.fragment_qa_swipe_whole_page_after);

        mContent = findViewById(R.id.content);
        mFragmentRoot = findViewById(R.id.fragmentRoot);

        findViewById(R.id.detail).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.detail:
                openDetailFragment();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (isShowDetail()) {
            hideDetailFragmentContainer();
            removeDetailFragment();
        }
    }

    private boolean isShowDetail() {
        return mFragmentRoot.getVisibility() == View.VISIBLE;
    }

    private void hideDetailFragmentContainer() {
        mContent.setVisibility(View.VISIBLE);
        mFragmentRoot.setVisibility(View.GONE);
    }

    private void showDetailFragmentContainer() {
        mContent.setVisibility(View.GONE);
        mFragmentRoot.setVisibility(View.VISIBLE);
    }

    private void openDetailFragment() {
        showDetailFragmentContainer();
        getFragmentManager().beginTransaction().replace(R.id.fragmentRoot, QA4WrongSwipeWhoPageInPageFragment.newInstance(), QA4WrongSwipeWhoPageInPageFragment.class.getSimpleName()).commit();
    }

    private void removeDetailFragment() {
        Fragment fragment = getFragmentManager().findFragmentById(R.id.fragmentRoot);
        getFragmentManager().beginTransaction().remove(fragment).commit();
    }
}
