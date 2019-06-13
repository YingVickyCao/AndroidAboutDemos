package com.hades.example.android.widget._list._recyclerview.dummy;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.BaseFragment;
import com.hades.example.android.lib.mock.DummyContent;
import com.hades.example.android.lib.mock.DummyItem;
import com.hades.example.android.widget._list._recyclerview._dag_reorder_list.v1.SimpleDividerItemDecoration;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link IItemClickAction}
 * interface.
 */
public class DummyRecyclerViewFragment extends BaseFragment implements IItemClickAction {
    private static final String TAG = DummyRecyclerViewFragment.class.getSimpleName();

    private static final int ARG_COLUMN_COUNT = 1;
//    private static final  int ARG_COLUMN_COUNT =2;

    private RecyclerView rv;
    private DummyRecyclerViewAdapter mAdapter;
    private LinearLayoutManager mLinearLayoutManager;

    public DummyRecyclerViewFragment() {
    }

    @SuppressWarnings("unused")
    public static DummyRecyclerViewFragment newInstance(int columnCount) {
        return new DummyRecyclerViewFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.widget_recyclerview_dummy, container, false);
        rv = view.findViewById(R.id.list);
        view.findViewById(R.id.findItemView).setOnClickListener(v -> findItemView());
        view.findViewById(R.id.findViewByPosition).setOnClickListener(v -> findViewByPosition());

        view.findViewById(R.id.scrollToPosition).setOnClickListener(v -> scrollToPosition());
        view.findViewById(R.id.smoothScrollToPosition).setOnClickListener(v -> smoothScrollToPosition());

        initList(view);

        return view;
    }

    private void initList(View view) {
        Context context = view.getContext();
        // PO:RecyclerView.setHasFixedSize(true)
        rv.setHasFixedSize(true);
        forbidScrollVertically();
//        mLinearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rv.setLayoutManager(mLinearLayoutManager);
//        rv.setLayoutManager(new GridLayoutManager(context, ARG_COLUMN_COUNT));
        rv.addItemDecoration(new SimpleDividerItemDecoration(getContext(), R.drawable.drawable_shape_4_divider_vertical));
        mAdapter = new DummyRecyclerViewAdapter(DummyContent.ITEMS(), this);
        rv.setAdapter(mAdapter);
    }

    private void forbidScrollVertically() {
        mLinearLayoutManager = new LinearLayoutManager(getActivity()) {
            @Override
            public boolean canScrollVertically() {
//                return super.canScrollVertically();
                return false;
            }
        };
    }

    @Override
    public void onResume() {
        super.onResume();
        mAdapter.setListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        mAdapter.setListener(null);
    }

    @Override
    public void onItemClickListener(DummyItem item) {
        Toast.makeText(getContext(), item.colo2, Toast.LENGTH_SHORT).show();
    }

    private void findItemView() {
        for (int i = 0; i < DummyContent.COUNT - 1; i++) {
            findItemView(i);
        }
    }

    private void findItemView(int childPosition) {
        View childView = rv.getChildAt(childPosition);
        if (childView instanceof ViewGroup && null != childView) {
            TextView title = childView.findViewById(R.id.title);
            Log.d(TAG, "findItemView: RecyclerView.getChildAt(" + childPosition + ")=" + title.getText().toString());
        } else {
            Log.d(TAG, "findItemView: RecyclerView.getChildAt(" + childPosition + ")=null");
        }

    }

    private void findViewByPosition() {
        for (int i = 0; i < DummyContent.COUNT - 1; i++) {
            findViewByPosition(i);
        }
    }

    private void findViewByPosition(int childPosition) {
        View childView = mLinearLayoutManager.findViewByPosition(childPosition);
        if (childView instanceof ViewGroup && null != childView) {
            TextView title = childView.findViewById(R.id.title);
            Log.d(TAG, "findViewByPosition: LayoutManager.findViewByPosition(" + childPosition + ")= " + title.getText().toString());
        } else {
            Log.d(TAG, "findViewByPosition: LayoutManager.findViewByPosition(" + childPosition + ")= null");
        }

    }

    private void scrollToPosition() {
        rv.scrollToPosition(DummyContent.COUNT - 2);
//        mLinearLayoutManager.scrollToPosition(DummyContent.COUNT - 2);
    }

    private void smoothScrollToPosition() {
        rv.smoothScrollToPosition(DummyContent.COUNT - 2);
    }
}