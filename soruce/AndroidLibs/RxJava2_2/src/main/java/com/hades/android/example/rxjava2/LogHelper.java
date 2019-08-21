package com.hades.android.example.rxjava2;

public class LogHelper {
    public static String getThreadInfo() {
        return "thread :" + Thread.currentThread().getName() + "," + Thread.currentThread().getId();
    }
}
