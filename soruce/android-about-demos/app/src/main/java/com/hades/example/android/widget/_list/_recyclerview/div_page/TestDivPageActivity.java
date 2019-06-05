package com.hades.example.android.widget._list._recyclerview.div_page;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hades.example.android.R;
import com.hades.example.android.lib.mock.DummyItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestDivPageActivity extends AppCompatActivity implements MainAdapter.LoadMoreListener {

    List<DummyItem> list = new ArrayList<>();
    RecyclerView recyclerView;

    MainAdapter adapter;

    private LoadMoreScrollListener mLoadMoreScrollListener;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {

                case 1:
                    adapter.setErrorStatus();
                    break;
                case 2:
                    adapter.setLastedStatus();
                    break;
                case 3:
                    List<DummyItem> list = new ArrayList<>();
                    for (int i = 0; i < 10; i++) {
                        list.add(new DummyItem(i + 1, "Item" + i, i + 1));
                    }
                    adapter.addList(list);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.widget_recyclerview_div_page);

        recyclerView = (RecyclerView) findViewById(R.id.recycle_view);

        for (int i = 0; i < 20; i++) {
            list.add(new DummyItem(i + 1, "Item" + i, i + 1));
        }

        adapter = new MainAdapter(this, list, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mLoadMoreScrollListener = new LoadMoreScrollListener();
        recyclerView.addOnScrollListener(mLoadMoreScrollListener);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        recyclerView.removeOnScrollListener(mLoadMoreScrollListener);
    }

    @Override
    public void loadMoreData() {
        if (adapter.isLoading()) {
            return;
        }
        adapter.setLoading(true);
        Random random = new Random();
        int randomInt = random.nextInt(3) + 1;
        Log.v("dyp", "random:" + randomInt);
        if (randomInt == 1) {
            handler.sendEmptyMessageDelayed(1, 3000);
        } else if (randomInt == 2) {
            handler.sendEmptyMessageDelayed(2, 3000);
        } else {
            handler.sendEmptyMessageDelayed(3, 3000);
        }
    }
}