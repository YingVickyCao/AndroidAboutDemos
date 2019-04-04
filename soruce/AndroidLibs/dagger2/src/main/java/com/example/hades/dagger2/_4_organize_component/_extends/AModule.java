package com.example.hades.dagger2._4_organize_component._extends;

import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class AModule {
    @Singleton
    @Provides
    public A provideA() {
        return new A();
    }
}
