package com.example.android.widget.list.dag_reorder_list2.v1;

public class Child extends Item {
    public int groupPos;
    public String groupName;

    @Override
    public int getType() {
        return TYPE_CHILD;
    }
}
