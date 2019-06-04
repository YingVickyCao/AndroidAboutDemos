package com.hades.example.android.widget._list._recyclerview.dummy;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.BaseFragment;
import com.hades.example.android.lib.mock.DummyContent;
import com.hades.example.android.widget._list._recyclerview._dag_reorder_list.SimpleDividerItemDecoration;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link IItemClickAction}
 * interface.
 */
public class DummyRecyclerViewFragment extends BaseFragment {

    private static final  int ARG_COLUMN_COUNT =1;
//    private static final  int ARG_COLUMN_COUNT =2;
    private int mColumnCount = 1;

    private IItemClickAction mListener;
    private RecyclerView rv;

    public DummyRecyclerViewFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static DummyRecyclerViewFragment newInstance(int columnCount) {
        return new DummyRecyclerViewFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.widget_recyclerview_dummy, container, false);
        rv = view.findViewById(R.id.list);
        initList(view);
        return view;
    }

    private void initList(View view) {
        Context context = view.getContext();
        // PO:RecyclerView.setHasFixedSize(true)
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(context));
//        rv.setLayoutManager(new GridLayoutManager(context, ARG_COLUMN_COUNT));

        rv.addItemDecoration(new SimpleDividerItemDecoration(getContext(), R.drawable.drawable_shape_4_divider_vertical));
        rv.setAdapter(new DummyRecyclerViewAdapter(DummyContent.ITEMS(), mListener));
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IItemClickAction) {
            mListener = (IItemClickAction) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement IItemClickAction");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

}
