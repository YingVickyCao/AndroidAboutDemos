package com.hades.example.android.app_component.service.system.vibrator;

import android.Manifest;
import android.app.Service;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.MotionEvent;
import android.widget.Toast;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.PermissionActivity;


/*
 <!-- 授予程序访问振动器的权限 -->
 <uses-permission android:name="android.permission.VIBRATE"/>
 */
public class VibratorActivityWithFragmentActivity extends PermissionActivity {
    Vibrator vibrator;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_system_vibrator_service);

        initViews();

        // 获取系统的Vibrator服务
        vibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
    }

    @Override
    protected void requestPermission() {
        super.requestPermission();
        checkPermission("Request VIBRATE permission", Manifest.permission.VIBRATE);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Toast.makeText(this, "手机振动", Toast.LENGTH_SHORT).show();
        vibrator.vibrate(500);
        return super.onTouchEvent(event);
    }
}