package com.example.hades.togetherdagger1anddagger2;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class Dagger2Module {
    @Provides
    @Singleton
    B provideB(@First Dagger1Module.A a) {
        return new B(a);
    }

    @First
    @Provides
    @Singleton
    Dagger1Module.A provideA() {
        return new Dagger1Module.A();
    }
}
