package com.hades.example.android.widget._list.scrollview.nested_scroll_view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.BaseFragment;


/**
 * ScrollView 中嵌套一个ListView，仅仅ListView可以滑动，ScrollView不可以滑动。
 */
public class ScrollViewAboveListViewFragment extends BaseFragment {
    public static ScrollViewAboveListViewFragment newInstance() {
        return new ScrollViewAboveListViewFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.widget_scrollview_above_listview_layout, container, false);
    }
}