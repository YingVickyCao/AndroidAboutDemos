package com.example.hades.dagger2._9_add_multi_component_in_one_component._1;

import dagger.Component;

@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(App app);
}