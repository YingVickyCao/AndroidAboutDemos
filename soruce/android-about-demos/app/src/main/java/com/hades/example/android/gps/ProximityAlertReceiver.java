package com.hades.example.android.gps;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.widget.Toast;

public class ProximityAlertReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        boolean isEnter = intent.getBooleanExtra(LocationManager.KEY_PROXIMITY_ENTERING, false); // 获取是否为进入指定区域
        if (isEnter) {
            Toast.makeText(context, "您已经进入疯狂软件教育中心", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, "您已经离开疯狂软件教育中心", Toast.LENGTH_LONG).show();
        }
    }
}
