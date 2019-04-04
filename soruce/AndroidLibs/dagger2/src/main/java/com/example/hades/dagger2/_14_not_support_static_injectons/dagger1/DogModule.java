package com.example.hades.dagger2._14_not_support_static_injectons.dagger1;

import com.example.hades.dagger2._14_not_support_static_injectons.Dog;
import com.example.hades.dagger2._14_not_support_static_injectons.IAnimal;
import dagger.Module1;
import dagger.Provides1;

import javax.inject.Singleton1;

@Module1(library =true)
public class DogModule {
    @Provides1
    @Singleton1
    public IAnimal provideDog() {
        return new Dog();
    }
}
