package com.example.hades.dagger2._4_organize_component._dependencies;

import com.example.hades.dagger2._common._scope.ActivityScope;
import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = BModule.class)
public interface TestDependenceActivityComponent {
    void inject(TestDependenceActivity TestDependenceActivity);
}