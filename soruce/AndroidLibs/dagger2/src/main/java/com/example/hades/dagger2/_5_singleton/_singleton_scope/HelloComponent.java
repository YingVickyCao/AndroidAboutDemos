package com.example.hades.dagger2._5_singleton._singleton_scope;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {HelloModule.class})
public interface HelloComponent {
    void inject(TestSingletonActivity activity);
}