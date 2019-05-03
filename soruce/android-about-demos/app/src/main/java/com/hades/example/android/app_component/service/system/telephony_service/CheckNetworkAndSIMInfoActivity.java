package com.hades.example.android.app_component.service.system.telephony_service;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import com.google.android.material.snackbar.Snackbar;
import com.hades.example.android.R;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/*
    <!-- 添加访问手机位置的权限 -->
    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"/>
    <!-- 添加访问手机状态的权限 -->
    <uses-permission android:name="android.permission.READ_PHONE_STATE"/>
 */
public class CheckNetworkAndSIMInfoActivity extends Activity {
    private RxPermissions rxPermissions;
    private View mRoot;
    private boolean mIsHasPermission = false;

    ListView showView;
    String[] statusNames;
    private ArrayList<String> statusValues = new ArrayList<>();
    private ArrayList<Map<String, String>> status = new ArrayList<>();
    private SimpleAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_system_telehphone_service_4_get_network_and_sim_info);

        rxPermissions = new RxPermissions(this);
        rxPermissions.setLogging(true);

        showView = findViewById(R.id.tableContentList);

        adapter = buildSimpleAdapter(status);
        showView.setAdapter(adapter);

//        findViewById(R.id.checkPermission).setOnClickListener(v -> checkPermission());
        findViewById(R.id.getData).setOnClickListener(v -> getData());
    }

    private void getData() {
        status.clear();
        statusValues.clear();

        buildData();
        status = buildListData();
        adapter.notifyDataSetChanged();
    }

    private void buildData() {
        // 获取系统的 TelephonyManager 对象
        TelephonyManager tManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        // 获取各种状态名称的数组
        statusNames = getResources().getStringArray(R.array.statusNames);
        // 获取代表SIM卡状态的数组
        String[] simState = getResources().getStringArray(R.array.simState);
        // 获取代表电话网络类型的数组
        String[] phoneType = getResources().getStringArray(R.array.phoneType);
        // 获取设备编号
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        // TODO: 15/10/2018
        statusValues.add(tManager.getDeviceId());
        statusValues.add(tManager.getDeviceId());
        // 获取系统平台的版本
        statusValues.add(tManager.getDeviceSoftwareVersion() != null ? tManager.getDeviceSoftwareVersion() : "未知");
        // 获取网络运营商代号
        statusValues.add(tManager.getNetworkOperator());
        // 获取网络运营商名称
        statusValues.add(tManager.getNetworkOperatorName());
        // 获取手机网络类型
        statusValues.add(phoneType[tManager.getPhoneType()]);
        // 获取设备所在位置
        statusValues.add(tManager.getCellLocation() != null ? tManager.getCellLocation().toString() : "未知位置");
        // 获取SIM卡的国别
        statusValues.add(tManager.getSimCountryIso());
        // 获取SIM卡序列号
        statusValues.add(tManager.getSimSerialNumber());
        // 获取SIM卡状态
        statusValues.add(simState[tManager.getSimState()]);
    }

    private ArrayList<Map<String, String>> buildListData() {
        for (int i = 0; i < statusValues.size(); i++) {
            HashMap<String, String> map = new HashMap<>();
            map.put("name", statusNames[i]);
            map.put("value", statusValues.get(i));
            status.add(map);
        }
        return status;
    }

    private SimpleAdapter buildSimpleAdapter(ArrayList<Map<String, String>> status) {
        return new SimpleAdapter(this, status, R.layout.service_system_telehphone_service_4_get_network_and_sim_info_item_view, new String[]{"name", "value"}, new int[]{R.id.name, R.id.value});
    }

    private void checkPermission() {
        if (!rxPermissions.isGranted(Manifest.permission.READ_PHONE_STATE) || !rxPermissions.isGranted(Manifest.permission.ACCESS_COARSE_LOCATION)) {
            askUser2GrantPermissions();
            return;
        } else {
            mIsHasPermission = true;
        }
    }

    private void requestPermission() {
        rxPermissions.request(Manifest.permission.READ_PHONE_STATE, Manifest.permission.ACCESS_COARSE_LOCATION).subscribe(new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(Boolean granted) {
                mIsHasPermission = granted;
                if (granted) {
                    Toast.makeText(CheckNetworkAndSIMInfoActivity.this, "permission available", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(CheckNetworkAndSIMInfoActivity.this, "permission not granted", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    private void askUser2GrantPermissions() {
        rxPermissions.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_PHONE_STATE, Manifest.permission.ACCESS_COARSE_LOCATION).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean shouldShowRequestPermissionRationale) throws Exception {
                if (shouldShowRequestPermissionRationale) {
                    Snackbar.make(mRoot, R.string.permission_rationale_4_send_message,
                            Snackbar.LENGTH_INDEFINITE)
                            .setAction(R.string.ok, new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    requestPermission();
                                }
                            })
                            .show();
                } else {
                    requestPermission();
                }
            }
        });
    }
}