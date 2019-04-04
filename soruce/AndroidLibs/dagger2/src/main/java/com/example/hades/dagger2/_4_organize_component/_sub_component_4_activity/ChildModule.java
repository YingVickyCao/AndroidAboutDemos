package com.example.hades.dagger2._4_organize_component._sub_component_4_activity;

import com.example.hades.dagger2._common._scope.ActivityScope;
import dagger.Module;
import dagger.Provides;

@Module
public class ChildModule {
    @ActivityScope
//    @Singleton
    @Provides
    public Child provideChild(Father father) {
        return new Child(father);
    }
}
