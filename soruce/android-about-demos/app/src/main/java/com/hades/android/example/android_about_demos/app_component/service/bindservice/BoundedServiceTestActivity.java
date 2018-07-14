package com.hades.android.example.android_about_demos.app_component.service.bindservice;


import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.hades.android.example.android_about_demos.R;
import com.hades.android.example.android_about_demos.mock.LogHelper;

public class BoundedServiceTestActivity extends Activity {
    private static final String TAG = BoundedServiceTestActivity.class.getSimpleName();

    // 保持所启动的Service的IBinder对象
    BoundedService.MyBinder mBinder;
    // 定义一个ServiceConnection对象
    private ServiceConnection mConn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_bounded_service_test);

        initServiceConnection();

        findViewById(R.id.bind).setOnClickListener(v -> bindService());
        findViewById(R.id.unbind).setOnClickListener(v -> unbindService());
        findViewById(R.id.getServiceStatus).setOnClickListener(v -> getServiceStatus());

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
                mBinder = (BoundedService.MyBinder) service;  // ①
            }

            // 当Service所在当宿主进程由于异常终止或者其他原因终止，导致该Service与访问者之间断开连接时，回调该方法
            // 当调用者主动使用unbindService()时，不回调该方法。
            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.d(TAG, "onServiceDisconnected: ");
            }
        };
    }

    private void bindService() {
//        Log.d(TAG, "bindService: ");
        bindServiceWithAutoCreate();
//        bindServiceWithAutoCreateInThread();
//        bindServiceWithNoAutoCreate();
    }

    private void bindServiceWithAutoCreate() {
        Log.d(TAG, "bindService: ");
        Intent intent = new Intent(this, BoundedService.class);
        // Flags:指定绑定时如果Service仍未创建时是否自动创建Service。
        // auto create service
        bindService(intent, mConn, Service.BIND_AUTO_CREATE);
    }

    private void bindServiceWithAutoCreateInThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                LogHelper.printThreadInfo(TAG, "bindServiceWithAutoCreateInThread");
                Intent intent = new Intent(BoundedServiceTestActivity.this, BoundedService.class);
                // Flags:指定绑定时如果Service仍未创建时是否自动创建Service。
                // auto create service
                bindService(intent, mConn, Service.BIND_AUTO_CREATE);
            }
        }).start();
    }

    private void bindServiceWithNoAutoCreate() {
        Log.d(TAG, "bindService: ");
        Intent intent = new Intent(this, BoundedService.class);

        // Flags:指定绑定时如果Service仍未创建时是否自动创建Service。
        // 0 = 不自动创建，
        bindService(intent, mConn, 0);
    }

    private void unbindService() {
        Log.d(TAG, "unbindService: ");
        unbindService(mConn);
    }

    private void getServiceStatus() {
        Log.d(TAG, "getServiceStatus: mBinder.getCount()=" + mBinder.getCount());
        Toast.makeText(BoundedServiceTestActivity.this, "Service的count值为：" + mBinder.getCount(), Toast.LENGTH_SHORT).show();  // ②
    }

    private void startService() {
        Log.d(TAG, "startService: ");
        startService(new Intent(this, BoundedService.class));
    }

    private void stopService() {
        Log.d(TAG, "stopService: ");
        stopService(new Intent(this, BoundedService.class));
    }
}