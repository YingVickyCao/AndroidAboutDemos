package com.example.hades.dagger2._10_subgraphs._subclass._plus_4_interface;

import com.example.hades.dagger2.Log;
import dagger.Module;
import dagger.Provides;

@Module(includes = {AModule.class})
public class AppModule {
    private static final String TAG = AppModule.class.getSimpleName();

    private App mApp;

    public AppModule(App app) {
        Log.d(TAG,  "AppModule(App app)@" + this.hashCode());
        mApp = app;
    }

    @Provides
    public App provideApp() {
        return mApp;
    }
}