package com.example.hades.dagger2._8_provider;

import dagger.Component;

@Component(modules = {AModule.class})
public interface TestProviderActivityComponent {
    void inject(TestProviderActivity activity);
}