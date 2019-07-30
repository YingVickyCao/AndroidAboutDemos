package com.hades.example.android.widget._list._recyclerview._dag_reorder_list.v2;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
public class DragAndReorderListFragment extends Fragment {
    private static final String TAG = DragAndReorderListFragment.class.getSimpleName();

    private View loadingContainer;

    private List<Group> list1;
    private List<Group> list2;

    private ItemTouchHelper mItemTouchHelper1;
    private ItemTouchHelper mItemTouchHelper2;

    private ItemTouchHelperAdapter mAdapter1;
    private ItemTouchHelperAdapter mAdapter2;

    private TextView mCurrentPageView;
    private NestedScrollView nestedScrollView;
    private View group0;
    private Button mMoreOrLess;

    private OpenedPage mOpenedPage = new OpenedPage();

    public static final int TYPE_1 = 1;
    public static final int TYPE_2 = 2;

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
        nestedScrollView = view.findViewById(R.id.nestedScrollView);

        group0 = view.findViewById(R.id.group0);
        mMoreOrLess = view.findViewById(R.id.moreOrLess);

        mCurrentPageView = view.findViewById(R.id.currentPage);
        RecyclerView rv1 = view.findViewById(R.id.lv1);
        RecyclerView rv2 = view.findViewById(R.id.lv2);

        view.findViewById(R.id.moreOrLess).setOnClickListener(v -> moreOrLess());

        SimpleItemTouchHelperCallback simpleItemTouchHelperCallback = new SimpleItemTouchHelperCallback();
        SimpleItemTouchHelperCallback simpleItemTouchHelperCallback2 = new SimpleItemTouchHelperCallback();
        mItemTouchHelper1 = new ItemTouchHelper(simpleItemTouchHelperCallback);
        mItemTouchHelper2 = new ItemTouchHelper(simpleItemTouchHelperCallback2);

        mAdapter1 = setViews(rv1, list1, mItemTouchHelper1, simpleItemTouchHelperCallback);
        mAdapter1.setType(TYPE_1);
        mAdapter1.setLimitMode(true);

        mAdapter2 = setViews(rv2, list2, mItemTouchHelper2, simpleItemTouchHelperCallback2);
        mAdapter2.setType(TYPE_2);

        moreOrLess();
        return view;
    }

    private void moreOrLess() {
        if (getString(R.string.more).equals(mMoreOrLess.getText().toString())) {
            mMoreOrLess.setText(getString(R.string.less));
            mAdapter1.setLimitMode(true);
        } else {
            mMoreOrLess.setText(getString(R.string.more));
            mAdapter1.setLimitMode(false);
        }

        mAdapter1.notifyDataSetChanged();
        mAdapter2.notifyDataSetChanged();
    }

    private ItemTouchHelperAdapter setViews(RecyclerView rv, List<Group> list1, ItemTouchHelper itemTouchHelper, SimpleItemTouchHelperCallback callback) {
        rv.setLayoutManager(getLinearLayoutManager());
        rv.setHasFixedSize(true);// PO
        ItemTouchHelperAdapter adapter = new ItemTouchHelperAdapter(list1, getActivity());

        adapter.setGroupResId(R.layout.widget_recyclerview_4_drag_reorder_item_view_v2_1);
        adapter.setOpenedPage(mOpenedPage);
        IDragView dragListener = new IDragView() {

            @Override
            public void startDrag(RecyclerView.ViewHolder viewHolder) {
                Log.d(TAG, "startDrag: group0 height=" + group0.getMeasuredHeight());
                getActivity().runOnUiThread(() -> itemTouchHelper.startDrag(viewHolder));
            }

            @Override
            public void endDrag() {
                adapter.resetIsOnDragTag();
                hideLoading();
            }

            @Override
            public void startSwipe(RecyclerView.ViewHolder viewHolder) {
                itemTouchHelper.startSwipe(viewHolder);
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
            public void openPage(Group bean, boolean isGroup, String title, String childTitle) {
                String openedCurrentPage = "Open " + (isGroup ? title : childTitle);
                Toast.makeText(getContext(), openedCurrentPage, Toast.LENGTH_SHORT).show();

                mOpenedPage.updateOpenedPage(title, childTitle, "Group1", isGroup);
                mCurrentPageView.setText(openedCurrentPage);
                adapter.notifyDataSetChanged();
            }
        };
        adapter.setDragView(dragListener);
        adapter.setHasStableIds(true);

        rv.setAdapter(adapter);

        callback.setAdapter(adapter);
        callback.setStartDragListener(dragListener);

        itemTouchHelper.attachToRecyclerView(rv);
        return adapter;
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
        list2 = initListData("Group 2 - Child ", 30);
    }

    private List<Group> initListData(String childPre, int num) {
        List<Group> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            List<Child> childList = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                childList.add(new Child(childPre + (i + 1) + "_" + (j + 1)));
                if (j >= 4) {
                    break;
                }
            }
            Group message = new Group(String.valueOf(i + 1), (i + 1), childList);
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