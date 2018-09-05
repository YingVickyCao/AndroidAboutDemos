package com.hades.android.example.android_about_demos.gson.bean;

public class Stu {
    private String firstName = "Catty";
    private String lastName = "Smith";
    private String sex = "male";
    private short age = 25;

    @Override
    public String toString() {
        return "{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                '}';
    }
}
