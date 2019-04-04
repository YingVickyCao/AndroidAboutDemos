package com.example.hades.dagger2._coffee;

/**
 * https://github.com/google/dagger/tree/master/examples/simple
 */
public class CoffeeApp {

  public static void main(String[] args) {
    CoffeeShop coffeeShop = DaggerCoffeeShop.builder().build();
    coffeeShop.maker().brew();
  }
}
