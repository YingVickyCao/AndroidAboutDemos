package com.example.hades.dagger2._4_organize_component._sub_component._plus;

import com.example.hades.dagger2._common._scope.ActivityScope;
import dagger.Module;
import dagger.Provides;

@Module
public class AModule {
    @ActivityScope
    @Provides
    public A provideA() {
        return new A();
    }
}