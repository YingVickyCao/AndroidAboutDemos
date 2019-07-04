package com.hades.example.android.runtime_permission;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.snackbar.Snackbar;
import com.hades.example.android.R;

import java.util.ArrayList;

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
public class RuntTimePermissionTestActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = RuntTimePermissionTestActivity.class.getSimpleName();

    private static final int REQUEST_CODE_4_REQUEST_PERMISSIONS_4_SEND_MESSAGE = 1000;
    private static final int REQUEST_CODE_4_REQUEST_PERMISSIONS_4_RECEIVE_MESSAGE = 2000;
    private static final int REQUEST_CODE_4_REQUEST_PERMISSIONS_4_SEND_RECEIVE_MESSAGE = 3000;
    private static final int REQUEST_CODE_4_REQUEST_PERMISSIONS_GROUP_4_SMS = 4000;

    /**
     * Root of the layout of this Activity.
     */
    private View mLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle(R.string.runt_time_permission);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run_time_permission_layout);
        mLayout = findViewById(R.id.sample_main_layout);

        findViewById(R.id.sendMessage).setOnClickListener(this);
        findViewById(R.id.receiveMessage).setOnClickListener(this);
        findViewById(R.id.sendReceiveMessage).setOnClickListener(this);
        findViewById(R.id.permissionGroup).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sendMessage:
                sendMessage();
                break;

            case R.id.receiveMessage:
                receiveMessage();
                break;

            case R.id.sendReceiveMessage:
                sendReceiveMessage();
                break;

            case R.id.permissionGroup:
                permissionGroup();
                break;
        }
    }

    private void sendMessage() {
        Toast.makeText(this, "sendMessage", Toast.LENGTH_SHORT).show();
        if (isLackSendMsgPermission()) {
            requestSendMessagePermission();
        } else {
            doSendMessage();
        }
    }

    private void receiveMessage() {
        Toast.makeText(this, "receiveMessage", Toast.LENGTH_SHORT).show();
        if (isLackReceiveMsgPermission()) {
            requestReceiveMessagePermission();
        }
    }

    private void sendReceiveMessage() {
        Toast.makeText(this, "sendReceiveMessage", Toast.LENGTH_SHORT).show();
        if (isLackSendReceiveMsgPermission()) {
            requestSendReceiveMessagePermission();
        }
    }


    private void permissionGroup() {
        if (isLackSMSPermissionGroup()) {
            requestSMSPermissionGroup();
        }
    }

    private void doSendMessage() {
        doSendMessage(get2SendPhoneNum(), get2SendMessage());
    }

    private void doSendMessage(String phoneNumber, String message) {
        SmsManager sms = SmsManager.getDefault();
        PendingIntent sentIntent = null;
        /*PendingIntent sentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, RuntTimePermissionTestActivity.class), 0);*/
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

    private String get2SendMessage() {
        return "This is a test for simpleNotify message.timeStamp=" + System.currentTimeMillis();
    }

    private String get2SendPhoneNum() {
        return "175694236919";
    }

    private String getRecevierMessage() {
        return "Hi, can you receiver this message.";
    }


    private boolean isLackSendMsgPermission() {
        return checkPermission(Manifest.permission.SEND_SMS);
    }

    private boolean isLackReceiveMsgPermission() {
        return checkPermission(Manifest.permission.RECEIVE_SMS);
    }

    private boolean isLackSendReceiveMsgPermission() {
        return checkPermission(Manifest.permission.SEND_SMS) || checkPermission(Manifest.permission.RECEIVE_SMS);
    }

    private boolean isLackSMSPermissionGroup() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return checkPermission(Manifest.permission_group.SMS);
        } else {
            return false;
        }
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

    private void showRequestPermissionRationale4ReceiveMessage() {
        Snackbar.make(mLayout, R.string.permission_rationale_4_receive_message,
                Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.ok, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        requestPermission4ReceiveMessage();
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

    private void showRequestPermissionRationale4PermissionGroup4SMS() {
        Snackbar.make(mLayout, R.string.permission_rationale_4_permission_group_4_sms,
                Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.ok, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        requestGroupPermission4SMS();
                    }
                })
                .show();
    }

    private boolean shouldShowRequestPermissionRationale4SendMessage() {
        return ActivityCompat.shouldShowRequestPermissionRationale(getContext(), Manifest.permission.SEND_SMS);
    }

    private boolean shouldShowRequestPermissionRationale4ReceiveMessage() {
        return ActivityCompat.shouldShowRequestPermissionRationale(getContext(), Manifest.permission.RECEIVE_SMS);
    }

    private boolean shouldShowRequestPermissionRationale4SendReceiveMessage() {
        return ActivityCompat.shouldShowRequestPermissionRationale(getContext(), Manifest.permission.SEND_SMS)
                && ActivityCompat.shouldShowRequestPermissionRationale(getContext(), Manifest.permission.RECEIVE_SMS);
    }

    private boolean shouldShowRequestPermissionRationale4SMSGroup() {
        return ActivityCompat.shouldShowRequestPermissionRationale(getContext(), Manifest.permission_group.SMS);
    }

    private void requestSendMessagePermission() {
        // Should we show an explanation?
        if (shouldShowRequestPermissionRationale4SendMessage()) {
            // Show an explanation to the user *asynchronously* -- don't block this thread waiting for the user's response!
            // After the user sees the explanation, try again to request the permission.
            Log.d(TAG, "Displaying sendMessage permission rationale to provide additional context.");
            showRequestPermissionRationale4SendMessage();
        } else {
            // No explanation needed, we can request the permission.
            requestPermission4SendMessage();
        }
    }

    private void requestReceiveMessagePermission() {
        if (shouldShowRequestPermissionRationale4ReceiveMessage()) {
            Log.d(TAG, "Displaying receiveMessage permission rationale to provide additional context.");
            showRequestPermissionRationale4ReceiveMessage();
        } else {
            requestPermission4ReceiveMessage();
        }
    }

    private void requestSendReceiveMessagePermission() {
        if (shouldShowRequestPermissionRationale4SendReceiveMessage()) {
            Log.d(TAG, "Displaying simpleNotify and receive message permission rationale to provide additional context.");
            showRequestPermissionRationale4SendReceiveMessage();
        } else {
            requestPermission4SendReceiveMessage();
        }
    }

    private void requestSMSPermissionGroup() {
        if (shouldShowRequestPermissionRationale4SMSGroup()) {
            Log.d(TAG, "Displaying SMS permission rationale to provide additional context.");
            showRequestPermissionRationale4PermissionGroup4SMS();
        } else {
            requestPermission4SendMessage();
        }
    }

    private void requestPermission4SendMessage() {
        ActivityCompat.requestPermissions(getContext(), new String[]{Manifest.permission.SEND_SMS}, REQUEST_CODE_4_REQUEST_PERMISSIONS_4_SEND_MESSAGE);
    }

    private void requestPermission4ReceiveMessage() {
        ActivityCompat.requestPermissions(getContext(), new String[]{Manifest.permission.RECEIVE_SMS}, REQUEST_CODE_4_REQUEST_PERMISSIONS_4_RECEIVE_MESSAGE);
    }

    private void requestPermission4SendReceiveMessage() {
        ActivityCompat.requestPermissions(getContext(), new String[]{Manifest.permission.SEND_SMS, Manifest.permission.RECEIVE_SMS}, REQUEST_CODE_4_REQUEST_PERMISSIONS_4_RECEIVE_MESSAGE);
    }

    private void requestGroupPermission4SMS() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(getContext(), new String[]{Manifest.permission_group.SMS}, REQUEST_CODE_4_REQUEST_PERMISSIONS_GROUP_4_SMS);
        } else {
            Toast.makeText(this, R.string.min_api_4_group_permission, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Check For Permissions
     *
     * @return true: Lack permission. False: has permission.
     */
    private boolean checkPermission(String permission) {
        return PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(getContext(), permission);
    }

    private Activity getContext() {
        return this;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d(TAG, "onRequestPermissionsResult: requestCode=" + requestCode);
        switch (requestCode) {
            case REQUEST_CODE_4_REQUEST_PERMISSIONS_4_SEND_MESSAGE: {
                onRequestPermissionsResult4SendMessage(requestCode, permissions, grantResults);
                break;
            }

            case REQUEST_CODE_4_REQUEST_PERMISSIONS_4_RECEIVE_MESSAGE:
                onRequestPermissionsResult4ReceiveMessage(requestCode, permissions, grantResults);
                break;

            case REQUEST_CODE_4_REQUEST_PERMISSIONS_GROUP_4_SMS:
                onRequestPermissionsResult4GroupPermission4SMS(requestCode, permissions, grantResults);
                break;

            case REQUEST_CODE_4_REQUEST_PERMISSIONS_4_SEND_RECEIVE_MESSAGE:
                onRequestPermissionsResult4SendReceiveMessage(requestCode, permissions, grantResults);
                break;

            default:
                // other 'case' lines to check for other permissions this app might request
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;
        }
    }

    private void onRequestPermissionsResult4SendMessage(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (REQUEST_CODE_4_REQUEST_PERMISSIONS_4_SEND_MESSAGE != requestCode) {
            Log.e(TAG, "onRequestPermissionsResult4SendMessage: requestCode is wrong");
            return;
        }
        // Check if the only required permission has been granted
        // If request is cancelled, the result arrays are empty.
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            doSendMessage();

            // Send message permission has been granted, Group can be sent.
            Log.i(TAG, "Send message permission has now been granted. Can simpleNotify message.");
            showPermissionAvailable4SendMessage();

        } else {
            // permission denied, boo! Disable the functionality that depends on this permission.
            Log.i(TAG, "Send message permission was NOT granted.");
            showPermissionsNotGranted();
        }
    }

    private void showPermissionAvailable4SendMessage() {
        Toast.makeText(this, R.string.permission_available_4_send_message, Toast.LENGTH_SHORT).show();
    }

    private void showPermissionAvailable4ReceiveMessage() {
        Toast.makeText(this, R.string.permission_available_4_receive_message, Toast.LENGTH_SHORT).show();
    }

    private void showPermissionAvailable4SendReceiveMessage() {
        Toast.makeText(this, R.string.permission_available_4_send_receive_message, Toast.LENGTH_SHORT).show();
    }

    private void showPermissionAvailable4PermissionGroup4SMS() {
        Toast.makeText(this, R.string.permission_available_4_permission_group_4_sms, Toast.LENGTH_SHORT).show();
    }

    private void showPermissionsNotGranted() {
        Toast.makeText(this, R.string.permissions_not_granted, Toast.LENGTH_SHORT).show();
    }

    private void onRequestPermissionsResult4ReceiveMessage(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (REQUEST_CODE_4_REQUEST_PERMISSIONS_4_RECEIVE_MESSAGE != requestCode) {
            Log.e(TAG, "onRequestPermissionsResult4ReceiveMessage: requestCode is wrong");
            return;
        }
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            showPermissionAvailable4ReceiveMessage();
        } else {
            showPermissionsNotGranted();
        }
    }

    private void onRequestPermissionsResult4SendReceiveMessage(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (REQUEST_CODE_4_REQUEST_PERMISSIONS_4_SEND_RECEIVE_MESSAGE != requestCode) {
            Log.e(TAG, "onRequestPermissionsResult4ReceiveMessage: requestCode is wrong");
            return;
        }
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            showPermissionAvailable4SendReceiveMessage();
        } else {
            showPermissionsNotGranted();
        }
    }

    private void onRequestPermissionsResult4GroupPermission4SMS(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (REQUEST_CODE_4_REQUEST_PERMISSIONS_GROUP_4_SMS != requestCode) {
            Log.e(TAG, "onRequestPermissionsResult4GroupPermission4SMS: requestCode is wrong");
            return;
        }

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            showPermissionAvailable4PermissionGroup4SMS();
        } else {
            showPermissionsNotGranted();
        }
    }

}
