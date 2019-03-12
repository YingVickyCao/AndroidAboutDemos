package com.hades.example.android.app_component.service.boundservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.hades.example.android.lib.utils.LogHelper;

public class LocalBoundedService extends Service {
    private static final String TAG = LocalBoundedService.class.getSimpleName();
    private int mCount;
    private boolean mQuit;
    private boolean mIsBounded = false;

    // 定义onBinder方法所返回的
    private MyBinder binder = new MyBinder();

    // 通过继承Binder来实现IBinder类
    public class MyBinder extends Binder {
        public int getCount() {
            // 获取Service的运行状态：mCount
            return mCount;
        }

        public boolean isBounded() {
            return mIsBounded;
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
        mIsBounded = true;
        super.onRebind(intent);
    }

    // 必须实现的方法，绑定该Service时回调该方法
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: " + LogHelper.getThreadInfo());
        mIsBounded = true;

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
                }
            }
        }.start();
        return binder;
    }

    // Service被断开连接时回调该方法
    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind: " + LogHelper.getThreadInfo());
        mIsBounded = false;
        return true;
    }

    // Service被关闭之前回调该方法
    @Override
    public void onDestroy() {
        this.mQuit = true;
        Log.d(TAG, "onDestroy: " + LogHelper.getThreadInfo());
    }
}