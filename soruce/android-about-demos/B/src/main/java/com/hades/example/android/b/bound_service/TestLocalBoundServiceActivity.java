package com.hades.example.android.b.bound_service;


import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.hades.example.android.b.LogHelper;
import com.hades.example.android.b.R;

public class TestLocalBoundServiceActivity extends Activity {
    private static final String TAG = TestLocalBoundServiceActivity.class.getSimpleName();

    // 保持所启动的Service的IBinder对象
    LocalBoundedService.LocalBinder mBinder;
    // 定义一个ServiceConnection对象
    private ServiceConnection mConn;
    boolean mIsBounded = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_bounded_service_test);

        ((TextView)findViewById(R.id.topic)).setText("Remote BoundService");

        initServiceConnection();

        findViewById(R.id.bind).setOnClickListener(v -> bindService());
        findViewById(R.id.bindAutoCreate).setOnClickListener(v -> bindAutoCreate());
        findViewById(R.id.bindAutoCreateInThread).setOnClickListener(v -> bindAutoCreateInThread());
        findViewById(R.id.unbind).setOnClickListener(v -> unbindService());
        findViewById(R.id.check).setOnClickListener(v -> check());

        findViewById(R.id.start).setOnClickListener(v -> startService());
        findViewById(R.id.stop).setOnClickListener(v -> stopService());
    }

    private void initServiceConnection() {
        /**
         * ServiceConnection 用于监听访问者与Service之间的连接情况
         */
        mConn = new ServiceConnection() {
            // 当该Activity与Service连接成功时回调该方法
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.d(TAG, "onServiceConnected: ");
                // 获取Service的onBind()方法所返回的IBinder - MyBinder对象 ,访问者通过IBinder与Service进行通信。
                mBinder = (LocalBoundedService.LocalBinder) service;  // ①
                mIsBounded = true;
            }

            // 当Service所在当宿主进程由于异常终止或者其他原因终止，导致该Service与访问者之间断开连接时，回调该方法
            // 当调用者主动使用unbindService()时，不回调该方法。
            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.d(TAG, "onServiceDisconnected: ");
                mBinder = null;
                mIsBounded = false;
            }
        };
    }

    private void bindService() {
        Log.d(TAG, "bindService: ");
        Intent intent = new Intent(this, LocalBoundedService.class);
        bindService(intent, mConn, 0);
    }

    private void bindAutoCreate() {
        Log.d(TAG, "bindService: ");
        Intent intent = new Intent(this, LocalBoundedService.class);
        bindService(intent, mConn, Service.BIND_AUTO_CREATE);
    }

    private void bindAutoCreateInThread() {
        new Thread(() -> {
            Log.d(TAG, "bindAutoCreateInThread->run: " + LogHelper.getThreadInfo());
            Intent intent = new Intent(TestLocalBoundServiceActivity.this, LocalBoundedService.class);
            bindService(intent, mConn, Service.BIND_AUTO_CREATE);
        }).start();
    }

    private void unbindService() {
        if (null == mBinder || !mIsBounded) {
            return;
        }
        Log.d(TAG, "unbindService: ");
        unbindService(mConn);
        mIsBounded = false;
    }

    private void startService() {
        Log.d(TAG, "startService: ");
        startService(new Intent(this, LocalBoundedService.class));
    }

    private void stopService() {
        Log.d(TAG, "stopService: ");
        stopService(new Intent(this, LocalBoundedService.class));
    }

    private void check() {
        Log.d(TAG, "check: mBinder.getCount()=" + mBinder.getCount());
        Toast.makeText(TestLocalBoundServiceActivity.this, "Service的count值为：" + mBinder.getCount(), Toast.LENGTH_SHORT).show();  // ②
    }
}