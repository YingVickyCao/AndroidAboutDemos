package com.example.hades.dagger2._2_inject_with_module._constructor_with_parameter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {PersonActivityModule.class})
public interface PersonActivityComponent {
    void inject(PersonActivity personActivity);
}