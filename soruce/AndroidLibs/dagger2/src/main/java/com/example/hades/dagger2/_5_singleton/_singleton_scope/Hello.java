package com.example.hades.dagger2._5_singleton._singleton_scope;

public class Hello {
    public Hello() {
    }

    public String hi() {
        return "Hello@" + hashCode();
    }
}
