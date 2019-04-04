package com.example.hades.dagger2._13_created_twice_when_live_together.dagger1;

import com.example.hades.dagger2._13_created_twice_when_live_together.A;
import com.example.hades.dagger2._13_created_twice_when_live_together.B;
import dagger.Module1;
import dagger.Provides1;

import javax.inject.Singleton1;

@Module1(includes = {AModule.class}, injects = {B.class}, complete = false, library = true)
public class BModule {
    @Provides1
    @Singleton1
    public B provideB(A a) {
        B b = new B(a);
        System.out.println("Dagger1 BModule@" + hashCode() + "," + b.getInfo());
        System.out.println();
        return b;
    }
}