package com.example.hades.dagger2._2_inject_with_module._constructor_with_parameter;


public class Info {
    private static final String TAG = Info.class.getSimpleName();

    public Info() {
    }

    public String info() {
        return TAG + "@" + hashCode();
    }
}
