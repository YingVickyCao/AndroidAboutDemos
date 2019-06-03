package com.example.android.widget.list.dag_reorder_list2.v2;

import android.graphics.Canvas;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;


public class SimpleItemTouchHelperCallback extends ItemTouchHelper.Callback {
    private static final String TAG = SimpleItemTouchHelperCallback.class.getSimpleName();

    private ItemTouchHelperAdapter mAdapter;
    private IDragView mStartDragListener;

    SimpleItemTouchHelperCallback(ItemTouchHelperAdapter adapter, IDragView startDragListener) {
        mAdapter = adapter;
        mStartDragListener = startDragListener;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
//        Log.d(TAG, "getMovementFlags: thread name=" + Thread.currentThread().getName() + ",thread id=" + Thread.currentThread().getId()); // getMovementFlags: thread name=Thread-8,thread id=1847
        return makeMovementFlags(getDragDirectionsFlags(viewHolder), swipeFlags());
//        return makeFlag(ACTION_STATE_DRAG, getDragDirectionsFlags(viewHolder));
    }

    private boolean isFirst(RecyclerView.ViewHolder viewHolder) {
        return viewHolder.getAdapterPosition() == 0;
    }

    private boolean isLast(RecyclerView.ViewHolder viewHolder) {
        return viewHolder.getAdapterPosition() == (mAdapter.mList.size() - 1);
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
//        Log.d(TAG, "onChildDraw: thread name=" + Thread.currentThread().getName() + ",thread id=" + Thread.currentThread().getId()); // onChildDraw: thread name=main,thread id=2
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
//        Log.d(TAG, "onMove: thread name=" + Thread.currentThread().getName() + ",thread id=" + Thread.currentThread().getId()); // onMove: thread name=main,thread id=2
        return mAdapter.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
    }

    // ---
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
//        Log.d(TAG, "onSwiped: thread name=" + Thread.currentThread().getName() + ",thread id=" + Thread.currentThread().getId());
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
//        Log.d(TAG, "onSelectedChanged: thread name=" + Thread.currentThread().getName() + ",thread id=" + Thread.currentThread().getId() + ",actionState=" + actionState);// onSelectedChanged: thread name=main,thread id=2,actionState=0

        if (ItemTouchHelper.ACTION_STATE_DRAG == actionState) {
            if (viewHolder instanceof IItemViewHolder) {
                ((IItemViewHolder) viewHolder).onItemSelected();
            }
        }

        if (ItemTouchHelper.ACTION_STATE_IDLE == actionState) {
            if (null != mStartDragListener) {
                mStartDragListener.endDrag();
            }
        }
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
//        Log.d(TAG, "clearView: thread name=" + Thread.currentThread().getName() + ",thread id=" + Thread.currentThread().getId() + "position=" + viewHolder.getAdapterPosition()); // clearView: thread name=main,thread id=2position=15

        if (viewHolder instanceof IItemViewHolder) {
            ((IItemViewHolder) viewHolder).onItemClear();
        }
    }
}