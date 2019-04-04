package com.example.hades.dagger2._4_organize_component._sub_component_4_subclass._plus;

import dagger.Module;
import dagger.Provides;

// ERROR:DebugAppComponent doesn't have info @Subcomponent.Builder, which is required when used with @Module.subcomponents
//@Module(includes = {DataModule.class}, subcomponents = DebugAppComponent.class)
@Module(includes = {AModule.class})
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