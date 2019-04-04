package com.example.hades.dagger2._2_inject_with_module._constructor_with_parameter;

import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class PersonActivityModule {
    private static final String TAG = PersonActivityModule.class.getSimpleName();

    @Singleton
    @Provides
    Person providePerson(Info info) {
        return new Person(info);
    }

    @Singleton
    @Provides
    Info provideInfo() {
        return new Info();
    }
}