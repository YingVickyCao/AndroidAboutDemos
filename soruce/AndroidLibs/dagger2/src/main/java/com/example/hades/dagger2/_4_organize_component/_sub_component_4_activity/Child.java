package com.example.hades.dagger2._4_organize_component._sub_component_4_activity;

public class Child {
    private static final String TAG = Child.class.getSimpleName();
    Father mFather;

    public Child(Father father) {
        mFather = father;
    }

    public String info() {
        return TAG + "@" + hashCode() + "," + mFather.info();
    }
}
