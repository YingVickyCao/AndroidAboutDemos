package com.example.hades.dagger2._9_add_multi_component_in_one_component._1;

import dagger.Module;

@Module(includes = {AModule.class, BModule.class})
public class AppModule {
}