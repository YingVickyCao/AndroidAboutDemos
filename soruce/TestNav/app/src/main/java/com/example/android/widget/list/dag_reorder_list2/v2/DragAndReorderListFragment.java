package com.example.android.widget.list.dag_reorder_list2.v2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

    private TextView desc;

    private RecyclerView lv1;
    private RecyclerView lv2;

    private View loadingContainer;

    private ItemTouchHelperAdapter adapter1;
    private ItemTouchHelperAdapter adapter2;
    private List<Message> list1;
    private List<Message> list2;
    private ItemTouchHelper mItemTouchHelper1;
    private ItemTouchHelper mItemTouchHelper2;
    private StartDragListener mStartDragListener1;
    private StartDragListener mStartDragListener2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.widget_recyclerview_4_drag_reorder_list_v2, container, false);
        initList1Data();
        initList2Data();


        desc = view.findViewById(R.id.desc);

        lv1 = view.findViewById(R.id.lv1);
        lv2 = view.findViewById(R.id.lv2);

        loadingContainer = view.findViewById(R.id.loadingContainer);

        lv1.setLayoutManager(getLinearLayoutManager());
        lv2.setLayoutManager(getLinearLayoutManager());

        lv1.setHasFixedSize(true);// PO
        lv2.setHasFixedSize(true);// PO

        adapter1 = new ItemTouchHelperAdapter(list1, getActivity());
        adapter2 = new ItemTouchHelperAdapter(list2, getActivity());

        adapter1.setGroupResId(R.layout.widget_recyclerview_4_drag_reorder_item_view_v2_1);
        adapter2.setGroupResId(R.layout.widget_recyclerview_4_drag_reorder_item_view_v2_2);

        mStartDragListener1 = new StartDragListener() {

            @Override
            public void startDrag(RecyclerView.ViewHolder viewHolder) {
                mItemTouchHelper1.startDrag(viewHolder);
            }

            @Override
            public void endDrag() {
                hideLoading();
            }

            @Override
            public void startSwipe(RecyclerView.ViewHolder viewHolder) {
                mItemTouchHelper1.startSwipe(viewHolder);
            }

            @Override
            public void hideLoading() {
                DragAndReorderListFragment.this.hideLoading();
            }

            @Override
            public void showLoading() {
                DragAndReorderListFragment.this.showLoading();
            }
        };

        mStartDragListener2 = new StartDragListener() {

            @Override
            public void startDrag(RecyclerView.ViewHolder viewHolder) {
                mItemTouchHelper2.startDrag(viewHolder);
            }

            @Override
            public void endDrag() {
                hideLoading();
            }

            @Override
            public void startSwipe(RecyclerView.ViewHolder viewHolder) {
                mItemTouchHelper2.startSwipe(viewHolder);
            }

            @Override
            public void hideLoading() {
                DragAndReorderListFragment.this.hideLoading();
            }

            @Override
            public void showLoading() {
                DragAndReorderListFragment.this.showLoading();
            }
        };

        adapter1.setStartDragListener(mStartDragListener1);
        adapter2.setStartDragListener(mStartDragListener2);

        lv1.setAdapter(adapter1);
        lv2.setAdapter(adapter2);

        mItemTouchHelper1 = new ItemTouchHelper(new SimpleItemTouchHelperCallback(adapter1, mStartDragListener1));
        mItemTouchHelper2 = new ItemTouchHelper(new SimpleItemTouchHelperCallback(adapter2, mStartDragListener2));

        mItemTouchHelper1.attachToRecyclerView(lv1);
        mItemTouchHelper2.attachToRecyclerView(lv2);

        desc.setFocusable(true);
        desc.setFocusableInTouchMode(true);
        desc.requestFocus();
        return view;
    }

    private LinearLayoutManager getLinearLayoutManager() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        return linearLayoutManager;
    }

    private void initList1Data() {
        list1 = initListData("Group 1 - Child ", 20);
    }

    private void initList2Data() {
        list2 = initListData("Group 2 - Child ", 50);
    }

    private List<Message> initListData(String childPre, int num) {
        List<Message> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            List<Child> childList = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                childList.add(new Child(childPre + (i + 1) + "_" + (j + 1)));
                if (j >= 4) {
                    break;
                }
            }
            Message message = new Message(String.valueOf(i + 1), (i + 1), false, childList);
            message.setExpand(false);
            list.add(message);
        }
        return list;
    }

    public void hideLoading() {
        getActivity().runOnUiThread(() -> loadingContainer.setVisibility(View.GONE));
    }

    public void showLoading() {
        getActivity().runOnUiThread(() -> loadingContainer.setVisibility(View.VISIBLE));
    }
}