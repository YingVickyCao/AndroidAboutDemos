package com.example.hades.dagger2._8_provider_2._live_together;

import dagger.Module1;
import dagger.Provides1;

import javax.inject.Singleton1;

@Module1(injects = {ICustomBuilder.class, Subject.class})
public final class SerializationModule {

    @Provides1
    @Singleton1
    public Subject provideSubject(Subject.Info info) {
        return Subject.create(info);
    }

    @Provides1
    public ICustomBuilder provideICustomBuilder() {
        return CustomBuilder.create();
    }
}
