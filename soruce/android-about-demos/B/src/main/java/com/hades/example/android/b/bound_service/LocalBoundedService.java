package com.hades.example.android.b.bound_service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.hades.example.android.b.LogHelper;

public class LocalBoundedService extends Service {
    private static final String TAG = LocalBoundedService.class.getSimpleName();
    private int mCount;
    private boolean mQuit;

    // 定义onBinder方法所返回的
    private LocalBinder binder = new LocalBinder();

    // 通过继承Binder来实现IBinder类
    class LocalBinder extends Binder {
        int getCount() {
            // 获取Service的运行状态：mCount
            return mCount;
        }
    }

    // Service被创建时回调该方法
    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate: " + LogHelper.getThreadInfo());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: " + LogHelper.getThreadInfo());
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onRebind(Intent intent) {
        Log.d(TAG, "onRebind: " + LogHelper.getThreadInfo());
        super.onRebind(intent);
    }

    // 必须实现的方法，绑定该Service时回调该方法
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: " + LogHelper.getThreadInfo());

        // 返回IBinder对象
        // 启动一条线程，动态地修改count状态值
        new Thread() {
            @Override
            public void run() {
                while (!mQuit) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                    }
                    mCount++;
                    Log.d(TAG, "run: count=" + mCount);
                }
            }
        }.start();
        return binder;
    }

    // Service被断开连接时回调该方法
    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind: " + LogHelper.getThreadInfo());
        return true;
    }

    // Service被关闭之前回调该方法
    @Override
    public void onDestroy() {
        this.mQuit = true;
        Log.d(TAG, "onDestroy: " + LogHelper.getThreadInfo());
    }
}