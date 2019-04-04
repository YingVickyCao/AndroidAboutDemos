package com.example.hades.dagger1.plus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(injects = Two.class)
public class TwoModule {
    @Provides
    @Singleton
    Two provideTwo() {
        return new Two();
    }
}
