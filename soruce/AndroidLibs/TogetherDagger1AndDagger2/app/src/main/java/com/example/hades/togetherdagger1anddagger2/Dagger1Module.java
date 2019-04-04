package com.example.hades.togetherdagger1anddagger2;

import javax.inject.Inject;
import javax.inject.Inject1;
import javax.inject.Singleton;
import javax.inject.Singleton1;

import dagger.Module1;
import dagger.Provides1;

@Module1(injects = MainActivity.class)
public class Dagger1Module {
    @Provides1
    @Singleton1
    B provideB(final A a) {
        return new B(a);
    }

//    @First
//    @Provides1
//    @Singleton1
//    B.A provieA(){
//        return new B.A();
//    }

    @Singleton1
    @Singleton
    static class A {

        @Inject1
        @Inject
        /**
         * This class can be injected by both dagger 1 and 2
         */
        public A() {
            //no op
        }

        public String randomValue() {
            //for learning purpose
            return "34290df320e2k0id";
        }
    }
}
