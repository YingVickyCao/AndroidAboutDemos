package com.hades.android.example.android_about_demos.data_storage.database.bean;

public class ContactInfo {
    private long id = -1;
    private String name;
    private String phone;

    public ContactInfo(long id, String name, String number) {
        this.id = id;
        this.name = name;
        this.phone = number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setNumber(String number) {
        this.phone = number;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setID(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
