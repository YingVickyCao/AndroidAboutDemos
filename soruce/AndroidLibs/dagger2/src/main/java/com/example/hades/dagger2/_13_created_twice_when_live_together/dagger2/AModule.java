package com.example.hades.dagger2._13_created_twice_when_live_together.dagger2;

import com.example.hades.dagger2._13_created_twice_when_live_together.A;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class AModule {
    @Provides
    @Singleton
    public A provideA() {
        A a = new A();
        System.out.println("Dagger2 AModule@" + hashCode() + "," + a.getInfo());
        return a;
    }
}