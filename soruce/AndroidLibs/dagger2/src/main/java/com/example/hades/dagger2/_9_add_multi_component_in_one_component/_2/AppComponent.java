package com.example.hades.dagger2._9_add_multi_component_in_one_component._2;

import dagger.Component;

@Component(modules = {AModule.class, BModule.class})
public interface AppComponent {
    void inject(App app);
}