package com.example.hades.dagger2._5_singleton._jvm;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {WorldModule.class})
public abstract class WorldComponent {
    public abstract void inject(TestSingletonActivity activity);

    private static WorldComponent mInstance;

    public static WorldComponent getInstance() {
        if (mInstance == null) {
            mInstance = DaggerWorldComponent.builder().build();
        }

        return mInstance;
    }
}