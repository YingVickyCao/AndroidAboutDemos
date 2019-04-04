package com.hades.android.example.gson;

public class StuHasTransient {
    public String firstName;
    public String lastName;
    public String sex;
    public transient short age;

    public StuHasTransient(String firstName, String lastName, String sex, short age) {
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
