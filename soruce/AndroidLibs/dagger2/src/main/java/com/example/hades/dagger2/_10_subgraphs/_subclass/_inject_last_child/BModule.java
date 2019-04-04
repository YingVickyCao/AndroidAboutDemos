package com.example.hades.dagger2._10_subgraphs._subclass._inject_last_child;

import com.example.hades.dagger2.Log;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module(includes = {AModule.class})
public class BModule {
//    @Provides
//    @Singleton
//    public B provideN() {
//        B b = new B();
//        Log.d(this.getClass().getSimpleName(), this.getClass().getSimpleName() + "@" + hashCode() + "," + b.info2());
//        return b;
//    }

    @Provides
    @Singleton
    public B provideN(A a) {
        B b = new B(a);
        Log.d(this.getClass().getSimpleName(), this.getClass().getSimpleName() + "@" + hashCode() + "," + b.info());
        return b;
    }
}