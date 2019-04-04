package com.example.hades.dagger2._4_organize_component._sub_component_4_activity;

public class Father {
    private static final String TAG = Father.class.getSimpleName();

    public Father() {
    }

    public String info() {
        return TAG + "@" + hashCode();
    }
}