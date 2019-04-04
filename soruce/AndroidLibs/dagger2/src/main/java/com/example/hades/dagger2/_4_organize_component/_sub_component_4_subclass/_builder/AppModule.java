package com.example.hades.dagger2._4_organize_component._sub_component_4_subclass._builder;

import dagger.Module;
import dagger.Provides;

@Module(includes = {AModule.class}, subcomponents = DebugAppComponent.class)
public class AppModule {

    private App mApp;

    public AppModule(App app) {
        mApp = app;
    }

    @Provides
    public App provideApp() {
        return mApp;
    }
}