package com.example.hades.dagger2._14_not_support_static_injectons.dagger2;

import com.example.hades.dagger2._14_not_support_static_injectons.IAnimal;

import javax.inject.Inject;

public class Dagger2Activity {
    @Inject
    IAnimal mB;

    public void onCreate() {
        DaggerDagger2ActivityComponent.builder().animalModule(new AnimalModule(true)).build().inject(this);

        System.out.println(mB.getName());
    }
}