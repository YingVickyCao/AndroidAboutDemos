package com.example.android.widget.list.dag_reorder_list2.v2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

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

    private View loadingContainer;

    private List<Message> list1;
    private List<Message> list2;
    private ItemTouchHelper mItemTouchHelper1;
    private ItemTouchHelper mItemTouchHelper2;

    private OpenedPage mOpenedPage = new OpenedPage();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initList1Data();
        initList2Data();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.widget_recyclerview_4_drag_reorder_list_v2, container, false);

        loadingContainer = view.findViewById(R.id.loadingContainer);

        RecyclerView lv1 = view.findViewById(R.id.lv1);
        RecyclerView lv2 = view.findViewById(R.id.lv2);

        lv1.setLayoutManager(getLinearLayoutManager());
        lv2.setLayoutManager(getLinearLayoutManager());

        lv1.setHasFixedSize(true);// PO
        lv2.setHasFixedSize(true);// PO

        ItemTouchHelperAdapter adapter1 = new ItemTouchHelperAdapter(list1, getActivity());
        ItemTouchHelperAdapter adapter2 = new ItemTouchHelperAdapter(list2, getActivity());

        adapter1.setGroupResId(R.layout.widget_recyclerview_4_drag_reorder_item_view_v2_1);
        adapter2.setGroupResId(R.layout.widget_recyclerview_4_drag_reorder_item_view_v2_2);

        IDragView startDragListener1 = new IDragView() {

            @Override
            public void startDrag(RecyclerView.ViewHolder viewHolder) {
                mItemTouchHelper1.startDrag(viewHolder);
            }

            @Override
            public void endDrag() {
                adapter1.resetIsOnDragTag();
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

            @Override
            public void openPage(Message bean, boolean isGroup, String title, String childTitle) {
                Toast.makeText(getContext(), "Open " + (isGroup ? title : childTitle), Toast.LENGTH_SHORT).show();
                mOpenedPage.updateOpenedPage(title, childTitle, "Group1", isGroup);
            }
        };

        IDragView startDragListener2 = new IDragView() {
            @Override
            public void startDrag(RecyclerView.ViewHolder viewHolder) {
                mItemTouchHelper2.startDrag(viewHolder);
            }

            @Override
            public void endDrag() {
                adapter2.resetIsOnDragTag();
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

            @Override
            public void openPage(Message bean, boolean isGroup, String title, String childTitle) {
                Toast.makeText(getContext(), "Open " + bean.getTitle(), Toast.LENGTH_SHORT).show();
                mOpenedPage.updateOpenedPage(title, childTitle, "Group2", isGroup);
            }
        };

        adapter1.setDragView(startDragListener1);
        adapter2.setDragView(startDragListener2);

        lv1.setAdapter(adapter1);
        lv2.setAdapter(adapter2);

        mItemTouchHelper1 = new ItemTouchHelper(new SimpleItemTouchHelperCallback(adapter1, startDragListener1));
        mItemTouchHelper2 = new ItemTouchHelper(new SimpleItemTouchHelperCallback(adapter2, startDragListener2));

        mItemTouchHelper1.attachToRecyclerView(lv1);
        mItemTouchHelper2.attachToRecyclerView(lv2);

        TextView desc = view.findViewById(R.id.desc);
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
            Message message = new Message(String.valueOf(i + 1), (i + 1), childList);
            message.setExpand(false);
            list.add(message);
        }
        return list;
    }

    void hideLoading() {
        getActivity().runOnUiThread(() -> loadingContainer.setVisibility(View.GONE));
    }

    void showLoading() {
        getActivity().runOnUiThread(() -> loadingContainer.setVisibility(View.VISIBLE));
    }
}