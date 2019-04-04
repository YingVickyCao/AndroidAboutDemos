package com.example.hades.dagger2._2_inject_with_module._constructor_with_parameter;

public class Person {
    private static final String TAG = Person.class.getSimpleName();
    private Info mInfo;

    public Person(Info info) {
        mInfo = info;
    }

    public String printInfo() {
        return TAG + "@" + hashCode() + "," + mInfo.info();
    }
}
