package com.example.android.widget.list.dag_reorder_list2.v2;

import java.util.List;

public class Message {
    private String info;
    private int id;
    private boolean isChecked;
    private List<Child> children;
    private boolean isCollapse;

    public Message(String info, int id, boolean isChecked, List<Child> items) {
        this.info = info;
        this.id = id;
        this.isChecked = isChecked;
        this.children = items;
    }

    public String getInfo() {
        return info;
    }

    public int getId() {
        return id;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public List<Child> getChildren() {
        return children;
    }

    public void setCollapse(boolean collapse) {
        isCollapse = collapse;
    }

    public boolean isCollapse() {
        return isCollapse;
    }
}