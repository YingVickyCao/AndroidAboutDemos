package com.example.hades.dagger2._10_subgraphs._subclass._inject_last_child;

import com.example.hades.dagger2.Log;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class AModule {
    @Singleton
    @Provides
    public A provideM() {
        A a = new A();
        Log.d(this.getClass().getSimpleName(), this.getClass().getSimpleName() + "@" + hashCode() + "," + a.info());
        return a;
    }
}
