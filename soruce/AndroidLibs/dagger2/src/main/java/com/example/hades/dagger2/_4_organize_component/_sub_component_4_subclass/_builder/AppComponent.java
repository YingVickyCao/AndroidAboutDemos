package com.example.hades.dagger2._4_organize_component._sub_component_4_subclass._builder;

import dagger.Component;

//@Singleton
//@FirstScope
@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(App app);

    App app();

    DebugAppComponent.Builder buildDebugAppComponent();
}