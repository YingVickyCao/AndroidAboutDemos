package com.example.hades.dagger2._8_provider_2._live_together;

import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public final class SerializationModule4Dagger2 {

    @Provides
    @Singleton
    public Subject provideSubject(Subject.Info info) {
        return Subject.create(info);
    }

    @Provides
    public ICustomBuilder provideICustomBuilder() {
        return CustomBuilder.create();
    }
}