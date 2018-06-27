package com.hades.android.example.android_about_demos.temp.sundy.async.handller;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.hades.android.example.android_about_demos.temp.sundy.async.CommonConstants;
import com.hades.android.example.android_about_demos.temp.sundy.async.HandlerConceptActivity;

public class DelayHandler extends Handler {
    private HandlerConceptActivity mHandlerConceptActivity;

    public DelayHandler(HandlerConceptActivity handlerConceptActivity, Looper looper) {
        super(looper);
        mHandlerConceptActivity = handlerConceptActivity;
    }

    @Override
    public void handleMessage(Message msg) {
        // TODO Auto-generated method stub
        super.handleMessage(msg);
        Log.i(CommonConstants.LOGCAT_TAG_NAME, "Start message: " + msg.getData().getString(mHandlerConceptActivity.HANDLER_KEY));
        try {
            Thread.sleep(5 * 1000);
        } catch (Exception e) {
        }
        Log.i(CommonConstants.LOGCAT_TAG_NAME, "Finish message: " + msg.getData().getString(mHandlerConceptActivity.HANDLER_KEY));
    }
}
