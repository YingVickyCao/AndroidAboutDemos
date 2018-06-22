package com.hades.android.example.android_about_demos.dag_reorder_listview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Ref:
 * https://blog.csdn.net/qq_30379689/article/details/52463139
 * https://blog.csdn.net/minwenping/article/details/69829704
 * https://www.jianshu.com/p/4eff036360da
 */
public class MainActivity extends AppCompatActivity implements ItemTouchHelperAdapter.startDragListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    private final static int NUM = 5;

    private RecyclerView rv;
    private ItemTouchHelperAdapter adapter;
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
        adapter = new ItemTouchHelperAdapter(list);
        adapter.setStartDragListener(this);
        rv.setAdapter(adapter);

        mItemTouchHelper = new ItemTouchHelper(new SimpleItemTouchHelperCallback(adapter));
        mItemTouchHelper.attachToRecyclerView(rv);
    }

    private void initData() {
        list = new ArrayList<>();
        for (int i = 0; i < NUM; i++) {
            list.add(new Message(String.valueOf(i + 1), "Message of " + (i + 1), R.drawable.ic_launcher_round));
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