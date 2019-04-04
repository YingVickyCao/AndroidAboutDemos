package com.example.hades.togetherdagger1anddagger2;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = Dagger2Module.class)
public interface Dagger2Component {
    void inject(MainActivity activity);
}
