package com.example.hades.dagger2._10_subgraphs._subclass._inject_parent;

import dagger.Component;

@Component(modules = {FActivityModule.class})
public interface FActivityComponent {
    void inject(FActivity activity);
}