package com.example.hades.dagger2._10_subgraphs._subclass._plus_4_interface_2;

import com.example.hades.dagger2.Log;

public class AInChild implements IA {
    private static final String TAG = AInChild.class.getSimpleName();

    public AInChild() {
        Log.d(TAG, TAG + "()@" + hashCode());

    }

    public String info() {
        return TAG + "@" + hashCode();
    }
}