package com.hades.example.android.widget._list._recyclerview._dag_reorder_list.v2;

import android.graphics.Canvas;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class SimpleItemTouchHelperCallback extends ItemTouchHelper.Callback {
    private static final String TAG = SimpleItemTouchHelperCallback.class.getSimpleName();

    private ItemTouchHelperAdapter mAdapter;
    private IDragView mStartDragListener;
    private RecyclerView recyclerView;
    private NestedScrollView mNestedScrollView;

    SimpleItemTouchHelperCallback(ItemTouchHelperAdapter adapter, IDragView startDragListener, RecyclerView recyclerView, NestedScrollView nestedScrollView) {
        mAdapter = adapter;
        mStartDragListener = startDragListener;
        this.recyclerView = recyclerView;
        mNestedScrollView = nestedScrollView;
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
            Log.d(TAG, "makeMovementFlags:first");
        } else if (isLast(viewHolder)) {
            dragFlags = dragFlags4Last();
            Log.d(TAG, "makeMovementFlags:lase");
        } else {
            dragFlags = dragFlags4Middle();
            Log.d(TAG, "makeMovementFlags:middle");
        }
        return dragFlags;
    }

    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {// onChildDraw: thread name=main,thread id=2
//        Log.d(TAG, "onChildDraw: thread name=" + Thread.currentThread().getName() + ",thread id=" + Thread.currentThread().getId());

        Log.d(TAG, "onChildDraw: pos=" + viewHolder.getAdapterPosition() + ",dx=" + dX + ",dy=" + dY + ",actionState=" + actionState);
        if (actionState == ItemTouchHelper.ACTION_STATE_DRAG) {
            View itemView = viewHolder.itemView;
            if (viewHolder.getAdapterPosition() == 0) {
                Log.d(TAG, "onChildDraw: top");
                // If Drag (bottom -> top) to  fist , stop drop continue
            } else if (itemView.getBottom() == recyclerView.getHeight()) {
                Log.d(TAG, "onChildDraw: bottom");
            } else {
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        }
    }

    @Override
    public boolean canDropOver(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder current, @NonNull RecyclerView.ViewHolder target) {
        return super.canDropOver(recyclerView, current, target);
    }

    // |
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) { // onMove: thread name=main,thread id=2
        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        Log.d(TAG, "onMove: =" + viewHolder.getAdapterPosition() + ",target =" + target.getAdapterPosition() + ",last=" + layoutManager.findLastCompletelyVisibleItemPosition()); // onMove: thread name=main,thread id=2
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
    public RecyclerView.ViewHolder chooseDropTarget(@NonNull RecyclerView.ViewHolder selected, @NonNull List<RecyclerView.ViewHolder> dropTargets, int curX, int curY) {
        if (null == selected) {
            Log.d(TAG, "chooseDropTarget:  ,curY=" + curY);

            RecyclerView.ViewHolder viewHolder = super.chooseDropTarget(selected, dropTargets, curX, curY);
            return viewHolder;
        } else {
//            if (dropTargets.size() == 1) {
//                RecyclerView.ViewHolder first = dropTargets.get(0);
//                if (null != first && first.getAdapterPosition() + 1 < mAdapter.getItemCount() - 1) {
//                    RecyclerView.ViewHolder second = recyclerView.findViewHolderForAdapterPosition(first.getAdapterPosition() + 1);
//                    if (second != null) {
//                        dropTargets.add(second);
//                    }
//                }
//            }

            List<Integer> dropTargetsPosition = new ArrayList<>();
            for (int i = 0; i < dropTargets.size(); i++) {
                dropTargetsPosition.add(dropTargets.get(i).getAdapterPosition());
            }

            RecyclerView.ViewHolder viewHolder = super.chooseDropTarget(selected, dropTargets, curX, curY);
            if (null != viewHolder) {
                Log.d(TAG, "chooseDropTarget: selected=" + selected.getAdapterPosition() + ",dropTargets=" + dropTargetsPosition.toString() + ",return =" + viewHolder.getAdapterPosition() + ",curY=" + curY
                        + "\n,selected [bottom=" + selected.itemView.getBottom() + ",top=" + selected.itemView.getTop() + ",height=" + selected.itemView.getHeight() + ",y=" + selected.itemView.getY() + "]");
            }

            // not work
//        if ((recyclerView.computeVerticalScrollExtent() + recyclerView.computeVerticalScrollOffset()) >= recyclerView.computeVerticalScrollRange()) {
//            Log.d(TAG, "chooseDropTarget: recyclerView scroll to bottom");
//        }

            // not work
//        boolean isCanScollTop = recyclerView.canScrollVertically(1);
//        Log.d(TAG, "chooseDropTarget: " + (isCanScollTop ? "Can" : "Can not") + " scroll to top" + (isCanScollBottom ? ",Can" : "Can not") + " scroll to bottom");

            // not work
            int scrollY = mNestedScrollView.getScrollY();
//        boolean isCanScollBottom = recyclerView.canScrollVertically(-1);
            View onlyChild = mNestedScrollView.getChildAt(0);
            if (onlyChild.getHeight() <= scrollY + mNestedScrollView.getHeight()) {
                Log.d(TAG, "chooseDropTarget: recyclerView scroll to bottom");
            }
            return viewHolder;
        }
    }

    @Override
    public int interpolateOutOfBoundsScroll(@NonNull RecyclerView recyclerView, int viewSize, int viewSizeOutOfBounds, int totalSize, long msSinceStartScroll) {
        int r = super.interpolateOutOfBoundsScroll(recyclerView, viewSize, viewSizeOutOfBounds, totalSize, msSinceStartScroll);
        Log.d(TAG, "interpolateOutOfBoundsScroll: viewSize=" + viewSize + ",viewSizeOutOfBounds=" + viewSizeOutOfBounds + ",totalSize=" + totalSize + ",msSinceStartScroll=" + msSinceStartScroll + ",r=" + r);
        return r;
    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) { // onSelectedChanged: thread name=main,thread id=2,actionState=0
        if (null != viewHolder) {
            Log.d(TAG, "onSelectedChanged: ,actionState=" + actionState + ",viewHolder=" + viewHolder.getAdapterPosition());// onSelectedChanged: thread name=main,thread id=2,actionState=0
        }
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
        super.onSelectedChanged(viewHolder, actionState);
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {// clearView: thread name=main,thread id=2position=15
        if (viewHolder instanceof IItemViewHolder) {
            ((IItemViewHolder) viewHolder).onItemClear();
        }
        super.clearView(recyclerView, viewHolder);
    }
}