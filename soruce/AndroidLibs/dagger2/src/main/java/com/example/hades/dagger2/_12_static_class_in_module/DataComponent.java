package com.example.hades.dagger2._12_static_class_in_module;

import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {NumModule.class})
//@Component(modules = {DataModule.class})
public interface DataComponent {
    void inject(MainActivity activity);
}