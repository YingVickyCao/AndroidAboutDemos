package com.example.hades.dagger2._12_static_class_in_module_2._dagger1;

import dagger.Module1;
import dagger.Provides1;

import javax.inject.Named1;
import javax.inject.Singleton1;

@Module1(injects = {IA.class}, complete = false, library = true)
public final class DataModule {

    @Provides1
    @Named1(NumsNames.ONE)
    CustomNumber provideNumber1() {
        return CustomNumber.one();
    }

    @Provides1
    @Named1(NumsNames.TWO)
    CustomNumber provideNumber2() {
        return CustomNumber.two();
    }

    @Provides1
    @Singleton1
    IA provideA(final DefaultA.Info info) {
        return new DefaultA(info);
    }

}