package com.hades.android.example.android_about_demos.temp.sundy.async;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class MyThreadHandler extends Handler {

    @Override
    public void handleMessage(Message msg) {
        // TODO Auto-generated method stub
        super.handleMessage(msg);
        Log.i("MyThreadHandler", "Get the message by Handler Thread");
    }

}
