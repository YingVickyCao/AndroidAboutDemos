package com.example.hades.dagger2._4_organize_component._sub_component_4_subclass._plus;

import dagger.Module;
import dagger.Provides;

@Module
public class AModule {

    @Provides
//    @FirstScope
//    @Singleton
    public A provideA() {
        return new A();
    }
}