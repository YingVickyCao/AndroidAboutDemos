package com.hades.example.android.widget._list._recyclerview._dag_reorder_list;

import android.support.v7.widget.RecyclerView;

public interface StartDragListener {
    void startDrag(RecyclerView.ViewHolder viewHolder);

    void startSwipe(RecyclerView.ViewHolder viewHolder);
}