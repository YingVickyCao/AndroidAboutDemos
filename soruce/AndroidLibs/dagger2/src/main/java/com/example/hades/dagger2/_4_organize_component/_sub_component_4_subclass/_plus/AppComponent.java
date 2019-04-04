package com.example.hades.dagger2._4_organize_component._sub_component_4_subclass._plus;

import dagger.Component;

//@Singleton
//@FirstScope
//@Component(modules = {ComponentActivityModule.class})
@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(App app);

    App app();

    DebugAppComponent plus(DebugAppModule debugAppModule);
}