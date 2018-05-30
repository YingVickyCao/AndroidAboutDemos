package com.hades.android.example.android_about_demos.nested_scroll_view;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hades.android.example.android_about_demos.R;

import java.util.ArrayList;
import java.util.List;


/**
 * ScrollView 中嵌套一个ListView，仅仅ListView可以滑动，ScrollView不可以滑动。
 */
public class NestedScrollViewHasRecycleViewFragment extends Fragment {
    public static NestedScrollViewHasRecycleViewFragment newInstance() {
        return new NestedScrollViewHasRecycleViewFragment();
    }

    private RecyclerView mRecyclerView;
    private NumAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nestedscrollview_has_recyclerview_layout, container, false);

        mAdapter = new NumAdapter(getListData());
        
        mRecyclerView = view.findViewById(R.id.recyclerView);
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