package com.example.hades.dagger2._4_organize_component._sub_component_4_subclass._builder;

import dagger.Module;
import dagger.Provides;

@Module(includes = {AModule.class})
public class BModule {

    @Provides
//    @Singleton
    public B provideB(A a) {
        return new B(a);
    }
}