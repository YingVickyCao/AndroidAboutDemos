package com.example.hades.dagger2._9_add_multi_component_in_one_component._2;

import dagger.Module;
import dagger.Provides;

@Module
public class BModule {

    @Provides
    public B provideB() {
        return new B();
    }
}