package com.hades.android.example.gson;

public class Foo<T> {
    private T value;

    public Foo(T _value) {
        value = _value;
    }

    @Override
    public String toString() {
        return "{" +
                "value=" + value +
                '}';
    }
}
