package com.hades.example.android.gps;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.RxPermissionsActivity;

import java.util.List;

public class TestGpsActivity extends RxPermissionsActivity {
    LocationManager lm;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gps);

        initViews();

        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE); // 获取系统的LocationManager对象

        listAllLocationProviders();
        listAllFreeLocationProviders();
        gpsLocationInfo();
        proximityAlert();
    }

    @Override
    protected void requestPermission() {
        checkPermission("Request permission for GPS", Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION);
    }

    private void listAllLocationProviders() {
        /**
         * Samsung SM-G9730
         * passive, gps, network
         */
        List<String> providerNames = lm.getAllProviders();

        TextView textView = findViewById(R.id.allProviders);
        textView.setText(providerNames.toString());
    }

    private void listAllFreeLocationProviders() {
        Criteria cri = new Criteria();
        cri.setCostAllowed(false);
        cri.setAltitudeRequired(true);
        cri.setBearingRequired(true);

        /**
         * Samsung SM-G9730: null
         */
        List<String> providerNames = lm.getProviders(cri, false);

        TextView textView = findViewById(R.id.allFreeProviders);
        textView.setText(providerNames.toString());
    }

    @SuppressLint("MissingPermission")
    private void gpsLocationInfo() {
        /**
         * Samsung SM-G9730: no data
         * Android emulator send mock data
         */
        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER); // Request Runtime permission Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION
        setLocationInfo(location);
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000, 8, new LocationListener() {// Request Runtime permission Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION
            @Override
            public void onLocationChanged(Location location) {
                setLocationInfo(location);
            }

            @Override
            public void onProviderDisabled(String provider) {
                setLocationInfo(null);
            }

            @Override
            public void onProviderEnabled(String provider) {
                setLocationInfo(lm.getLastKnownLocation(provider));
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
            }
        });
    }

    public void setLocationInfo(Location newLocation) {
        TextView textView = findViewById(R.id.locationInfo);
        String info;
        if (newLocation == null) {
            info = "";
        } else {
            info = "经度：" + newLocation.getLongitude() + "\n" +
                    "纬度：" + newLocation.getLatitude() + "\n" +
                    "高度：" + newLocation.getAltitude() + "\n" +
                    "速度：" + newLocation.getSpeed() + "\n" +
                    "方向：" + newLocation.getBearing();
        }
        textView.setText(info);
    }

    @SuppressLint("MissingPermission")
    private void proximityAlert() {
        double longitude = 30;
        double latitude = 50;
        float radius = 5000; // 定义半径（5公里）

        Intent intent = new Intent(this, ProximityAlertReceiver.class); // 定义Intent
        PendingIntent pi = PendingIntent.getBroadcast(this, -1, intent, 0);  // 将Intent包装成PendingIntent

        lm.addProximityAlert(latitude, longitude, radius, -1, pi); // 添加临近警告
    }
}