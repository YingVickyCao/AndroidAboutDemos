package com.example.hades.dagger2._4_organize_component._sub_component._plus;

import com.example.hades.dagger2.Log;
import com.example.hades.dagger2._common._scope.FragmentScope;
import dagger.Module;
import dagger.Provides;

//@Module(includes = {DataModule.class})
@Module
public class BModule {
    private static final String TAG = BModule.class.getSimpleName();

//    @Singleton // ERROR:
    @FragmentScope
    @Provides
    public B provideB(A a) {
        Log.d(TAG, "DataModule@" + hashCode());
        return new B(a);
    }
}