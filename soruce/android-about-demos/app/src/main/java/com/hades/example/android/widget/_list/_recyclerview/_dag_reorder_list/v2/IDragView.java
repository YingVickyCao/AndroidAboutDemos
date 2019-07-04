package com.hades.example.android.widget._list._recyclerview._dag_reorder_list.v2;

import androidx.recyclerview.widget.RecyclerView;

public interface IDragView {
    void startDrag(RecyclerView.ViewHolder viewHolder);

    void endDrag();

    void startSwipe(RecyclerView.ViewHolder viewHolder);

    void hideLoading();

    void showLoading();

    void openPage(Group bean, boolean isGroup, String title, String childTitle);
}