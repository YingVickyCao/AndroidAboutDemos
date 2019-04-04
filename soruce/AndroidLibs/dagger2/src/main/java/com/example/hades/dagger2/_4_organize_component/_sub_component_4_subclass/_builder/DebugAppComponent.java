package com.example.hades.dagger2._4_organize_component._sub_component_4_subclass._builder;

import dagger.Subcomponent;

/*
 *   AppComponent = @Singleton
 *
 *   Error:(11, 8) java: [Dagger/IncompatiblyScopedBindings] DebugAppComponent (unscoped) may not reference scoped bindings:
 *   @Provides @Singleton DataModule.provideA()
 *   component path: AppComponent → DebugAppComponent
 *
 */

/*
 *
 * AppComponent = @Singleton
 Error:(11, 8) java:  DebugAppComponent has conflicting scopes: AppComponent also has @Singleton
 *
 */


/*
 AppComponent = @FirstScope
 DebugAppComponent = @Singleton
 Error:(11, 8) java: [Dagger/IncompatiblyScopedBindings] DebugAppComponent scoped with @Singleton may not reference bindings with different scopes:
 @Provides @FirstScope @A DataModule.provideA()
 component path: AppComponent → DebugAppComponent
 */

/**
 * <pre>
 * </pre>
 */
//@Singleton
@Subcomponent(modules = {DebugAppModule.class})
public interface DebugAppComponent {
    void inject(DebugApp app);

    @Subcomponent.Builder
    interface Builder {
        DebugAppComponent build();

        Builder debugAppModule(DebugAppModule module);
    }
}