package com.example.hades.dagger2._4_organize_component._sub_component._builder;

import com.example.hades.dagger2.Log;
import dagger.Module;

// // Adding subcomponents or not is same
@Module(includes = {AModule.class}, subcomponents = {SubFragmentComponent.class})
public class ComponentActivityModule {
    private static final String TAG = ComponentActivityModule.class.getSimpleName();

    public ComponentActivityModule() {
        Log.d(TAG, "ComponentActivityModule@" + hashCode());
    }
}