package com.example.hades.dagger2._4_organize_component._extends;

import com.example.hades.dagger2.Log;
import com.example.hades.dagger2._common._scope.ActivityScope;
import dagger.Module;
import dagger.Provides;

@Module
public class BModule {
    private static final String TAG = BModule.class.getSimpleName();

    @ActivityScope
//    @Singleton
    @Provides
    public B provideB(A a) {
        Log.d(TAG, "DataModule@" + hashCode());
        return new B(a);
    }
}
