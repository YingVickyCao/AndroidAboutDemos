package com.hades.android.example.android_about_demos.app_component.service.startservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.hades.android.example.android_about_demos.mock.LogHelper;

public class FirstService extends Service {
    private static final String TAG = FirstService.class.getSimpleName();
    private int mNum = 0;
    //    private int MAX_NUM = 1000;
    private int MAX_NUM = 10;
    private boolean mIsForceStop;

    @Override
    public IBinder onBind(Intent arg0) {
        Log.d(TAG, "onBind: ");
        return null;
    }

    // Service被创建时回调该方法
    @Override
    public void onCreate() {
        super.onCreate();
//        Log.d(TAG, "onCreate");
        LogHelper.printThreadInfo(TAG, "onCreate");
    }

    // Service被启动时回调该方法
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");
//        LogHelper.printThreadInfo(TAG, "onStartCommand");
        return START_STICKY;
    }

//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
////        Log.d(TAG, "onStartCommand");
////        LogHelper.printThreadInfo(TAG, "onStartCommand");
//        int num = intent.getIntExtra(KEY_COUNT, 0);
//        Log.d(TAG, "onStartCommand," + num);
//        mNum = num;
//        mockHeavyWorkInThread();
//        return START_STICKY;
//    }

//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
////        Log.d(TAG, "onStartCommand");
//        LogHelper.printThreadInfo(TAG, "onStartCommand");
////        mockHeavyWorkInUIThread();
////        mockHeavyWorkInThread();
//        mockHeavyWorkInThread4CheckStop();
//        return START_STICKY;
//    }

    private void mockHeavyWorkInThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = mNum; i < MAX_NUM; i++) {
                    LogHelper.printThreadInfo(TAG, "mockHeavyWork", "i=" + i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                stopSelf();
            }
        }).start();
    }

    private void mockHeavyWorkInThread4CheckStop() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = mNum; i < MAX_NUM; i++) {
                    if (mIsForceStop) {
                        LogHelper.printThreadInfo(TAG, "mockHeavyWork,force stop", "i=" + i);
                        return;
                    }
                    LogHelper.printThreadInfo(TAG, "mockHeavyWork", "i=" + i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                stopSelf();
            }
        }).start();
    }

    private void mockHeavyWorkInUIThread() {
        for (int i = mNum; i < MAX_NUM; i++) {
            LogHelper.printThreadInfo(TAG, "mockHeavyWork", "i=" + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

//    // Service被关闭之前回调
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
////        Log.d(TAG, "onDestroy");
////        stopSelf();
//        LogHelper.printThreadInfo(TAG, "onDestroy");
//    }

    // Service被关闭之前回调
    @Override
    public void onDestroy() {
        super.onDestroy();
//        Log.d(TAG, "onDestroy");
//        mIsForceStop = true;
        LogHelper.printThreadInfo(TAG, "onDestroy");
    }
}