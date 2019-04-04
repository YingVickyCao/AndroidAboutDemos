package com.example.hades.dagger2._8_provider_2._live_together;

import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {TestProviderActivityModule4Dagger2.class})
public interface TestProviderActivityComponent4Dagger2 {
    void inject(TestProviderActivity activity);
}