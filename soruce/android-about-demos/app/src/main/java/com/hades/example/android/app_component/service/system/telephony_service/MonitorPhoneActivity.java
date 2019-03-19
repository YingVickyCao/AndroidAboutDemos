package com.hades.example.android.app_component.service.system.telephony_service;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.hades.example.android.R;

import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Date;

/*
 <!-- 授予该应用读取通话状态的权限 -->
 <uses-permission android:name="android.permission.READ_PHONE_STATE"/>
 */
public class MonitorPhoneActivity extends Activity {
    private static final String TAG = MonitorPhoneActivity.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_system_telehphone_service_4_monitor_phone);

        monitorPhone();
    }

    private void monitorPhone() {
        TelephonyManager tManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        tManager.listen(getPhoneStateListener(), PhoneStateListener.LISTEN_CALL_STATE);
    }

    private PhoneStateListener getPhoneStateListener() {
        return new PhoneStateListener() {
            @Override
            public void onCallStateChanged(int state, String number) {
                Log.d(TAG, "onCallStateChanged:state=" + state + ",number=" + state);
                switch (state) {
                    case TelephonyManager.CALL_STATE_IDLE: // 0 =无响应、关闭通话
                        break;

                    case TelephonyManager.CALL_STATE_OFFHOOK: // 2 = hold on / dialing，对来电作出反应
                        break;

                    case TelephonyManager.CALL_STATE_RINGING: // 1 = 来电
                        OutputStream os = null;
                        try {
                            os = openFileOutput("phoneList", MODE_APPEND);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        PrintStream ps = new PrintStream(os);
                        // 将来电号码记录到文件中
                        ps.println(new Date() + " 来电：" + number);
                        ps.close();
                        break;
                    default:
                        break;
                }
                super.onCallStateChanged(state, number);
            }
        };

    }
}