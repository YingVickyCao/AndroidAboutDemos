package com.example.hades.dagger2._5_singleton._jvm;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class WorldModule {
    @Singleton
    @Provides
    public World provideWorld() {
        return new World();
    }
}
