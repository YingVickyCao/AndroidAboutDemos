package com.example.hades.dagger2._9_add_multi_component_in_one_component._2;

import dagger.Module;
import dagger.Provides;

@Module
public class AModule {
    private static final String TAG = AModule.class.getSimpleName();

    @Provides
    public A provideA() {
        return new A();
    }
}