package com.example.hades.dagger2._4_organize_component._extends;


import com.example.hades.dagger2._common._scope.ActivityScope;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@ActivityScope
@Component(modules = {BModule.class, AppModule.class})
public interface TestExtendComponent extends AppComponent {
    void inject(TestExtendActivity extendTestActivity);
}