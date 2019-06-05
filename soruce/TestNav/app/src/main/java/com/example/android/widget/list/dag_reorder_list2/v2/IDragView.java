package com.example.android.widget.list.dag_reorder_list2.v2;

import androidx.recyclerview.widget.RecyclerView;

public interface IDragView {
    void startDrag(RecyclerView.ViewHolder viewHolder);

    void endDrag();

    void startSwipe(RecyclerView.ViewHolder viewHolder);

    void hideLoading();

    void showLoading();

    void openPage(Message bean, boolean isGroup, String title, String childTitle);
}