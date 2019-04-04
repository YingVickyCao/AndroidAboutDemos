package com.example.hades.usedagger2module;

import javax.inject.Inject;

public class Man {
    @Inject
    Car car;

    public Man(Car car) {
        this.car = car;
    }
}
