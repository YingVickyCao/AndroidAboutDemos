package com.hades.example.android.app_component.broadcast.ordered;


import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import com.hades.example.android.R;


public class TestOrderedBroadcastActivity extends Activity {

    public static final String ACTION_TWO = "com.hades.example.android.app_component.broadcast.normal.BootCompletedReceiver.TWO";

    private OrderMsgReceiver1 mReceiver1;
    private OrderMsgReceiver2 mReceiver2;
    private OrderMsgReceiver3 mReceiver3;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.broadcast_ordered);

        mReceiver1 = new OrderMsgReceiver1();
        mReceiver2 = new OrderMsgReceiver2();
        mReceiver3 = new OrderMsgReceiver3();

        findViewById(R.id.sendOrderedBroadcast).setOnClickListener(v -> sendOrderedBroadcast());
    }

    @Override
    protected void onResume() {
        super.onResume();


//        test1();
        test2();
    }

    private void test1() {
        // If no action, can not receive anything
        IntentFilter intentFilter = new IntentFilter(ACTION_TWO);
//        IntentFilter intentFilter = new IntentFilter();
        registerReceiver(mReceiver1, intentFilter);

        IntentFilter intentFilter2 = new IntentFilter(ACTION_TWO);
//        IntentFilter intentFilter2 = new IntentFilter();
        registerReceiver(mReceiver2, intentFilter2);

        IntentFilter intentFilter3 = new IntentFilter(ACTION_TWO);
//        IntentFilter intentFilter3 = new IntentFilter();
        registerReceiver(mReceiver3, intentFilter3);
    }

    private void test2() {
        // 1 -> 3 -> 2
        IntentFilter intentFilter = new IntentFilter(ACTION_TWO);
        intentFilter.setPriority(3);
        registerReceiver(mReceiver1, intentFilter);

        IntentFilter intentFilter2 = new IntentFilter(ACTION_TWO);
        intentFilter2.setPriority(1);
        registerReceiver(mReceiver2, intentFilter2);

        IntentFilter intentFilter3 = new IntentFilter(ACTION_TWO);
        intentFilter3.setPriority(2);
        registerReceiver(mReceiver3, intentFilter3);
    }

    @Override
    protected void onPause() {
        super.onPause();

        unregisterReceiver(mReceiver1);
        unregisterReceiver(mReceiver2);
        unregisterReceiver(mReceiver3);
    }

    private void sendOrderedBroadcast() {
        sendOrderedBroadcast(new Intent(ACTION_TWO), null);
    }
}