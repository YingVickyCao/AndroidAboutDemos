package com.example.hades.dagger2._12_static_class_in_module_2._live_together;

import dagger.Module1;

@Module1(injects = {MainActivity.class}, includes = {DataModule.class}, complete = false, library = true)
public class MainActivityModule {
}