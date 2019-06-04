package com.hades.example.android.widget._list.scrollview.nested_scroll_view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;


/**
 * ScrollView 中嵌套一个ListView，仅仅ListView可以滑动，ScrollView不可以滑动。
 */
public class NestedScrollViewHasRecycleViewFragment extends BaseFragment {
    public static NestedScrollViewHasRecycleViewFragment newInstance() {
        return new NestedScrollViewHasRecycleViewFragment();
    }

    private RecyclerView mRecyclerView;
    private NumAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.widget_nestedscrollview_has_recyclerview_layout, container, false);

        mAdapter = new NumAdapter(getListData());
        
        mRecyclerView = view.findViewById(R.id.pageRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }

    private List<String> getListData() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add(String.valueOf(i));
        }
        return list;
    }
}