package com.example.hades.dagger2._coffee;

import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = { DripCoffeeModule.class })
public interface CoffeeShop {
  CoffeeMaker maker();
}
