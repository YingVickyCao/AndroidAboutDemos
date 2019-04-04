package com.example.hades.dagger2._2_inject_with_module._constructor_without_parameter;

import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {AActivityModule.class})
public interface AActivityComponent {
    void inject(AActivity activity);
}