package com.example.hades.dagger2._4_organize_component._sub_component_4_activity;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * 使用subcomponents表示全部开放依赖给@SubComponent
 */
@Module(subcomponents = ChildComponent.class)
public class FatherModule {
    @Singleton
    @Provides
    public Father provideFather() {
        return new Father();
    }
}
