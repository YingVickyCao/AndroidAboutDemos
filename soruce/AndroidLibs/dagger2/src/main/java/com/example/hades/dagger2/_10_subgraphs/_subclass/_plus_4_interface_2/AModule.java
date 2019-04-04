package com.example.hades.dagger2._10_subgraphs._subclass._plus_4_interface_2;

import com.example.hades.dagger2.Log;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class AModule {
    private static final String TAG = AModule.class.getSimpleName();
    boolean parent;

    public AModule(boolean parent) {
        Log.d(TAG, TAG + "DataModule()@" + hashCode());
        this.parent = parent;
    }

    @Provides
    @Singleton
    public IA provideA() {
        Log.d(TAG, "provideA()@" + hashCode());
        if (parent) {
            return new A();
        } else {
            return new AInChild();
        }
    }
}