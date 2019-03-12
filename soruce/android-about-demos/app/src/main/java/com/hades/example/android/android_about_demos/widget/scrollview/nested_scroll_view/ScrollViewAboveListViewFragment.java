package com.hades.example.android.android_about_demos.widget.scrollview.nested_scroll_view;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hades.example.android.android_about_demos.R;


/**
 * ScrollView 中嵌套一个ListView，仅仅ListView可以滑动，ScrollView不可以滑动。
 */
public class ScrollViewAboveListViewFragment extends Fragment {
    public static ScrollViewAboveListViewFragment newInstance() {
        return new ScrollViewAboveListViewFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.widget_scrollview_above_listview_layout, container, false);
    }
}