package com.example.hades.dagger1.plus;

import android.util.Log;

import javax.inject.Inject;

public class Two {
    private static final String TAG = Two.class.getSimpleName();

    /**
     * FIx_ERROR:error: No injectable members on com.example.hades.dagger1.plus.Two. Do you want to add an injectable constructor? required by com.example.hades.dagger1.MainActivity for com.example.hades.dagger1.MainActivityModule
     */
    @Inject
    public Two() {
    }

    public String hi() {
        Log.d(TAG, "hashCode=" + hashCode());
        return "Two";
    }
}