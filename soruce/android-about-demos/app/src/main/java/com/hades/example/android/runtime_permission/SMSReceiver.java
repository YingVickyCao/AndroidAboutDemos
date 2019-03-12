package com.hades.example.android.runtime_permission;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import static android.provider.Telephony.Sms.Intents.SMS_RECEIVED_ACTION;

/**
 * TestCase:
 * Case 1: When  deny SMS - SEND_SMS permission, SMSReceiver does not call onReceive.
 * Case 2: When allow SMS - SEND_SMS permission, SMSReceiver call onReceive, and receive SMS_RECEIVED_ACTION action.
 */

public class SMSReceiver extends BroadcastReceiver {
    private static final String TAG = SMSReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.d(TAG, "action: " + action);

        // TODO: 14/12/2017 parse the message in detail
        if (SMS_RECEIVED_ACTION.equals(action)) {
            Bundle bundle = intent.getExtras();
            // 提取短信消息
            Object[] pdus = (Object[]) bundle.get("pdus");
            SmsMessage[] messages = new SmsMessage[pdus.length];
            for (int i = 0; i < messages.length; i++) {
                messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
            }
            // 获取发送方号码
            String address = messages[0].getOriginatingAddress();
            String fullMessage = "";
            for (SmsMessage message : messages) {
                // 获取短信内容
                fullMessage += message.getMessageBody();
            }
            Log.d(TAG, "onReceive:address=" + address + ",fullMessage=" + fullMessage);
            Toast.makeText(context, "address=" + address + ",fullMessage=" + fullMessage, Toast.LENGTH_SHORT).show();
        }
    }
}
