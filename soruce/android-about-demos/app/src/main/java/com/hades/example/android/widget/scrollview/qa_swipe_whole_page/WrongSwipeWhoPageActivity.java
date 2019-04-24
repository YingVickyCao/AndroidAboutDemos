package com.hades.example.android.widget.scrollview.qa_swipe_whole_page;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.View;

import com.hades.example.android.R;

public class WrongSwipeWhoPageActivity extends Activity implements View.OnClickListener {
    View mContent;
    View mFragmentRoot;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.widget_scrollview_qa_wrong_swipe_whole_page);
        setContentView(R.layout.widget_scrollview_qa_wrong_swipe_whole_page_after);

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
        getFragmentManager().beginTransaction().replace(R.id.fragmentRoot, WrongSwipeWhoPageFragment.newInstance(), WrongSwipeWhoPageFragment.class.getSimpleName()).commit();
    }

    private void removeDetailFragment() {
        Fragment fragment = getFragmentManager().findFragmentById(R.id.fragmentRoot);
        getFragmentManager().beginTransaction().remove(fragment).commit();
    }
}
