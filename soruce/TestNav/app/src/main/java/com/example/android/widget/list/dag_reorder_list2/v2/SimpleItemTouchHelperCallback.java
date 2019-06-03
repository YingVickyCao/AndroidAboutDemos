package com.example.android.widget.list.dag_reorder_list2.v2;

import android.graphics.Canvas;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class SimpleItemTouchHelperCallback extends ItemTouchHelper.Callback {
    private ItemTouchHelperAdapter mAdapter;

    SimpleItemTouchHelperCallback(ItemTouchHelperAdapter adapter) {
        mAdapter = adapter;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        return makeMovementFlags(getDragDirectionsFlags(viewHolder), swipeFlags());
//        return makeFlag(ACTION_STATE_DRAG, getDragDirectionsFlags(viewHolder));
    }

    private boolean isFirst(RecyclerView.ViewHolder viewHolder) {
        return viewHolder.getAdapterPosition() == 0;
    }

    private boolean isLast(RecyclerView.ViewHolder viewHolder) {
        return viewHolder.getAdapterPosition() == (mAdapter.list.size() - 1);
    }

    private int dragFlags4Last() {
        return ItemTouchHelper.UP;
    }

    private int dragFlags4First() {
        return ItemTouchHelper.DOWN;
    }

    private int dragFlags4Middle() {
        return ItemTouchHelper.UP | ItemTouchHelper.DOWN;
    }

    private int dragFlags4Default() {
        return ItemTouchHelper.UP | ItemTouchHelper.DOWN;
    }

    private int swipeFlags() {
        return ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
    }

    private int getDragDirectionsFlags(RecyclerView.ViewHolder viewHolder) {
        int dragFlags;
        if (isFirst(viewHolder)) {
            dragFlags = dragFlags4First();
        } else if (isLast(viewHolder)) {
            dragFlags = dragFlags4Last();
        } else {
            dragFlags = dragFlags4Middle();
        }
        return dragFlags;
    }

    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        if (actionState == ItemTouchHelper.ACTION_STATE_DRAG) {
            View itemView = viewHolder.itemView;
            if (itemView.getTop() == 0 && viewHolder.getAdapterPosition() == 0) {
                // If Drag (bottom -> top) to  fist , stop drop continue
            } else if (itemView.getBottom() == recyclerView.getHeight()) {
            } else {
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        }
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

    // QA: Only drag btn can drag =  When long click item view, cannot drag. => false
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
