package com.example.hades.dagger2._8_provider;


import com.example.hades.dagger2.Log;
import dagger.Module;
import dagger.Provides;

@Module
public class AModule {
    private static final String TAG = AModule.class.getSimpleName();

    @Provides
    public A provideA() {
        A a = new A();
        Log.d(TAG, "provideA: new A()," + a.info());
        return a;
    }
}