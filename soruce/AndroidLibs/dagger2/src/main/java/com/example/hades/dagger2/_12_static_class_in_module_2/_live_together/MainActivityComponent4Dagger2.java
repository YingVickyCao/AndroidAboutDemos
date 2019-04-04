package com.example.hades.dagger2._12_static_class_in_module_2._live_together;

import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {MainActivityModule4Dagger2.class})
public interface MainActivityComponent4Dagger2 {
    void inject(MainActivity activity);
}
