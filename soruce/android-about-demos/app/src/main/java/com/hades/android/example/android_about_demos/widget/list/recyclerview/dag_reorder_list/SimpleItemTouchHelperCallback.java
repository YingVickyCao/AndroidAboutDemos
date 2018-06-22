package com.hades.android.example.android_about_demos.widget.list.recyclerview.dag_reorder_list;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import static android.support.v7.widget.helper.ItemTouchHelper.ACTION_STATE_DRAG;

public class SimpleItemTouchHelperCallback extends ItemTouchHelper.Callback {
    private ItemTouchHelperAdapter mAdapter;

    public SimpleItemTouchHelperCallback(ItemTouchHelperAdapter adapter) {
        mAdapter = adapter;
    }

    public void setAdapter(ItemTouchHelperAdapter adapter) {
        mAdapter = adapter;
    }

    // TODO: 22/06/2018 refactor
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        //首先回调的方法 返回int表示是否监听该方向
//                int swipeFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;//侧滑删除
//                return makeMovementFlags(dragFlags, swipeFlags);
        int dragFlags = getDragDirectionsFlags(viewHolder);
        return makeFlag(ACTION_STATE_DRAG, dragFlags);
    }

    private boolean isFirst(RecyclerView.ViewHolder viewHolder) {
        return viewHolder.getAdapterPosition() == 0;
    }

    private boolean isLast(RecyclerView.ViewHolder viewHolder) {
        return viewHolder.getAdapterPosition() == (mAdapter.list.size() - 1);
    }

    private int dragDirectionWhenLast() {
        return ItemTouchHelper.UP;
    }

    private int drawDirectionsWhenFirst() {
        return ItemTouchHelper.DOWN;
    }

    private int dragDirectionsWhenMiddle() {
        return ItemTouchHelper.UP | ItemTouchHelper.DOWN;
    }

    private int defaultDragDirections() {
        return ItemTouchHelper.UP | ItemTouchHelper.DOWN;
    }

    private int getDragDirectionsFlags(RecyclerView.ViewHolder viewHolder) {
        int dragFlags;
        if (isFirst(viewHolder)) {
            dragFlags = drawDirectionsWhenFirst();
        } else if (isLast(viewHolder)) {
            dragFlags = dragDirectionWhenLast();
        } else {
            dragFlags = dragDirectionsWhenMiddle();
        }
        return dragFlags;
    }

    // |
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return mAdapter.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
    }

    // ---
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        mAdapter.onItemDismiss(viewHolder.getAdapterPosition());
    }

    // QA: Only drag btn can drag =  When long click item view, cannot drag ?
    @Override
    public boolean isLongPressDragEnabled() {
        return false;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return false;
    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);

        if (viewHolder instanceof IItemTouchHelperViewHolder) {
            ((IItemTouchHelperViewHolder) viewHolder).onItemSelected();
        }
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);

        if (viewHolder instanceof IItemTouchHelperViewHolder) {
            ((IItemTouchHelperViewHolder) viewHolder).onItemClear();
        }
    }
}
