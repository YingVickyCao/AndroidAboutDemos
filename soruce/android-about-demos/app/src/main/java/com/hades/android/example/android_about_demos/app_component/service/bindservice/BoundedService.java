package com.hades.android.example.android_about_demos.app_component.service.bindservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.hades.android.example.android_about_demos.mock.LogHelper;

public class BoundedService extends Service {
    private static final String TAG = BoundedService.class.getSimpleName();
    private int mCount;
    private boolean mQuit;
    private boolean mIsBounded = false;

    // 定义onBinder方法所返回的对象
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
//        Log.d(TAG, "onCreate: ");
        LogHelper.printThreadInfo(TAG, "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: ");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onRebind(Intent intent) {
        Log.d(TAG, "onRebind: ");
//        LogHelper.printThreadInfo(TAG, "onRebind");
        mIsBounded = true;
        super.onRebind(intent);
    }

    // 必须实现的方法，绑定该Service时回调该方法
    @Override
    public IBinder onBind(Intent intent) {
//        Log.d(TAG, "onBind: ");
        LogHelper.printThreadInfo(TAG, "onBind");
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
//        Log.d(TAG, "onUnbind: ");
        LogHelper.printThreadInfo(TAG, "onUnbind");
        mIsBounded = false;
        return true;
    }

    // Service被关闭之前回调该方法
    @Override
    public void onDestroy() {
        this.mQuit = true;
//        Log.d(TAG, "onDestroy: ");
        LogHelper.printThreadInfo(TAG, "onDestroy");
    }
}