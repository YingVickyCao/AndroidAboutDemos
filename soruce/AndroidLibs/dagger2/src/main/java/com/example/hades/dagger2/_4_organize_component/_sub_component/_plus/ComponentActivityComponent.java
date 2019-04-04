package com.example.hades.dagger2._4_organize_component._sub_component._plus;

import com.example.hades.dagger2._common._scope.ActivityScope;
import dagger.Component;

@ActivityScope
@Component(modules = {ComponentActivityModule.class})
public interface ComponentActivityComponent {
    void inject(ComponentActivity subComponentTestActivity);

    SubFragmentComponent plus(SubFragmentModule subFragmentModule);
}