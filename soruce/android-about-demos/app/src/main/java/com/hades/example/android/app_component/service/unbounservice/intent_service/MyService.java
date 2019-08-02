package com.hades.example.android.app_component.service.unbounservice.intent_service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.hades.example.android.lib.utils.LogHelper;

public class MyService extends Service {
    private static final String TAG = MyService.class.getSimpleName();

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogHelper.printThread(TAG, "onStartCommand");
        /**
         * onStartCommand,thread =2,main
         */
        // 该方法内执行耗时任务可能导致ANR（Application Not Responding）异常  = 30s
        // 07-17 08:34:07.603 25578-25578/app D/MyService: onStartCommand,thread =2,main
        // 07-17 08:34:37.679 25578-25587/app I/zygote64: Thread[3,tid=25587,WaitingInMainSignalCatcherLoop,Thread*=0x7ae04c4400,peer=0x13780020,"Signal Catcher"]: reacting to signal 3
        // 07-17 08:34:37.794 25578-25587/app I/zygote64: Wrote stack traces to '/data/anr/traces.txt'
        long endTime = System.currentTimeMillis() + 50 * 1000;

        while (System.currentTimeMillis() < endTime) {
            synchronized (this) {
                try {
                    wait(endTime - System.currentTimeMillis());
                } catch (Exception e) {
                }
            }
        }
        Log.d(TAG, "onStartCommand: finish task");
        return START_STICKY;
    }
}