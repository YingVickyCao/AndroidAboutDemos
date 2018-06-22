package com.hades.android.example.android_about_demos.dag_reorder_listview;

import android.support.v7.widget.RecyclerView;

public interface StartDragListener {
    void startDrag(RecyclerView.ViewHolder viewHolder);

    void startSwipe(RecyclerView.ViewHolder viewHolder);
}
