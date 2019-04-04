package com.example.hades.dagger2._14_not_support_static_injectons;

public class Dog implements IAnimal {
    public String getName() {
        return getClass().getSimpleName();
    }
}
