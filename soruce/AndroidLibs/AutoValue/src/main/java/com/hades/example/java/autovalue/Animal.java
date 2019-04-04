package com.hades.example.java.autovalue;

import com.google.auto.value.AutoValue;

@AutoValue
abstract class Animal {
    static Animal create(String name, int numberOfLegs) {
        // See "How do I...?" below for nested classes.
        return new AutoValue_Animal(name, numberOfLegs);
    }

    abstract public String name();
    abstract public int numberOfLegs();
}