package com.example.hades.dagger2._6_qualifier;

import dagger.Module;
import dagger.Provides;

import javax.inject.Named;
import javax.inject.Singleton;

@Module
public class PersonModule {
//    @First
    @Named("First")
    @Provides
    @Singleton
    public Person providePerson() {
        return new Person();
    }

//    @Second
    @Named("Second")
    @Provides
    @Singleton
    public Person providePerson2() {
        return new Person("B");
    }
}
