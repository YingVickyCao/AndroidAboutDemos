package com.hades.android.example.android_about_demos.dag_reorder_listview;

public class Message { ;
    private String info;
    private int id;


    public Message(String info, int id) {
        this.info = info;
        this.id = id;
    }


    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}