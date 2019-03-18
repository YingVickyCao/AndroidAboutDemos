package com.hades.example.android.data_storage.database;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.hades.example.android.R;
import com.hades.example.android.base.BaseFragment;
import com.hades.example.android.mock.DummyItem;

import java.util.ArrayList;

import static com.hades.example.android.app_component.cp.dict.DictActivity.KEY_SEARCH_RESULT;

public class DummyContentFragment extends BaseFragment {
    public static final String TAG = DummyContentFragment.class.getSimpleName();

    ArrayList<DummyItem> mList = new ArrayList<>();
    private ListView mListView;
    private DummyContentAdapter mAdapter;

    public static Fragment getInstance(ArrayList<DummyItem> list) {
        Fragment fragment = new DummyContentFragment();

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
            mList.addAll(getArguments().getParcelableArrayList(KEY_SEARCH_RESULT)) ;
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