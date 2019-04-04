package com.example.hades.dagger2._10_subgraphs._subclass._plus_4_interface_2;

import com.example.hades.dagger2.Log;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module(includes = {AModule.class})
public class BModule {
    private static final String TAG = BModule.class.getSimpleName();

    public BModule() {
        Log.d(TAG, "BModule()@" + hashCode());
    }

    @Provides
    @Singleton
    public B provideB(IA a) {
        Log.d(TAG, "provideB@" + hashCode());
        return new B(a);
    }
}