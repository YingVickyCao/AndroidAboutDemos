package com.hades.android.example.android_about_demos.mock;

import android.util.Log;

public class LogHelper {
    public static void logThreadInfo(final String TAG, final String funcInfo, final String msg) {
        Log.d(TAG, funcInfo + ",msg=" + msg + ",thread =" + Thread.currentThread().getId() + "," + Thread.currentThread().getName());
    }

    public static void logThreadInfo(final String TAG, final String funcInfo) {
        Log.d(TAG, funcInfo + ",thread =" + Thread.currentThread().getId() + "," + Thread.currentThread().getName());
    }
}
