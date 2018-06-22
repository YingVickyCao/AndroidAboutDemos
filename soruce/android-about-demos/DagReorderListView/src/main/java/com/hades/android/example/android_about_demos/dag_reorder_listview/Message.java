package com.hades.android.example.android_about_demos.dag_reorder_listview;

public class Message {
    private String username;
    private String message;
    private int id;

    public Message(String username, String message, int id) {
        this.username = username;
        this.message = message;
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
