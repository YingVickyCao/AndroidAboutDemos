package com.example.hades.dagger2._10_subgraphs._subclass._plus;

import com.example.hades.dagger2.Log;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class AModule {
    private static final String TAG = AModule.class.getSimpleName();

    public AModule() {
        Log.d(TAG, TAG + "DataModule()@" + hashCode());
    }

    @Provides
    @Singleton
    public A provideA() {
        Log.d(TAG, "provideA()@" + hashCode());
        return new A();
    }
}