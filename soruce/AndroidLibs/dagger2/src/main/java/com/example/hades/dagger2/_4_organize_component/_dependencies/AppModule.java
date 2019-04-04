package com.example.hades.dagger2._4_organize_component._dependencies;

import com.example.hades.dagger2.Log;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class AppModule {
    private static final String TAG = AppModule.class.getSimpleName();

    private App mApp;

    public AppModule(App app) {
        mApp = app;
        Log.d(TAG, "ComponentActivityModule@" + hashCode());
    }

    @Singleton
    @Provides
    public App provideApp() {
        return mApp;
    }

    @Singleton
    @Provides
    public A provideA() {
        return new A();
    }
}
