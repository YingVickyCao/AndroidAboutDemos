package com.hades.example.android.app_component.broadcast.ordered;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class OrderMsgReceiver1 extends BroadcastReceiver {
    private static final String TAG = OrderMsgReceiver1.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive: " + intent.getAction());

        //  1 -> 3 -> 2 => abort = 1
        if (isOrderedBroadcast()) {
            abortBroadcast();
        }
    }
}