package com.example.hades.dagger2._4_organize_component._sub_component_4_activity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {FatherModule.class})
public interface FatherComponent {
    void inject(FatherActivity activity);

    /**
     * 让父类能够生成@SubComponent 类的方法
     */
    ChildComponent.Builder buildChildComponent();
}
