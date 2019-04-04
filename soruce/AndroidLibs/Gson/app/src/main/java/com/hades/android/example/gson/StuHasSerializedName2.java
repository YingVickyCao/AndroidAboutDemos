package com.hades.android.example.gson;

import com.google.gson.annotations.SerializedName;

public class StuHasSerializedName2 {
    private String name;
    private int age;

    // 三个属性(email、email_address、emailAddress)都中出现任意一个时均可以得到正确的结果 emailAddress
    // 当多种情况同时出时，以最后一个出现的值为准
    @SerializedName(value = "emailAddress", alternate = {"email", "email_address"})
    private String email;

    public StuHasSerializedName2(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                '}';
    }
}
