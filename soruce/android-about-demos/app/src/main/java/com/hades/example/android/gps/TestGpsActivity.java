package com.hades.example.android.gps;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.hades.example.android.R;
import com.hades.example.android.lib.base.RxPermissionsActivity;

import java.util.List;

public class TestGpsActivity extends RxPermissionsActivity {
    private static final String TAG = TestGpsActivity.class.getSimpleName();

    LocationManager mLocationManager;

    private LocationListener mLocationListener;

    private ProximityAlertReceiver mProximityAlertReceiver = new ProximityAlertReceiver();
    public final static String PROXIMITY_ALERT_ACTION = "PROXIMITY_ALERT_ACTION";

    private FusedLocationProviderClient mFusedLocationClient;
    private LocationCallback mLocationCallback;
    private LocationRequest mLocationRequest;

    @SuppressLint("MissingPermission")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gps);

        initViews();

        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE); // 获取系统的LocationManager对象

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this); // Google play store Location

        listAllLocationProviders();
        listAllFreeLocationProviders();

        proximityAlert();

        lastLocation();
        lastLocation_4_GooglePlayServices();

        initLocationUpdates();
        initLocationUpdates_4_GooglePlayServices();
    }

    @Override
    protected void requestPermission() {
        checkPermission("Request permission for GPS", Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(mProximityAlertReceiver, new IntentFilter(PROXIMITY_ALERT_ACTION));

        startLocationUpdates();
        startLocationUpdates_4_GooglePlayServices();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mProximityAlertReceiver);

        stopLocationUpdates();
        stopLocationUpdates_4_GooglePlayServices();
    }

    private void listAllLocationProviders() {
        /**
         * Samsung SM-G9730
         * passive, gps, network
         */
        List<String> providerNames = mLocationManager.getAllProviders();

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
        List<String> providerNames = mLocationManager.getProviders(cri, false);

        TextView textView = findViewById(R.id.allFreeProviders);
        textView.setText(providerNames.toString());
    }

    @SuppressLint("MissingPermission")
    private void proximityAlert() { // Test result: Not Produced
        double longitude = 30;
        double latitude = 50;
        float radius = 5000; // 定义半径（5公里）

        Intent intent = new Intent(PROXIMITY_ALERT_ACTION); // 定义Intent
        PendingIntent pi = PendingIntent.getBroadcast(this, -1, intent, 0);  // 将Intent包装成PendingIntent

        mLocationManager.addProximityAlert(latitude, longitude, radius, -1, pi); // 添加临近警告
    }

    @SuppressLint("MissingPermission")
    private void lastLocation() {
        /**
         * Samsung SM-G9730: no data
         * Android emulator send mock data
         */
        Location location = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER); // Request Runtime permission Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION
        String info = parseLocation(location);
        Log.d(TAG, "lastLocation: " + info);
        setLocationInfo(location);
    }

    @SuppressLint("MissingPermission")
    private void lastLocation_4_GooglePlayServices() {
        mFusedLocationClient.getLastLocation().addOnSuccessListener(this, location -> {
            if (null == location) {
                return;
            }
            String info = parseLocation(location);
            Log.d(TAG, "lastLocation 4 GooglePlayServices: " + info);
        });
    }

    private void initLocationUpdates() {
        if (null == mLocationListener) {
            mLocationListener = new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    if (null == location) {
                        return;
                    }
                    String info = parseLocation(location);
                    Log.d(TAG, "onLocationChanged: " + info);
                    setLocationInfo(location);
                }

                @Override
                public void onProviderDisabled(String provider) {
                    setLocationInfo(null);
                }

                @Override
                public void onProviderEnabled(String provider) {
                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {
                }
            };
        }
    }

    @SuppressLint("MissingPermission")
    private void startLocationUpdates() {
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, mLocationListener);
    }

    private void stopLocationUpdates() {
        mLocationManager.removeUpdates(mLocationListener);
    }

    public void setLocationInfo(Location newLocation) {
        TextView textView = findViewById(R.id.lastLocation);
        String info = parseLocation(newLocation);
        textView.setText(info);
    }

    public String parseLocation(Location newLocation) {
        if (newLocation == null) {
            return "";
        }
        return "经度：" + newLocation.getLongitude() + "," +
                "纬度：" + newLocation.getLatitude() + "," +
                "高度：" + newLocation.getAltitude() + "," +
                "速度：" + newLocation.getSpeed() + "," +
                "方向：" + newLocation.getBearing();
    }

    private void initLocationUpdates_4_GooglePlayServices() {
        mLocationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
                for (Location location : locationResult.getLocations()) {
                    if (null == location) {
                        continue;
                    }
                    String info = parseLocation(location);
                    Log.d(TAG, "onLocationResult: " + info);
                }
            }
        };

        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);          // 应用接收位置更新的速率（毫秒） => 1s
        mLocationRequest.setFastestInterval(5000);   // 应用处理位置更新的最快速率（毫秒）=> 5s
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY); // 设置请求的优先级
    }

    @SuppressLint("MissingPermission")
    private void startLocationUpdates_4_GooglePlayServices() {
        mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.getMainLooper());
    }

    private void stopLocationUpdates_4_GooglePlayServices() {
        mFusedLocationClient.removeLocationUpdates(mLocationCallback);
    }
}