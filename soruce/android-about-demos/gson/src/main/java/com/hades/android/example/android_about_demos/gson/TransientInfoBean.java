package com.hades.android.example.android_about_demos.gson;

public class TransientInfoBean {
    public String firstName;
    public String lastName;
    public String sex;
    public transient short age;

    public TransientInfoBean(String firstName, String lastName, String sex, short age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.age = age;
    }

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
