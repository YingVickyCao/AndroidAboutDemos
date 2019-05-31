package com.example.android.widget.list.dag_reorder_list2.v2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.widget.list.dag_reorder_list2.R;

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
public class DragAndReorderListFragment extends Fragment implements StartDragListener {
    private static final String TAG = DragAndReorderListFragment.class.getSimpleName();

    private final static int NUM = 5;

    private RecyclerView rv;
    private ItemTouchHelperAdapter adapter;
    private List<Message> list;
    private ItemTouchHelper mItemTouchHelper;

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

        mItemTouchHelper = new ItemTouchHelper(new SimpleItemTouchHelperCallback(adapter));
        mItemTouchHelper.attachToRecyclerView(rv);
        return view;
    }

    private void initData() {
        list = new ArrayList<>();
        for (int i = 0; i < NUM; i++) {
            List<Child> childList = new ArrayList<>();
            for (int j = 0; j < i + 1; j++) {
                childList.add(new Child("Child " + String.valueOf(i + 1) + "_" + (j + 1)));
            }
            list.add(new Message(String.valueOf(i + 1), (i + 1), false, childList));
        }
    }

    private void updateCollapseStatus(boolean isCollapse) {
        if (null == list) {
            return;
        }

        for (int i = 0; i < list.size(); i++) {
            Message message = list.get(i);
            if (null != message) {
                message.setCollapse(isCollapse);
            }
        }
    }

    @Override
    public void startDrag(RecyclerView.ViewHolder viewHolder) {
        updateCollapseStatus(true);
        adapter.notifyDataSetChanged();
        mItemTouchHelper.startDrag(viewHolder);
    }

    @Override
    public void startSwipe(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startSwipe(viewHolder);
    }
}