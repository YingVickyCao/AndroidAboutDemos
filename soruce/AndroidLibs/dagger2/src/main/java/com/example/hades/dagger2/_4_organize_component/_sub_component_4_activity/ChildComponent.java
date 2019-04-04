package com.example.hades.dagger2._4_organize_component._sub_component_4_activity;

import com.example.hades.dagger2._common._scope.ActivityScope;
import dagger.Subcomponent;

//@Singleton
@ActivityScope
@Subcomponent(modules = {ChildModule.class})
public interface ChildComponent {
    void inject(ChildActivity activity);

    @Subcomponent.Builder
    interface Builder {
        ChildComponent build();

        Builder childModule(ChildModule module);
    }
}