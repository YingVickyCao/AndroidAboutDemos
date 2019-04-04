package com.example.hades.dagger2._9_add_multi_component_in_one_component._2;

public class B {
    private static final String TAG = B.class.getSimpleName();

    public B() {
    }


    public String info() {
        return TAG + "@" + hashCode();
    }
}
