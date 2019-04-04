package com.example.hades.dagger2._4_organize_component._sub_component._plus;

import com.example.hades.dagger2.Log;
import dagger.Module;

@Module(includes = {AModule.class})
public class ComponentActivityModule {
    private static final String TAG = ComponentActivityModule.class.getSimpleName();

    public ComponentActivityModule() {
        Log.d(TAG, "ComponentActivityModule@" + hashCode());
    }
}
