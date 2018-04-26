package com.hades.android.example.android_about_demos.runtime_permission.lib;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.hades.android.example.android_about_demos.R;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * http://www.cnblogs.com/huhx/p/sendMessage.html
 * http://blog.csdn.net/chong7171/article/details/9024421
 * http://blog.csdn.net/lincyang/article/details/47686693
 * http://blog.csdn.net/mad1989/article/details/22426415/
 * http://blog.csdn.net/OOC_ZC/article/details/71249409o
 * https://developer.android.com/training/permissions/index.html
 * https://developer.android.google.cn/training/permissions/requesting.html
 * http://wiki.jikexueyuan.com/project/material-design/components/snackbars-and-toasts.html
 * http://blog.csdn.net/victor_fang/article/details/54631899
 * http://www.jianshu.com/p/0beb6243d650
 * http://blog.csdn.net/m0_37168878/article/details/624437654
 * http://blog.csdn.net/qq_33923079/article/details/53453607
 * http://blog.csdn.net/victor_fang/article/details/54631899
 * http://www.jianshu.com/p/f346b7446610
 * https://www.cnblogs.com/whycxb/p/6818685.html
 * <p> SMS:
 * SEND_SMS
 * RECEIVE_SMS
 * READ_SMS
 * RECEIVE_WAP_PUSH
 * RECEIVE_MMS
 * <p>
 * 1 删除app后，申请权限消失。重新安装后，重新申请权限。
 * 2 仅仅申请RECEIVE_SMS权限，SMSReceiver能成功接受SMS_RECEIVED_ACTION广播。SEND_SMS不用申请权限，也能成功发送信息。
 */
