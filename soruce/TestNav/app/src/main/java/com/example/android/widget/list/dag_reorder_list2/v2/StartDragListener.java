package com.example.android.widget.list.dag_reorder_list2.v2;

import androidx.recyclerview.widget.RecyclerView;

public interface StartDragListener {
    boolean OnLongClickDragListener();

    void startDrag(RecyclerView.ViewHolder viewHolder);

    void startSwipe(RecyclerView.ViewHolder viewHolder);

    void hideLoading();

    void showLoading();
}