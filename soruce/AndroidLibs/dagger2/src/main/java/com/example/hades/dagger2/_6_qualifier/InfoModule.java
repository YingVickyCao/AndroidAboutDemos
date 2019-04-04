package com.example.hades.dagger2._6_qualifier;

import dagger.Module;
import dagger.Provides;

import javax.inject.Named;
import javax.inject.Singleton;

@Module(includes = {PersonModule.class})
public class InfoModule {
    @Provides
    @Singleton
//    Info provideInfo(final @Second Person person) {
    Info provideInfo(final @Named("Second") Person person) {
        return new Info(person);
    }
}