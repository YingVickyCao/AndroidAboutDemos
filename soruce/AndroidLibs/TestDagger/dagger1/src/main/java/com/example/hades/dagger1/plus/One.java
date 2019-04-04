package com.example.hades.dagger1.plus;

import android.util.Log;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class One {
    private static final String TAG = One.class.getSimpleName();

    @Inject
    public One() {
    }

    public String hi() {
        Log.d(TAG, "One hashCode=" + hashCode());
        return "One";
    }
}