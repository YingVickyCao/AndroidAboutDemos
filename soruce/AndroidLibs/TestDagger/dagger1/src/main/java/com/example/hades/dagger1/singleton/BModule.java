package com.example.hades.dagger1.singleton;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(injects = B.class)
public class BModule {
    @Singleton
    @Provides
    B provideB() {
        return new B();
    }
}
