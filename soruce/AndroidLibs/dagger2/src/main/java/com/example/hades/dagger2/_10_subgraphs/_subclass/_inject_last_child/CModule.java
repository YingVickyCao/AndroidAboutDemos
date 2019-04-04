package com.example.hades.dagger2._10_subgraphs._subclass._inject_last_child;

import com.example.hades.dagger2.Log;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module(includes = {AModule.class})
public class CModule {
//    @Provides
//    @Singleton
//    public B provideN() {
//        B b = new B();
//        Log.d(this.getClass().getSimpleName(), this.getClass().getSimpleName() + "@" + hashCode() + "," + b.info2());
//        return b;
//    }

    @Provides
    @Singleton
    public C provideN(A a) {
        C c = new C(a);
        Log.d(this.getClass().getSimpleName(), this.getClass().getSimpleName() + "@" + hashCode() + "," + c.info());
        return c;
    }
}