package com.example.hades.dagger2._6_qualifier;

public class Info {
    private static final String TAG = Info.class.getSimpleName();
    private Person mPerson;

    public Info(Person person) {
        mPerson = person;
    }

    public String info() {
        return TAG + "@" + hashCode() + "," + mPerson.info();
    }
}