package com.example.hades.dagger2._13_created_twice_when_live_together.dagger2;

import com.example.hades.dagger2._13_created_twice_when_live_together.A;
import com.example.hades.dagger2._13_created_twice_when_live_together.B;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module(includes = {AModule.class})
public class BModule {
    @Provides
    @Singleton
    public B provideB(A a) {
        B b = new B(a);
        System.out.println("Dagger2 BModule@" + hashCode() + "," + b.getInfo());
        System.out.println();
        return b;
    }
}