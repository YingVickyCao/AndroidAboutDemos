package com.hades.example.android.widget._list._recyclerview._dag_reorder_list.v1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.BaseFragment;

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
public class DragAndReorderListFragment extends BaseFragment implements StartDragListener {
    private static final String TAG = DragAndReorderListFragment.class.getSimpleName();

    private final static int NUM = 50;

    private RecyclerView rv;
    private ItemTouchHelperAdapter adapter;
    private List<Message> list;
    private List<Message> list2;
    private ItemTouchHelper mItemTouchHelper;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.widget_recyclerview_4_drag_reorder_list_v1, container, false);
        initData();

        rv = view.findViewById(R.id.rv);
        rv.setLayoutManager(new CustomLinearLayoutManager(view.getContext()));
        adapter = new ItemTouchHelperAdapter(list);
        adapter.setStartDragListener(this);
        rv.setAdapter(adapter);

        // ERROR: not work
//        DividerItemDecoration itemDecoration = new DividerItemDecoration(rv.getContext(), LinearLayoutManager.VERTICAL);
//        itemDecoration.setDrawable(getResources().getDrawable(R.drawable.drawable_shape_4_divider_vertical));
//        rv.addItemDecoration(itemDecoration);

        // Worked.But when drag, div can swipe together with dragged item.
//        rv.addItemDecoration(new SimpleDividerItemDecoration(rv.getContext(), R.drawable.drawable_shape_4_divider_vertical));

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