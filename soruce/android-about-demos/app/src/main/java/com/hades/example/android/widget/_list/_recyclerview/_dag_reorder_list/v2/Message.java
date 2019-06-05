package com.hades.example.android.widget._list._recyclerview._dag_reorder_list.v2;

import java.util.List;

public class Message {
    private String title;
    private int id;
    private List<Child> children;
    private boolean isExpand;

    public Message(String title, int id, List<Child> items) {
        this.title = title;
        this.id = id;
        this.children = items;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public List<Child> getChildren() {
        return children;
    }

    public void setExpand(boolean expand) {
        isExpand = expand;
    }

    public boolean isExpand() {
        return isExpand;
    }
}