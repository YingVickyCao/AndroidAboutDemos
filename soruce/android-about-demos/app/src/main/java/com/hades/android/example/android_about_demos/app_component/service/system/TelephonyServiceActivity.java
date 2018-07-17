package com.hades.android.example.android_about_demos.app_component.service.system;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.hades.android.example.android_about_demos.R;
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
public class TelephonyServiceActivity extends Activity {
    private RxPermissions rxPermissions;
    private View mRoot;
    private boolean mIsHasPermission = false;

    ListView showView;
    // 声明代表状态名的数组
    String[] statusNames;
    // 声明代表手机状态的集合
    ArrayList<String> statusValues = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_system_telephony_service);

        rxPermissions = new RxPermissions(this);
        rxPermissions.setLogging(true);

        findViewById(R.id.checkPermission).setOnClickListener(v -> checkPermission());

        // 获取系统的TelephonyManager对象
        TelephonyManager tManager = (TelephonyManager)
                getSystemService(Context.TELEPHONY_SERVICE);
        // 获取各种状态名称的数组
        statusNames = getResources().getStringArray(R.array.statusNames);
        // 获取代表SIM卡状态的数组
        String[] simState = getResources()
                .getStringArray(R.array.simState);
        // 获取代表电话网络类型的数组
        String[] phoneType = getResources().getStringArray(
                R.array.phoneType);
        // 获取设备编号
        statusValues.add(tManager.getDeviceId());
        // 获取系统平台的版本
        statusValues.add(tManager.getDeviceSoftwareVersion()
                != null ? tManager.getDeviceSoftwareVersion() : "未知");
        // 获取网络运营商代号
        statusValues.add(tManager.getNetworkOperator());
        // 获取网络运营商名称
        statusValues.add(tManager.getNetworkOperatorName());
        // 获取手机网络类型
        statusValues.add(phoneType[tManager.getPhoneType()]);
        // 获取设备所在位置
        statusValues.add(tManager.getCellLocation() != null ? tManager
                .getCellLocation().toString() : "未知位置");
        // 获取SIM卡的国别
        statusValues.add(tManager.getSimCountryIso());
        // 获取SIM卡序列号
        statusValues.add(tManager.getSimSerialNumber());
        // 获取SIM卡状态
        statusValues.add(simState[tManager.getSimState()]);
        // 获得ListView对象
        showView = (ListView) findViewById(R.id.show);
        ArrayList<Map<String, String>> status = new ArrayList<>();
        // 遍历statusValues集合，将statusNames、statusValues
        // 的数据封装到List<Map<String , String>>集合中
        for (int i = 0; i < statusValues.size(); i++) {
            HashMap<String, String> map = new HashMap<>();
            map.put("name", statusNames[i]);
            map.put("value", statusValues.get(i));
            status.add(map);
        }
        // 使用SimpleAdapter封装List数据
        SimpleAdapter adapter = new SimpleAdapter(this, status,
                R.layout.line, new String[]{"name", "value"}
                , new int[]{R.id.name, R.id.value});
        // 为ListView设置Adapter
        showView.setAdapter(adapter);
    }

    private void checkPermission() {
        if (!rxPermissions.isGranted(Manifest.permission.WRITE_CONTACTS) || !rxPermissions.isGranted(Manifest.permission.READ_CONTACTS)) {
            askUser2GrantPermissions();
            return;
        } else {
            mIsHasPermission = true;
        }
    }

    private void requestPermission() {
        rxPermissions.request(Manifest.permission.WRITE_CONTACTS, Manifest.permission.READ_CONTACTS).subscribe(new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(Boolean granted) {
                mIsHasPermission = granted;
                if (granted) {
                    Toast.makeText(TelephonyServiceActivity.this, "permission available", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(TelephonyServiceActivity.this, "permission not granted", Toast.LENGTH_SHORT).show();
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
        rxPermissions.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_CONTACTS, Manifest.permission.READ_CONTACTS).subscribe(new Consumer<Boolean>() {
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