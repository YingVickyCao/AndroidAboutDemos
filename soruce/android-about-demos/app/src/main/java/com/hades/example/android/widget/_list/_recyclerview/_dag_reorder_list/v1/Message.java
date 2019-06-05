package com.hades.example.android.widget._list._recyclerview._dag_reorder_list.v1;

public class Message {
    private String info;
    private int id;
    private boolean isChecked;


    public Message(String info, int id, boolean isChecked) {
        this.info = info;
        this.id = id;
        this.isChecked = isChecked;
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
}