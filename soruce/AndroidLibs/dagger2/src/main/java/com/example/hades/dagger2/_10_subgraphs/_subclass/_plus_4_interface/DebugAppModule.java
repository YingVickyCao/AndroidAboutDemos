package com.example.hades.dagger2._10_subgraphs._subclass._plus_4_interface;

import com.example.hades.dagger2.Log;
import dagger.Module;

@Module(includes = {BModule.class})
public class DebugAppModule {
    private static final String TAG = DebugAppModule.class.getSimpleName();

    public DebugAppModule() {
        Log.d(TAG, "DebugAppModule()@" + hashCode());
    }
}