package com.hades.android.example.android_about_demos.app_component.service.startservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class FirstService extends Service {
    private static final String TAG = FirstService.class.getSimpleName();

    @Override
    public IBinder onBind(Intent arg0) {
        Log.d(TAG, "onBind: ");
        return null;
    }

    // Service被创建时回调该方法
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");

    }

    // Service被启动时回调该方法
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");
        return START_STICKY;
    }

    // Service被关闭之前回调
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }
}