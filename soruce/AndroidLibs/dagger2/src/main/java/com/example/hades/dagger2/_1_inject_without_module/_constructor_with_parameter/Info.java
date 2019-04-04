package com.example.hades.dagger2._1_inject_without_module._constructor_with_parameter;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class Info {
    private static final String TAG = Info.class.getSimpleName();

    @Inject
    public Info() {
    }

    public String info() {
        return TAG + "@" + hashCode();
    }
}