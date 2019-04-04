package com.example.hades.dagger2._4_organize_component._sub_component._builder;

import com.example.hades.dagger2._common._scope.FragmentScope;
import dagger.Subcomponent;

@FragmentScope
@Subcomponent(modules = {SubFragmentModule.class})
public interface SubFragmentComponent {
    void inject(SubFragment fragment);

    @Subcomponent.Builder
    interface Builder {
        SubFragmentComponent build();

        Builder subFragmentModule(SubFragmentModule subFragmentModule);
    }
}