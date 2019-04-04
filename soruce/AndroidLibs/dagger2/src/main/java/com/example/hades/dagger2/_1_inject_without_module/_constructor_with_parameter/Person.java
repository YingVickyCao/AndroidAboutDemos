package com.example.hades.dagger2._1_inject_without_module._constructor_with_parameter;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class Person {
    private static final String TAG = Person.class.getSimpleName();

    private Info mInfo;

    @Inject
    public Person(Info info) {
        mInfo = info;
    }

    public String info() {
        return TAG + "@" + hashCode() + "," + mInfo.info();
    }
}
