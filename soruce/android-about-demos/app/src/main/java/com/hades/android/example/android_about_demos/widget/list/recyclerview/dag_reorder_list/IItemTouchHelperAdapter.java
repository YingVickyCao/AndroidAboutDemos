package com.hades.android.example.android_about_demos.widget.list.recyclerview.dag_reorder_list;

public interface IItemTouchHelperAdapter {
    boolean onItemMove(int fromPosition, int toPosition);

    void onItemDismiss(int position);
}