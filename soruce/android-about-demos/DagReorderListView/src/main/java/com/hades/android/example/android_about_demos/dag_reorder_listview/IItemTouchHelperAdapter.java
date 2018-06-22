package com.hades.android.example.android_about_demos.dag_reorder_listview;

public interface IItemTouchHelperAdapter {
    boolean onItemMove(int fromPosition, int toPosition);

    void onItemDismiss(int position);
}