package com.example.hades.dagger2._1_inject_without_module._constructor_with_parameter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component
public interface PersonActivityComponent {
    void inject(PersonActivity personActivity);
}