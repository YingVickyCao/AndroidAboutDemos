package com.example.hades.dagger2._7_lazy;

import dagger.Component;

@Component(modules = {AModule.class})
public interface TestLazyActivityComponent {
    void inject(TestLazyActivity activity);
}