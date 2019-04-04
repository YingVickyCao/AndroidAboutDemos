package com.example.hades.dagger2._10_subgraphs._subclass._plus_4_interface_2;

import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {AppModule.class, DebugAppModule.class})
public interface AppComponent {
    void inject(App app);

    void inject(DebugApp debugApp);

    App app();
}