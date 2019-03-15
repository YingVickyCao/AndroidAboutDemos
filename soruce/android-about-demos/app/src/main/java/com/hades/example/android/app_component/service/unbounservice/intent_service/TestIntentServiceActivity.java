package com.hades.example.android.app_component.service.unbounservice.intent_service;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.hades.example.android.R;


public class TestIntentServiceActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_intent_service);
        findViewById(R.id.startService).setOnClickListener(v -> startService());
        findViewById(R.id.startIntentService).setOnClickListener(v -> startIntentService());
    }

    public void startService() {
        Intent intent = new Intent(this, MyService.class);
        startService(intent);
    }

    public void startIntentService() {
        Intent intent = new Intent(this, MyIntentService.class);
        startService(intent);
    }
}