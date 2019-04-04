package com.example.hades.dagger2._10_subgraphs._subclass._inject_last_child;

import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {FActivityModule.class})
public interface FActivityComponent {
    void inject(FActivity activity);
}