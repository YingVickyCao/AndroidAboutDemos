package com.hades.example.android.lib.mock;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.Nullable;

import com.hades.example.android.lib.R;
import com.hades.example.android.lib.base.BaseFragment;

import java.util.ArrayList;

public class DummyContentFragment extends BaseFragment {
    public static final String TAG = DummyContentFragment.class.getSimpleName();
    public static final String KEY_SEARCH_RESULT = "search_result";

    ArrayList<DummyItem> mList = new ArrayList<>();
    private ListView mListView;
    private DummyContentAdapter mAdapter;

    public static DummyContentFragment getInstance(ArrayList<DummyItem> list) {
        DummyContentFragment fragment = new DummyContentFragment();

        Bundle data = new Bundle();
        data.putParcelableArrayList(KEY_SEARCH_RESULT, list);
        fragment.setArguments(data);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dummy_content, container, false);

        if (null != getArguments()) {
            mList.addAll(getArguments().getParcelableArrayList(KEY_SEARCH_RESULT));
        }
        mListView = view.findViewById(R.id.listView);

        mAdapter = new DummyContentAdapter(mList, getActivity());
        mListView.setAdapter(mAdapter);
        return view;
    }

    public void setList(ArrayList<DummyItem> list) {
        mList.clear();
        mList.addAll(list);
        mAdapter.notifyDataSetChanged();
    }
}