package com.hades.android.example.android_about_demos.app_component.service.bindservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class BoundedService extends Service {
    private static final String TAG = BoundedService.class.getSimpleName();
    private int mCount;
    private boolean mQuit;

    // 定义onBinder方法所返回的对象
    private MyBinder binder = new MyBinder();

    // 通过继承Binder来实现IBinder类
    public class MyBinder extends Binder {
        public int getCount() {
            // 获取Service的运行状态：mCount
            return mCount;
        }
    }

    // Service被创建时回调该方法
    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate: ");

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
    }

    // 必须实现的方法，绑定该Service时回调该方法
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: ");
        // 返回IBinder对象
        return binder;
    }

    // Service被断开连接时回调该方法
    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind: ");
        return true;
    }

    // Service被关闭之前回调该方法
    @Override
    public void onDestroy() {
        this.mQuit = true;
        Log.d(TAG, "onDestroy: ");
    }
}