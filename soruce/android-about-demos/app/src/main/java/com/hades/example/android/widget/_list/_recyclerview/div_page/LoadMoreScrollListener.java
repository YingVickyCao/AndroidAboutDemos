package com.hades.example.android.widget._list._recyclerview.div_page;

import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class LoadMoreScrollListener extends RecyclerView.OnScrollListener {
    private static final String TAG = LoadMoreScrollListener.class.getSimpleName();

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        if (isHavingMore(recyclerView) && isBottom(recyclerView)) {
            MainAdapter adapter = (MainAdapter) recyclerView.getAdapter();
            if (!adapter.isLoading()) {
                adapter.LoadingMore();
            }
        }
    }

    private boolean isHavingMore(RecyclerView recyclerView) {
        MainAdapter adapter = (MainAdapter) recyclerView.getAdapter();
        return adapter.getItemCount() >= adapter.getNUM_IN_ONE_PAGE();
    }

    private boolean isBottom(RecyclerView recyclerView) {
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        MainAdapter adapter = (MainAdapter) recyclerView.getAdapter();

        if (manager instanceof LinearLayoutManager) {
            int lastVisibleItemPosition = ((LinearLayoutManager) manager).findLastVisibleItemPosition();
            Log.v(TAG, "isBottom, lastVisibleItemPosition:" + lastVisibleItemPosition + ",adapter.getItemCount()-1 :" + (adapter.getItemCount() - 1) + ",hasMore:" + adapter.hasMore);
            return adapter.getItemCount() - 1 == lastVisibleItemPosition;
        }
        return false;
    }

}
