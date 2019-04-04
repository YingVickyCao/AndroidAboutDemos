package com.example.hades.dagger2._12_static_class_in_module;

import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
//@Module(includes = {NumModule.class})
public class DataModule {
    @Provides
    @Singleton
    B provideB(A a) {
        return new B(a);
    }

    /*
    public static class A {
        @Inject
        @One
        int one;

        @Inject
        @Two
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
    }*/
}
