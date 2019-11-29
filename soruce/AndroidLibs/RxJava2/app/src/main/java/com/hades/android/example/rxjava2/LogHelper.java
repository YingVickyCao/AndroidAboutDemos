package com.hades.android.example.rxjava2;

public class LogHelper {
    public static String getThreadInfo() {
        return "thread name=" + Thread.currentThread().getName() + ",thread id=" + Thread.currentThread().getId();
    }
}
