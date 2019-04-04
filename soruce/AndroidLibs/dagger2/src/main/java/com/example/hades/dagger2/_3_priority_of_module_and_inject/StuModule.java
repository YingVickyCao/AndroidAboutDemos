package com.example.hades.dagger2._3_priority_of_module_and_inject;

import dagger.Module;
import dagger.Provides;

@Module
public class StuModule {
    @Provides
    public Stu provideStu() {
        return new Stu("provided by @Provides");
    }
}
