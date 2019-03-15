package com.hades.example.android.widget.list.recyclerview.dag_reorder_list;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hades.example.android.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Ref:
 * https://blog.csdn.net/qq_30379689/article/details/52463139
 * https://blog.csdn.net/minwenping/article/details/69829704
 * https://www.jianshu.com/p/4eff036360da
 * <p>
 * https://medium.com/@ipaulpro/drag-and-swipe-with-recyclerview-6a6f0c422efd
 */
public class DragReorderListFragment extends Fragment implements StartDragListener {
    private static final String TAG = DragReorderListFragment.class.getSimpleName();

    private final static int NUM = 5;

    private RecyclerView rv;
    private ItemTouchHelperAdapter adapter;
    private List<Message> list;
    private ItemTouchHelper mItemTouchHelper;

    public static DragReorderListFragment newInstance() {
        return new DragReorderListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.widget_recyclerview_4_drag_reorder_list, container, false);
        initData();

        rv = view.findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(view.getContext()));
        adapter = new ItemTouchHelperAdapter(list);
        adapter.setStartDragListener(this);
        rv.setAdapter(adapter);
        rv.addItemDecoration(new MyDecoration(getActivity(), LinearLayoutManager.VERTICAL, R.drawable.drawable_shape_4_divider));

        mItemTouchHelper = new ItemTouchHelper(new SimpleItemTouchHelperCallback(adapter));
        mItemTouchHelper.attachToRecyclerView(rv);
        return view;
    }

    private void initData() {
        list = new ArrayList<>();
        for (int i = 0; i < NUM; i++) {
            list.add(new Message(String.valueOf(i + 1), (i + 1), false));
        }
    }

    @Override
    public void startDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }

    @Override
    public void startSwipe(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startSwipe(viewHolder);
    }
}