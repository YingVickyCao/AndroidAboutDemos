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

public class BoundedServiceTestActivity extends Activity {
    private static final String TAG = BoundedServiceTestActivity.class.getSimpleName();

    // 保持所启动的Service的IBinder对象
    BoundedService.MyBinder binder;

    // 定义一个ServiceConnection对象
    Intent intent;
    private ServiceConnection conn = new ServiceConnection() {
        // 当该Activity与Service连接成功时回调该方法
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "onServiceConnected: ");

            // 获取Service的onBind方法所返回的MyBinder对象
            binder = (BoundedService.MyBinder) service;  // ①
        }

        // 当该Activity与Service断开连接时回调该方法
        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "onServiceDisconnected: ");
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_bounded_service_test);

        intent = new Intent(this, BoundedService.class);
        findViewById(R.id.bind).setOnClickListener(v -> bindService());
        findViewById(R.id.unbind).setOnClickListener(v -> unbindService());
        findViewById(R.id.getServiceStatus).setOnClickListener(v -> getServiceStatus());
    }

    private void bindService() {
        Log.d(TAG, "bindService: ");
        bindService(intent, conn, Service.BIND_AUTO_CREATE);
    }

    private void unbindService() {
        Log.d(TAG, "unbindService: ");
        unbindService(conn);
    }

    private void getServiceStatus() {
        Log.d(TAG, "getServiceStatus: binder.getCount()=" + binder.getCount());
        Toast.makeText(BoundedServiceTestActivity.this, "Service的count值为：" + binder.getCount(), Toast.LENGTH_SHORT).show();  // ②
    }
}