package com.hades.example.android.b;

import android.util.Log;

public class LogHelper {
    public static void printThreadInfo(final String TAG, final String funcInfo, final String msg) {
        Log.d(TAG, funcInfo + ",[thread =" + Thread.currentThread().getId() + "," + Thread.currentThread().getName() + "]," + msg);
    }

    public static void printThreadInfo(final String TAG, final String funcInfo) {
        Log.d(TAG, funcInfo + ",thread =" + Thread.currentThread().getId() + "," + Thread.currentThread().getName());
    }

    public static String getThreadInfo() {
        return "[thread =" + Thread.currentThread().getId() + "," + Thread.currentThread().getName() + "]";
    }

}