public class RxPermissionsTestActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = RxPermissionsTestActivity.class.getSimpleName();

    /**
     * Root of the layout of this Activity.
     */
    private View mLayout;
    private RxPermissions rxPermissions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle(R.string.rxPermissions_topic);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lib_rxpermissions_layout);
        mLayout = findViewById(R.id.sample_main_layout);

        rxPermissions = new RxPermissions(this); // where this is an Activity instance
        rxPermissions.setLogging(true);

        findViewById(R.id.sendMessage).setOnClickListener(this);
        findViewById(R.id.sendReceiveMessage).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sendMessage:
                sendMessage();
                break;

            case R.id.sendReceiveMessage:
                sendReceiveMessage();
                break;
        }
    }

    private Activity getActivity() {
        return this;
    }

    private void showRequestPermissionRationale4SendMessage() {
        Snackbar.make(mLayout, R.string.permission_rationale_4_send_message,
                Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.ok, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        requestPermission4SendMessage();
                    }
                })
                .show();
    }

    private void showRequestPermissionRationale4SendReceiveMessage() {
        Snackbar.make(mLayout, R.string.permission_rationale_4_send_receive_message,
                Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.ok, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        requestPermission4SendReceiveMessage();
                    }
                })
                .show();
    }

    private void sendMessage() {
        Toast.makeText(this, "sendMessage", Toast.LENGTH_SHORT).show();
        if (rxPermissions.isGranted(Manifest.permission.SEND_SMS)) {
            return;
        }

        rxPermissions.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.SEND_SMS).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean shouldShowRequestPermissionRationale) throws Exception {
                if (shouldShowRequestPermissionRationale) {
                    showRequestPermissionRationale4SendMessage();
                } else {
                    requestPermission4SendMessage();
                }
            }
        });
    }

    private void sendReceiveMessage() {
        Toast.makeText(this, "sendReceiveMessage", Toast.LENGTH_SHORT).show();
        if (rxPermissions.isGranted(Manifest.permission.SEND_SMS) && rxPermissions.isGranted(Manifest.permission.RECEIVE_SMS)) {
            return;
        }

        rxPermissions.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.SEND_SMS, Manifest.permission.RECEIVE_SMS).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean shouldShowRequestPermissionRationale) throws Exception {
                if (shouldShowRequestPermissionRationale) {
                    showRequestPermissionRationale4SendReceiveMessage();
                } else {
                    requestPermission4SendReceiveMessage();
                }
            }
        });
    }

    private void requestPermission4SendMessage() {
        rxPermissions.request(Manifest.permission.SEND_SMS).subscribe(new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(Boolean granted) {
                if (granted) {
                    doSendMessage();
                    showPermissionAvailable4SendMessage();
                } else {
                    showPermissionsNotGranted();
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

    private void requestPermission4SendReceiveMessage() {
        rxPermissions.request(Manifest.permission.SEND_SMS, Manifest.permission.RECEIVE_SMS).subscribe(new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(Boolean granted) {
                if (granted) {
                    showPermissionAvailable4SendReceiveMessage();
                } else {
                    showPermissionsNotGranted();
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

    private void requestPermission4SendReceiveMessage2() {
        rxPermissions.requestEach(Manifest.permission.SEND_SMS, Manifest.permission.RECEIVE_SMS)//权限名称，多个权限之间逗号分隔开
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {
                        Log.e(TAG, "{accept}permission.name=" + permission.name);
                        Log.e(TAG, "{accept}permission.granted=" + permission.granted);
                        if (permission.name.equals(Manifest.permission.SEND_SMS) && permission.granted) {
                            showPermissionAvailable4SendReceiveMessage();
                        } else {
                            showPermissionsNotGranted();
                        }
                    }
                });
    }

    private void showPermissionAvailable4SendMessage() {
        Toast.makeText(this, R.string.permission_available_4_send_message, Toast.LENGTH_SHORT).show();
    }

    private void showPermissionAvailable4SendReceiveMessage() {
        Toast.makeText(this, R.string.permission_available_4_send_receive_message, Toast.LENGTH_SHORT).show();
    }

    private void showPermissionsNotGranted() {
        Toast.makeText(this, R.string.permissions_not_granted, Toast.LENGTH_SHORT).show();
    }

    private void doSendMessage() {
        doSendMessage(get2SendPhoneNum(), get2SendMessage());
    }

    private String get2SendMessage() {
        return "This is a test for send message.timeStamp=" + System.currentTimeMillis();
    }

    private String get2SendPhoneNum() {
        return "175694236919";
    }

    private String getRecevierMessage() {
        return "Hi, can you receiver this message.";
    }

    private void doSendMessage(String phoneNumber, String message) {
        SmsManager sms = SmsManager.getDefault();
        /*PendingIntent sentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, RuntTimePermissionTestActivity.class), 0);*/
        PendingIntent sentIntent = null;
        /**
         * if message's length more than 70 ,then call divideMessage to dive message into several part and call sendTextMessage() else direct call sendTextMessage()
         */
        if (message.length() > 70) {
            ArrayList<String> msgs = sms.divideMessage(message);
            for (String msg : msgs) {
                sms.sendTextMessage(phoneNumber, null, msg, sentIntent, null);
            }
        } else {
            /**
             public void sendTextMessage(String destinationAddress, String scAddress, String text,PendingIntent sentIntent, PendingIntent deliveryIntent)
             destinationAddress:  // 目的地，也就是对方的手机号
             scAddress：          // 服务中心地址，为空的话就是默认的SMSC
             text：               // 发送消息的具体内容
             sentIntent：         // 当消息成功或者失败发送时，就发起这个广播
             deliveryIntent：     // 当消息到达目的地时，就发起这个广播
             */
            // aidl服务,进程间的通信
            /**
             * When run on Android 7.0（API 24）
             * ERROR:   java.lang.SecurityException: Sending SMS message: uid 10078 does not have android.permission.SEND_SMS.
             * Solution:
             *  When run on Android 6.0 (API 23)，or targetSdkVersion Android 6.0 (API 23), add runtime permission request.
             *  So, sendMessage() func add isLackSendMsgPermission check.
             * */
            sms.sendTextMessage(phoneNumber, null, message, sentIntent, null);
        }
        Toast.makeText(this, "短信发送完成", Toast.LENGTH_SHORT).show();
    }
}
