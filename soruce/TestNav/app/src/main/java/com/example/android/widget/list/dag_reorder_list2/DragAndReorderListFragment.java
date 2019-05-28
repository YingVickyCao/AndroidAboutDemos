package com.example.android.widget.list.dag_reorder_list2;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
    private List<String> group;
    private List<Item> mItems;
    private ItemTouchHelper mItemTouchHelper;

    private String[] groupNames = {"A", "B", "C", "D", "E", "F", "G"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.widget_recyclerview_4_drag_reorder_list, container, false);
        initData();

        rv = view.findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(view.getContext()));
        adapter = new ItemTouchHelperAdapter(mItems);
        adapter.setStartDragListener(this);
        rv.setAdapter(adapter);

        // ERROR: not work
//        DividerItemDecoration itemDecoration = new DividerItemDecoration(rv.getContext(), LinearLayoutManager.VERTICAL);
//        itemDecoration.setDrawable(getResources().getDrawable(R.drawable.drawable_shape_4_divider_vertical));
//        rv.addItemDecoration(itemDecoration);

        // Worked.But when drag, div can swipe together with dragged item.
//        rv.addItemDecoration(new SimpleDividerItemDecoration(rv.getContext(), R.drawable.drawable_shape_4_divider_vertical));

//        mItemTouchHelper = new ItemTouchHelper(new SimpleItemTouchHelperCallback(adapter));
//        mItemTouchHelper.attachToRecyclerView(rv);
        return view;
    }

    private void initData() {
        list = new ArrayList<>();
        for (int i = 0; i < NUM; i++) {
            list.add(new Message(String.valueOf(i + 1), (i + 1), false));
        }

        group = new ArrayList<>();
        for (int i = 0; i < NUM; i++) {
            list.add(new Message(String.valueOf(i + 1), (i + 1), false));
        }

        mItems = new ArrayList<>();
        for (int i = 0; i < groupNames.length; i++) {
            Group group = new Group();
            group.position = i;
            group.title = groupNames[i];
            mItems.add(group);

            int count = (int) (Math.random() * 10) % 4 + 1;
            for (int j = 0; j < count; j++) {
                Child child = new Child();
                child.position = j;
                child.groupPos = i;
                child.groupName = group.title;
                mItems.add(child);
            }
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