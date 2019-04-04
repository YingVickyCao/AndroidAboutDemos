package com.example.hades.dagger2._14_not_support_static_injectons.dagger2;

import com.example.hades.dagger2._14_not_support_static_injectons.Cat;
import com.example.hades.dagger2._14_not_support_static_injectons.IAnimal;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class CatModule {
    @Provides
    @Singleton
    public IAnimal provideCat() {
        return new Cat();
    }
}