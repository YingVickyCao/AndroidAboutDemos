package com.hades.example.android.app_component.service.system.sms_manager;


import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.EditText;
import android.widget.Toast;

import com.hades.example.android.R;


public class SendSmsActivity extends Activity {
    EditText number, content;
    SmsManager sManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_system_sms);

        sManager = SmsManager.getDefault();

        number = findViewById(R.id.number);
        content = findViewById(R.id.content);

        findViewById(R.id.sendImplicitBroadcast).setOnClickListener(v -> send());
    }

    private void send() {
        /**
         * PendingIntent
         */
        PendingIntent pi = PendingIntent.getBroadcast(SendSmsActivity.this, 0, new Intent(), 0);
        sManager.sendTextMessage(number.getText().toString(), null, content.getText().toString(), pi, null);
        Toast.makeText(SendSmsActivity.this, "短信发送完成", Toast.LENGTH_SHORT).show();
    }
}