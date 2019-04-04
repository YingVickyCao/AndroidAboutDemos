package com.example.hades.dagger2._13_created_twice_when_live_together.dagger1;

import com.example.hades.dagger2._13_created_twice_when_live_together.A;
import dagger.Module1;
import dagger.Provides1;

import javax.inject.Singleton1;

@Module1(injects = {A.class}, complete = false, library = true)
public class AModule {
    @Provides1
    @Singleton1
    public A provideA() {
        A a = new A();
        System.out.println("Dagger1 AModule@" + hashCode() + "," + a.getInfo());
        return a;
    }
}