package com.example.hades.dagger2._4_organize_component._dependencies;

import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(App app);

    /**
     * 提供A，当其他Component依赖AppComponent时，可以使用全局的A
     */
    A getA();
}