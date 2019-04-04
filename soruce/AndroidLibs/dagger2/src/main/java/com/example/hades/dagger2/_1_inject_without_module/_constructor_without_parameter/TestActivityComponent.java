package com.example.hades.dagger2._1_inject_without_module._constructor_without_parameter;

import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component
public interface TestActivityComponent {
    void inject(TestActivity activity);
}
