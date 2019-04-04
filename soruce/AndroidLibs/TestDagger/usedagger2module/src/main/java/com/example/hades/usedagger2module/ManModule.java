package com.example.hades.usedagger2module;

import dagger.Module;

@Module
public class ManModule {
    public Man provideMan(Car car) {
        return new Man(car);
    }
}
