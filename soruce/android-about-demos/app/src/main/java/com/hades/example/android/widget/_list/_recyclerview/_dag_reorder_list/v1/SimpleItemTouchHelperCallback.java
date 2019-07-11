package com.hades.example.android.widget._list._recyclerview._dag_reorder_list.v1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hades.example.android.R;

/**
 * https://en.proft.me/2017/12/14/how-add-swipe-and-dragdrop-support-recyclerview/
 */
public class SimpleItemTouchHelperCallback extends ItemTouchHelper.Callback {
    private static final String TAG = SimpleItemTouchHelperCallback.class.getSimpleName();

    private ItemTouchHelperAdapter mAdapter;
    private Paint p;

    SimpleItemTouchHelperCallback(ItemTouchHelperAdapter adapter) {
        mAdapter = adapter;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        return makeMovementFlags(getDragDirectionsFlags(viewHolder), swipeFlags());
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
            Log.d(TAG, "getDragDirectionsFlags: first");
            dragFlags = dragFlags4First();
        } else if (isLast(viewHolder)) {
            dragFlags = dragFlags4Last();
            Log.d(TAG, "getDragDirectionsFlags: last");
        } else {
            dragFlags = dragFlags4Middle();
            Log.d(TAG, "getDragDirectionsFlags: middle");
        }
        return dragFlags;
    }

    public static Bitmap getBitmapFromVectorDrawable(Context context, int drawableId) {
        Drawable drawable = ContextCompat.getDrawable(context, drawableId);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            drawable = (DrawableCompat.wrap(drawable)).mutate();
        }

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }

    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
//        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        Log.d(TAG, "onChildDraw: getAdapterPosition=" + viewHolder.getAdapterPosition() + ",getOldPosition=" + viewHolder.getOldPosition() + ",dx=" + dX + ",dy=" + dY + ",actionState=" + actionState + ",isCurrentlyActive=" + isCurrentlyActive);


        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {

            Bitmap icon;
            if (p == null) {
                p = new Paint();
            }

            View itemView = viewHolder.itemView;
            float height = (float) itemView.getBottom() - (float) itemView.getTop();
            float width = height / 3;

            if (dX > 0) {
                p.setColor(Color.parseColor("#388E3C"));
                RectF background = new RectF((float) itemView.getLeft(), (float) itemView.getTop(), dX, (float) itemView.getBottom());
                c.drawRect(background, p);

                float left = (float) itemView.getLeft() + width;
                float top = (float) itemView.getTop() + width;
                float right = (float) itemView.getLeft() + 2 * width;
                float bottom = (float) itemView.getBottom() - width;

                icon = getBitmapFromVectorDrawable(recyclerView.getContext(), R.drawable.drawable_vector_add);
                RectF iconDest = new RectF(left, top, right, bottom);

                c.drawBitmap(icon, null, iconDest, p);
            } else if (dX < 0) {
                p.setColor(Color.parseColor("#D32F2F"));
                RectF background = new RectF((float) itemView.getRight() + dX, (float) itemView.getTop(), (float) itemView.getRight(), (float) itemView.getBottom());
                c.drawRect(background, p);
                icon = getBitmapFromVectorDrawable(recyclerView.getContext(), R.drawable.drawable_vector_delete);

                float left = (float) itemView.getRight() - 2 * width;
                float top = (float) itemView.getTop() + width;
                float right = (float) itemView.getRight() - width;
                float bottom = (float) itemView.getBottom() - width;
                RectF iconDest = new RectF(left, top, right, bottom);

                c.drawBitmap(icon, null, iconDest, p);
            }
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }


        if (actionState == ItemTouchHelper.ACTION_STATE_DRAG) {
            View itemView = viewHolder.itemView;
//            Log.d(TAG, "onChildDraw: left=" + itemView.getLeft() + ",top=" + (float) itemView.getTop() + ",right=" + itemView.getRight() + ",bottom=" + (float) itemView.getBottom());
            Log.d(TAG, "onChildDraw: top=" + itemView.getTop() + ",bottom=" + (float) itemView.getBottom() + ",height=" + recyclerView.getHeight());

            if (itemView.getTop() == 0 && viewHolder.getAdapterPosition() == 0) {
                // If Drag (bottom -> top) to  fist , stop drop continue
                Log.d(TAG, "onChildDraw: top");
//            } else if (itemView.getBottom() == recyclerView.getHeight() && viewHolder.getAdapterPosition() == (recyclerView.getChildCount() - 1)) {
            } else if (itemView.getBottom() == recyclerView.getHeight()) {
                // If Drag (top -> bottom) to  last , stop drop continue
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                Log.d(TAG, "onChildDraw:findLastCompletelyVisibleItemPosition= " + layoutManager.findLastCompletelyVisibleItemPosition() + ",findLastVisibleItemPosition=" + layoutManager.findLastVisibleItemPosition());
//                boolean notAllVisible = layoutManager.findLastVisibleItemPosition() < recyclerView.getChildCount() - 1;
            } else {
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        }
    }

    @Override
    public void onChildDrawOver(@NonNull Canvas c, @NonNull RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDrawOver(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        Log.d(TAG, "onChildDrawOver: getAdapterPosition=" + viewHolder.getAdapterPosition() + ",getOldPosition=" + viewHolder.getOldPosition() + ",dx=" + dX + ",dy=" + dY + ",actionState=" + actionState + ",isCurrentlyActive=" + isCurrentlyActive);

        /*
        if (actionState == ItemTouchHelper.ACTION_STATE_DRAG) {
            View itemView = viewHolder.itemView;
//            Log.d(TAG, "onChildDraw: left=" + itemView.getLeft() + ",top=" + (float) itemView.getTop() + ",right=" + itemView.getRight() + ",bottom=" + (float) itemView.getBottom());
            Log.d(TAG, "onChildDraw: top=" + itemView.getTop() + ",bottom=" + (float) itemView.getBottom() + ",height=" + recyclerView.getHeight());

            if (itemView.getTop() == 0 && viewHolder.getAdapterPosition() == 0) {
                // If Drag (bottom -> top) to  fist , stop drop continue
                Log.d(TAG, "onChildDraw: top");
//            } else if (itemView.getBottom() == recyclerView.getHeight() && viewHolder.getAdapterPosition() == (recyclerView.getChildCount() - 1)) {
            } else if (itemView.getBottom() == recyclerView.getHeight()) {
                // If Drag (top -> bottom) to  last , stop drop continue
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                Log.d(TAG, "onChildDraw:findLastCompletelyVisibleItemPosition= " + layoutManager.findLastCompletelyVisibleItemPosition() + ",findLastVisibleItemPosition=" + layoutManager.findLastVisibleItemPosition());
                Log.d(TAG, "onChildDraw: bottom");
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        }*/
    }

    // |
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        Log.d(TAG, "onMove: origin=" + viewHolder.getAdapterPosition() + ",target pos=" + target.getAdapterPosition());
        return mAdapter.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
    }

    @Override
    public boolean canDropOver(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder current, @NonNull RecyclerView.ViewHolder target) {
        return super.canDropOver(recyclerView, current, target);
    }

    @Override
    public int getBoundingBoxMargin() {
        return super.getBoundingBoxMargin();
    }

    // ---
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        mAdapter.onItemDismiss(viewHolder.getAdapterPosition());
    }

    // QA: Only drag btn can drag =  When long click item view, cannot drag. => false
    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);
        Log.d(TAG, "onSelectedChanged: actionState=" + actionState);

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
