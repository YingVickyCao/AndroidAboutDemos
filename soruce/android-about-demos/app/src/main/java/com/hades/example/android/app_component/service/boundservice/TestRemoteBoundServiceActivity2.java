package com.hades.example.android.app_component.service.boundservice;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.TextView;

import com.hades.example.android.R;
import com.hades.example.android.lib.utils.LogHelper;

public class TestRemoteBoundServiceActivity2 extends Activity {
    private static final String TAG = TestRemoteBoundServiceActivity2.class.getSimpleName();

    // 保持所启动的Service的IBinder对象
    IBinder mBinder;
    // 定义一个ServiceConnection对象
    private ServiceConnection mConnection;
    boolean bound;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_bounded_service_test);

        ((TextView)findViewById(R.id.topic)).setText("Remote BoundService");

        setServiceConnection();

        findViewById(R.id.bind).setOnClickListener(v -> bindService());
        findViewById(R.id.bindAutoCreate).setOnClickListener(v -> bindAutoCreate());
        findViewById(R.id.bindAutoCreateInThread).setOnClickListener(v -> bindAutoCreateInThread());
        findViewById(R.id.unbind).setOnClickListener(v -> unbindService());
//        findViewById(R.id.getServiceStatus).setOnClickListener(v -> getServiceStatus());

        findViewById(R.id.start).setOnClickListener(v -> startService());
        findViewById(R.id.stopRecord).setOnClickListener(v -> stopService());
    }

    private Intent buildIntent() {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.hades.example.android.b", "com.hades.example.android.b.bound_service.LocalBoundedService"));
        intent.setAction("com.hades.example.android.b.bound_service.LocalBoundedService");
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        return intent;
    }

    private void bindService() {
        Log.d(TAG, "bindService: ");
        Intent intent = buildIntent();
        bindService(intent, mConnection, 0);
    }

    private void bindAutoCreate() {
        Log.d(TAG, "bindService: ");
        Intent intent = buildIntent();
        bindService(intent, mConnection, Service.BIND_AUTO_CREATE);
    }

    private void bindAutoCreateInThread() {
        new Thread(() -> {
            Log.d(TAG, "bindAutoCreateInThread->run: " + LogHelper.getThreadInfo());
            Intent intent = buildIntent();
            bindService(intent, mConnection, Service.BIND_AUTO_CREATE);
        }).start();
    }

    private void unbindService() {
        if (null == mBinder || !bound) {
            return;
        }
        Log.d(TAG, "unbindService: ");
        unbindService(mConnection);
    }

    private void startService() {
        Log.d(TAG, "startService: ");
        Intent intent = buildIntent();
        startService(intent);
    }

    private void stopService() {
        Log.d(TAG, "stopService: ");
        Intent intent = buildIntent();
        stopService(intent);
    }

    private void setServiceConnection() {
        /**
         * ServiceConnection 用于监听访问者与Service之间的连接情况
         */
        mConnection = new ServiceConnection() {
            // 当该Activity与Service连接成功时回调该方法
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.d(TAG, "onServiceConnected: ");
                // 获取Service的onBind()方法所返回的IBinder - MyBinder对象 ,访问者通过IBinder与Service进行通信。
                mBinder = service;  // ①
                bound = true;
            }

            // 当Service所在当宿主进程由于异常终止或者其他原因终止，导致该Service与访问者之间断开连接时，回调该方法
            // 当调用者主动使用unbindService()时，不回调该方法。
            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.d(TAG, "onServiceDisconnected: ");
                bound = false;
            }
        };
    }

}