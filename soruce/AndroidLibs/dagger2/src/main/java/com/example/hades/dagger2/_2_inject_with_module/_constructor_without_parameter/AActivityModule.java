package com.example.hades.dagger2._2_inject_with_module._constructor_without_parameter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AActivityModule {
    @Singleton
    @Provides
    A provideA() {
        return new A();
    }
}