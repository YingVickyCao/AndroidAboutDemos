package com.hades.android.example.android_about_demos.gson.bean;

public class Foo<T> {
    private T value;

    public Foo(T _value) {
        value = _value;
    }

    @Override
    public String toString() {
        return "Foo{" +
                "value=" + value +
                '}';
    }
}
