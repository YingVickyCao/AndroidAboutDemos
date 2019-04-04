package com.example.hades.dagger2._5_singleton._singleton_scope;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class HelloModule {
    @Singleton
    @Provides
    public Hello provideHello() {
        return new Hello();
    }
}
