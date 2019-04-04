package com.example.hades.dagger2._4_organize_component._sub_component_4_subclass._plus;

import dagger.Subcomponent;

/**
 * <pre>
 * AppComponent = @Singleton
 *
 * Error:(9, 8) java: [Dagger/IncompatiblyScopedBindings] DebugAppComponent (unscoped) may not reference scoped bindings:
 * @Provides @Singleton DataModule.provideA()
 * component path: AppComponent → DebugAppComponent
 * <pre/>
 */

/**
 * <pre>
 * AppComponent = @FirstScope
 * DebugAppComponent = @Singleton
 *
 * Error:(8, 8) java: [Dagger/IncompatiblyScopedBindings] DebugAppComponent scoped with @Singleton may not reference bindings with different scopes:
 * @Provides @FirstScope DataModule.provideA()
 * component path: AppComponent → DebugAppComponent
 *
 * <pre/>
 */
//@Singleton
@Subcomponent(modules = {DebugAppModule.class})
public interface DebugAppComponent {
    void inject(DebugApp app);
}