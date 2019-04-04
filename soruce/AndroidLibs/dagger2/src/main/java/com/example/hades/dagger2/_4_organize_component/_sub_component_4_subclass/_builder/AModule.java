package com.example.hades.dagger2._4_organize_component._sub_component_4_subclass._builder;

import dagger.Module;
import dagger.Provides;

@Module
public class AModule {

    @Provides
//    @Singleton
//    @FirstScope
    public A provideA() {
        return new A();
    }
}