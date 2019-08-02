package com.hades.example.android.app_component.service.unbounservice.intent_service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;

import com.hades.example.android.lib.utils.LogHelper;

public class MyIntentService extends IntentService {
    private static final String TAG = MyIntentService.class.getSimpleName();

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        LogHelper.printThread(TAG, "onStartCommand,intent=" + intent + ",flags=" + flags + ",startId=" + startId);
        return super.onStartCommand(intent, flags, startId);
    }

    // IntentService会使用单独的线程来执行该方法的代码
    @Override
    protected void onHandleIntent(Intent intent) {
        /**
         * onHandleIntent,thread =29058,IntentService[MyIntentService]
         */
        LogHelper.printThread(TAG, "onHandleIntent");

        // 该方法内可以执行任何耗时任务，比如下载文件等，此处只是让线程暂停50秒
//        long endTime = System.currentTimeMillis() + 50 * 1000;
        long endTime = System.currentTimeMillis() + 5 * 1000;
        while (System.currentTimeMillis() < endTime) {
            synchronized (this) {
                try {
                    wait(endTime - System.currentTimeMillis());
                } catch (Exception e) {
                }
            }
        }
        Log.d(TAG, "onHandleIntent: finish task");
    }

    @Override
    public boolean stopService(Intent name) {
        Log.d(TAG, "stopService: ");
        return super.stopService(name);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: ");
    }
}