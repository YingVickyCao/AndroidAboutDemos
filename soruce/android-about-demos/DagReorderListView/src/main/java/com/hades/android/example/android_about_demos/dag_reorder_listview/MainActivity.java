package com.hades.android.example.android_about_demos.dag_reorder_listview;

import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static android.support.v7.widget.helper.ItemTouchHelper.ACTION_STATE_DRAG;

/**
 * Ref:
 * https://blog.csdn.net/qq_30379689/article/details/52463139
 * https://blog.csdn.net/minwenping/article/details/69829704
 * https://www.jianshu.com/p/4eff036360da
 */
public class MainActivity extends AppCompatActivity implements RecycleViewAdapter.startDragListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    private final static int NUM = 5;

    private RecyclerView rv;
    private RecycleViewAdapter adapter;
    private List<Message> list;
    private ItemTouchHelper mItemTouchHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.widget_recyclerview_drag_reorder);

        initData();

        rv = findViewById(R.id.rv);
//                rv.addItemDecoration(new MyDecoration(this, LinearLayoutManager.VERTICAL, R.drawable.divider));
//        rv.addItemDecoration(new MyDecoration(this, LinearLayoutManager.VERTICAL, 20, getResources().getColor(R.color.colorAccent)));
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecycleViewAdapter(list);
        adapter.setStartDragListener(this);
        rv.setAdapter(adapter);

        //为RecycleView绑定触摸事件
        mItemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                //首先回调的方法 返回int表示是否监听该方向
//                int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;//拖拽
//                int swipeFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;//侧滑删除
//                return makeMovementFlags(dragFlags, swipeFlags);

                int dragFlags;
                Log.d(TAG, "getMovementFlags:" + viewHolder.getAdapterPosition());
                if (viewHolder.getAdapterPosition() == 0) {// 当第一个，只能向下拖动
                    Log.d(TAG, "getMovementFlags: first");
                    dragFlags = ItemTouchHelper.DOWN;
                } else if (viewHolder.getAdapterPosition() == (list.size() - 1)) {// 当最后一个，只能向上拖动
                    Log.d(TAG, "getMovementFlags: last");
                    dragFlags = ItemTouchHelper.UP;
                } else {// 当中间一个，能向上/下拖动
                    Log.d(TAG, "getMovementFlags: middle");
                    dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;//拖拽
                }

//                dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;//拖拽
                return makeFlag(ACTION_STATE_DRAG, dragFlags);
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                //滑动事件
                Collections.swap(list, viewHolder.getAdapterPosition(), target.getAdapterPosition());
                adapter.notifyItemMoved(viewHolder.getAdapterPosition(), target.getAdapterPosition());
                return true;
            }

            @Override
            public void onMoved(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, int fromPos, RecyclerView.ViewHolder target, int toPos, int x, int y) {
                Log.d(TAG, "onMoved: fromPos=" + fromPos + ",toPos=" + toPos + ",x=" + x + ",y=" + y);
                super.onMoved(recyclerView, viewHolder, fromPos, target, toPos, x, y);
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                //侧滑事件
//                list.remove(viewHolder.getAdapterPosition());
//                adapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }

            @Override
            public boolean isLongPressDragEnabled() {
                return true;
            }

            @Override
            public boolean isItemViewSwipeEnabled() {
                return super.isItemViewSwipeEnabled();
            }

            @Override
            public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
                super.onSelectedChanged(viewHolder, actionState);
                Log.d(TAG, "onSelectedChanged:actionState= " + actionState);
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
                Log.d(TAG, "onMoved: dX=" + dX + ",dY=" + dY + ",actionState=" + actionState + ",isCurrentlyActive=" + isCurrentlyActive);
            }

            @Override
            public int interpolateOutOfBoundsScroll(RecyclerView recyclerView, int viewSize, int viewSizeOutOfBounds, int totalSize, long msSinceStartScroll) {
                return super.interpolateOutOfBoundsScroll(recyclerView, viewSize, viewSizeOutOfBounds, totalSize, msSinceStartScroll);
            }
        });
        mItemTouchHelper.attachToRecyclerView(rv);
    }

    private void initData() {
        list = new ArrayList<>();
        for (int i = 0; i < NUM; i++) {
            list.add(new Message(String.valueOf(i + 1), String.valueOf(System.currentTimeMillis()), "Message of " + (i + 1), R.drawable.ic_launcher_round));
        }
    }

    @Override
    public void startDragItem(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }
}