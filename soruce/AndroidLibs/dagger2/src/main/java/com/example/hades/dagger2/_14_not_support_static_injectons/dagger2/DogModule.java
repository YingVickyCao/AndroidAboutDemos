package com.example.hades.dagger2._14_not_support_static_injectons.dagger2;

import com.example.hades.dagger2._14_not_support_static_injectons.Dog;
import com.example.hades.dagger2._14_not_support_static_injectons.IAnimal;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class DogModule {
    @Provides
    @Singleton
    public IAnimal provideDog() {
        return new Dog();
    }
}