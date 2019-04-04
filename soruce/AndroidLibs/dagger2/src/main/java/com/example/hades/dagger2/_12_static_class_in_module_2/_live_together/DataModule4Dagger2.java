package com.example.hades.dagger2._12_static_class_in_module_2._live_together;

import com.example.hades.dagger2._common._qualifier.One;
import com.example.hades.dagger2._common._qualifier.Two;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public final class DataModule4Dagger2 {

    @Provides
    @One
    CustomNumber provideNumber1() {
        return CustomNumber.one();
    }

    @Provides
    @Two
    CustomNumber provideNumber2() {
        return CustomNumber.two();
    }

    @Provides
    @Singleton
    IA provideA(final DefaultA.Info info) {
        return new DefaultA(info);
    }
}