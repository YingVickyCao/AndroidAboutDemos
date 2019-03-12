package com.hades.example.android.android_about_demos.widget.scrollview.qa_swipe_whole_page;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hades.example.android.android_about_demos.R;

public class WrongSwipeWhoPageFragment extends Fragment {
    public static WrongSwipeWhoPageFragment newInstance() {
        return new WrongSwipeWhoPageFragment();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.widget_scrollview_qa_wrong_swipe_whole_page_4_fragment, container, false);
    }
}
