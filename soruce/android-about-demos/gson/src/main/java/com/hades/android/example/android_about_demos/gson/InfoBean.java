package com.hades.android.example.android_about_demos.gson;

public class InfoBean {
    private String firstName = "John";
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
