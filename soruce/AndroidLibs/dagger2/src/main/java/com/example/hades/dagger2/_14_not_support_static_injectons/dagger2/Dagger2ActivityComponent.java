package com.example.hades.dagger2._14_not_support_static_injectons.dagger2;

import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {Dagger2ActivityModule.class})
public interface Dagger2ActivityComponent {
    void inject(Dagger2Activity activity);
}