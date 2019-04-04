package com.example.hades.dagger2._10_subgraphs._subclass._inject_last_child;

import com.example.hades.dagger2.Log;
import dagger.Module;

@Module(includes = {BModule.class, CModule.class})
public class CActivityModule extends FActivityModule {
//public class CActivityModule  {

    public CActivityModule() {
        Log.d(this.getClass().getSimpleName(), this.getClass().getSimpleName() + "@" + hashCode());

    }
}