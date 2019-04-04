package com.example.hades.dagger2._12_static_class_in_module;

import com.example.hades.dagger2._common._qualifier.One;
import com.example.hades.dagger2._common._qualifier.Two;

import javax.inject.Inject;

public class A {
//    @Inject
//    @One
    int one;

//    @Inject
//    @Two

    int two;

    @Inject
    public A(@One int one, @Two int two) {
        this.one = one;
        this.two = two;
    }

    @Override
    public String toString() {
        return "{" +
                "one=" + one +
                ", two=" + two +
                '}';
    }
}
