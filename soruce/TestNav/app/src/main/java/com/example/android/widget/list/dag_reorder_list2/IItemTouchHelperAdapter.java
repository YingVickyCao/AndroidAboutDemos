package com.example.android.widget.list.dag_reorder_list2;

public interface IItemTouchHelperAdapter {
    boolean onItemMove(int fromPosition, int toPosition);

    void onItemDismiss(int position);
}