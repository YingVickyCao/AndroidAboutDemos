package com.example.hades.dagger2._12_static_class_in_module_2._live_together;

import com.example.hades.dagger2._common._qualifier.One;
import com.example.hades.dagger2._common._qualifier.Two;
import dagger.Module1;
import dagger.Provides1;

import javax.inject.Singleton1;

@Module1(injects = {IA.class}, complete = false, library = true)
public final class DataModule {

    @Provides1
    @One
    CustomNumber provideNumber1() {
        return CustomNumber.one();
    }

    @Provides1
    @Two
    CustomNumber provideNumber2() {
        return CustomNumber.two();
    }

    @Provides1
    @Singleton1
    IA provideA(final DefaultA.Info info) {
        return new DefaultA(info);
    }

}