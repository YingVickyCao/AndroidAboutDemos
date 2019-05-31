package com.example.android.widget.list.dag_reorder_list2.v1;

public abstract class Item {

    public final static int TYPE_GROUP = 0xa01;
    public final static int TYPE_CHILD = 0xa02;

    public int position;

    public abstract int getType();
}
