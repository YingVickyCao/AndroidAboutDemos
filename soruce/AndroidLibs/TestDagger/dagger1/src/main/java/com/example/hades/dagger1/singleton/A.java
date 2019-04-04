package com.example.hades.dagger1.singleton;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class A {
    /**
     * FIX_ERROR:error: Scoping annotations are only allowed on concrete types and @Provides methods: com.example.hades.dagger1.singleton.A.A()
     */
//    @Singleton
    @Inject
    public A() {
    }

    public String sayA() {
        return "I am A";
    }
}