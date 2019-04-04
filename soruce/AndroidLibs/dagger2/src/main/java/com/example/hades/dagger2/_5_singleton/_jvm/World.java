package com.example.hades.dagger2._5_singleton._jvm;

public class World {
    public World() {
    }

    public String hi() {
        return "World@" + hashCode();
    }
}
