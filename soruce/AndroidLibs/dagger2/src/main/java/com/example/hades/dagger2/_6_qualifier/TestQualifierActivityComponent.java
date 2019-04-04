package com.example.hades.dagger2._6_qualifier;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {PersonModule.class, InfoModule.class})
public interface TestQualifierActivityComponent {
    void inject(TestQualifierActivity activity);
}