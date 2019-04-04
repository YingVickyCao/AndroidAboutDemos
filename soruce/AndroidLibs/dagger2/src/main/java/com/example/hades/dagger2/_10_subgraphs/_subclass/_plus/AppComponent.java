package com.example.hades.dagger2._10_subgraphs._subclass._plus;

import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {AppModule.class, DebugAppModule.class})
public interface AppComponent {
    void inject(App app);

    void inject(DebugApp debugApp);

    App app();

//    void plus(DebugAppModule debugAppModule); // not need
}