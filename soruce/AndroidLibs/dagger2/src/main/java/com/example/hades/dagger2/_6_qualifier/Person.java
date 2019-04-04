package com.example.hades.dagger2._6_qualifier;

public class Person {
    private static final String TAG = Person.class.getSimpleName();

    private String name = "A";

    public Person(String name) {
        this.name = name;
    }

    public Person() {
    }

    public String info() {
        return TAG + "@" + hashCode() + ",name=" + name;
    }
}