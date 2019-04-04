package com.example.hades.dagger2._10_subgraphs._subclass._inject_last_child;

import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {CActivityModule.class})
public interface CActivityComponent {
    void inject(CActivity activity);
}