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
public class DragAndReorderListFragment extends Fragment {
    private static final String TAG = DragAndReorderListFragment.class.getSimpleName();

    private final static int NUM = 5;

    private RecyclerView lv1;

    private RecyclerView lv2;
    private ItemTouchHelperAdapter adapter1;
    private ItemTouchHelperAdapter adapter2;
    private List<Message> list1;
    private List<Message> list2;
    private ItemTouchHelper mItemTouchHelper1;
    private ItemTouchHelper mItemTouchHelper2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.widget_recyclerview_4_drag_reorder_list_v2, container, false);
        initList1Data();
        initList2Data();

        lv1 = view.findViewById(R.id.lv1);
        lv2 = view.findViewById(R.id.lv2);

        lv1.setLayoutManager(new LinearLayoutManager(view.getContext()));
        lv2.setLayoutManager(new LinearLayoutManager(view.getContext()));

        adapter1 = new ItemTouchHelperAdapter(list1);
        adapter2 = new ItemTouchHelperAdapter(list2);

        adapter1.setStartDragListener(new StartDragListener() {
            @Override
            public void startDrag(RecyclerView.ViewHolder viewHolder) {
                updateList1CollapseStatus(true);
                adapter1.notifyDataSetChanged();
                mItemTouchHelper1.startDrag(viewHolder);
            }

            @Override
            public void startSwipe(RecyclerView.ViewHolder viewHolder) {
                mItemTouchHelper1.startSwipe(viewHolder);
            }
        });

        adapter2.setStartDragListener(new StartDragListener() {
            @Override
            public void startDrag(RecyclerView.ViewHolder viewHolder) {
                updateList2CollapseStatus(true);
                adapter2.notifyDataSetChanged();
                mItemTouchHelper1.startDrag(viewHolder);
            }

            @Override
            public void startSwipe(RecyclerView.ViewHolder viewHolder) {
                mItemTouchHelper2.startSwipe(viewHolder);
            }
        });

        lv1.setAdapter(adapter1);
        lv2.setAdapter(adapter2);

        mItemTouchHelper1 = new ItemTouchHelper(new SimpleItemTouchHelperCallback(adapter1));
        mItemTouchHelper2 = new ItemTouchHelper(new SimpleItemTouchHelperCallback(adapter2));

        mItemTouchHelper1.attachToRecyclerView(lv1);
        mItemTouchHelper2.attachToRecyclerView(lv2);
        return view;
    }

    private void initList1Data() {
        list1 = new ArrayList<>();
        for (int i = 0; i < NUM; i++) {
            List<Child> childList = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                childList.add(new Child("Group 1 -  Child " + (i + 1) + "_" + (j + 1)));
            }
            list1.add(new Message(String.valueOf(i + 1), (i + 1), false, childList));
        }
    }

    private void initList2Data() {
        list2 = new ArrayList<>();
        for (int i = 0; i < NUM; i++) {
            List<Child> childList = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                childList.add(new Child("Group 2 - Child " + (i + 1) + "_" + (j + 1)));
            }
            list2.add(new Message(String.valueOf(i + 1), (i + 1), false, childList));
        }
    }

    private void updateCollapseStatus(List<Message> list, boolean isCollapse) {
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

    private void updateList1CollapseStatus(boolean isCollapse) {
        updateCollapseStatus(list1, isCollapse);
    }

    private void updateList2CollapseStatus(boolean isCollapse) {
        updateCollapseStatus(list2, isCollapse);
    }
}