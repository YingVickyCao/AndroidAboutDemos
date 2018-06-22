package com.hades.android.example.android_about_demos.app_component.fragment.qa_swipe_whole_page;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hades.android.example.android_about_demos.R;

public class QA4WrongSwipeWhoPageInPageFragment extends Fragment {
    public static QA4WrongSwipeWhoPageInPageFragment newInstance() {
        return new QA4WrongSwipeWhoPageInPageFragment();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_qa_swipe_whole_page_4_fragment_content, container, false);
    }
}
