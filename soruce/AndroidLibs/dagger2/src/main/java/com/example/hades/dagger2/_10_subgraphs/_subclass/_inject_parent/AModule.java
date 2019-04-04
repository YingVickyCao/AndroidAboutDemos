package com.example.hades.dagger2._10_subgraphs._subclass._inject_parent;

import dagger.Module;
import dagger.Provides;

@Module
public class AModule {
    @Provides
    public A provideM() {
        return new A();
    }
}
