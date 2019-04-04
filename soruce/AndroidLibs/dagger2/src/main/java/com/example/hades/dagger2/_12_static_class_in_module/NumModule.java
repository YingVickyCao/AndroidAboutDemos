package com.example.hades.dagger2._12_static_class_in_module;

import com.example.hades.dagger2._common._qualifier.One;
import com.example.hades.dagger2._common._qualifier.Two;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module(includes = {DataModule.class})
//@Module
public class NumModule {
    @Provides
    @Singleton
    @One
    int provideOne() {
        return 1;
    }

    @Provides
    @Singleton
    @Two
    int provideTwo() {
        return 2;
    }
}
