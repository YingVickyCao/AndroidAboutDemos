package com.example.hades.dagger2._11_global_singleton;

public class NetworkChannel {
    private static final String TAG = NetworkChannel.class.getSimpleName();

    public void info() {
        System.out.println(TAG + "@" + hashCode());
    }
}
