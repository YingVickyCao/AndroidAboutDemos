package com.hades.android.example.gson;

import com.google.gson.annotations.SerializedName;

public class StuHasSerializedName {
    private String name;
    private int age;

    @SerializedName("emailAddress")
    private String email_address;

    public StuHasSerializedName(String name, int age, String email_address) {
        this.name = name;
        this.age = age;
        this.email_address = email_address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", email_address='" + email_address + '\'' +
                '}';
    }
}
