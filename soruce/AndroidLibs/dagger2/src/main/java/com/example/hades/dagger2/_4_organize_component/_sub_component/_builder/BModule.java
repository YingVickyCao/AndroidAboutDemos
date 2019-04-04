package com.example.hades.dagger2._4_organize_component._sub_component._builder;

import com.example.hades.dagger2.Log;
import com.example.hades.dagger2._common._scope.FragmentScope;
import dagger.Module;
import dagger.Provides;

//@Module(includes = {DataModule.class})
/**
 * Error:(8, 8) java: [Dagger/IncompatiblyScopedBindings] SubFragmentComponent scoped with @FragmentScope may not reference bindings with different scopes:
 *
 * @com.example.hades.dagger2.scope.ActivityScope @Provides A DataModule.provideA()
 * <p>
 * component path: ComponentActivityComponent → SubFragmentComponent
 */
@Module
public class BModule {
    private static final String TAG = BModule.class.getSimpleName();

    @FragmentScope
    @Provides
    public B provideB(A a) {
        Log.d(TAG, "DataModule@" + hashCode());
        return new B(a);
    }
}