package com.hades.example.android.app_component.broadcast.normal;


import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.hades.example.android.R;

import static com.hades.example.android.app_component.broadcast.normal.SimpleReceiver.ACTION_ONE;
import static com.hades.example.android.app_component.broadcast.normal.SimpleReceiver.KEY_ONE;


public class TestNormalBroadcastActivity extends Activity {
    private SimpleReceiver mSimpleReceiver;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.broadcast_normal);

        mSimpleReceiver = new SimpleReceiver();

        findViewById(R.id.sendImplicitBroadcast).setOnClickListener(v -> sendImplicitBroadcast());
        findViewById(R.id.sendExplicitBroadcast).setOnClickListener(v -> sendExplicitBroadcast());
    }

    @Override
    protected void onResume() {
        super.onResume();

        receivingImplicitBroadcast();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mSimpleReceiver);
    }

    // QA：Receiving an Implicit broadcast
    private void receivingImplicitBroadcast() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION_ONE);
//        registerReceiver(mSimpleReceiver, filter);
        LocalBroadcastManager.getInstance(this).registerReceiver(mSimpleReceiver, filter);
    }

    // QA：Sending an Implicit Broadcast.(通常)
    private void sendImplicitBroadcast() {
        Intent intent = new Intent();
        intent.setAction(ACTION_ONE);
        intent.putExtra(KEY_ONE, "implicit Broadcast");
//        sendBroadcast(intent);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    /**
     * QA：Sending an Explicit Broadcast
     */
    private void sendExplicitBroadcast() {
        Intent intent = new Intent(this, SimpleReceiver.class);
        intent.setAction(ACTION_ONE);
        intent.putExtra(KEY_ONE, "Explicit broadcast");
        sendBroadcast(intent);
//        sendOrderedBroadcast();
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
//        LocalBroadcastManager.getInstance(this).sendBroadcastSync (intent);  == sendOrderedBroadcast()
    }
}