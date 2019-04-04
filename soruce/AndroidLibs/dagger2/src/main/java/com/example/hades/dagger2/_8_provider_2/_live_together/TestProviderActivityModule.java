package com.example.hades.dagger2._8_provider_2._live_together;

import dagger.Module1;

@Module1(injects = {TestProviderActivity.class}, includes = {SerializationModule.class})
public class TestProviderActivityModule {
}
