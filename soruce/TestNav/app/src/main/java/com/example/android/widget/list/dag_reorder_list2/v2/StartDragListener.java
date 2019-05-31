package com.example.android.widget.list.dag_reorder_list2.v2;

import androidx.recyclerview.widget.RecyclerView;

public interface StartDragListener {
    void startDrag(RecyclerView.ViewHolder viewHolder);

    void startSwipe(RecyclerView.ViewHolder viewHolder);
}