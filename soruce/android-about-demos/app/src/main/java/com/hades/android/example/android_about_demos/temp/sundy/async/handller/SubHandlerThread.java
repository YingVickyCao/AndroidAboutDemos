package com.hades.android.example.android_about_demos.temp.sundy.async.handller;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

import com.hades.android.example.android_about_demos.mock.LogHelper;
import com.hades.android.example.android_about_demos.mock.MockHeavyWork;
import com.hades.android.example.android_about_demos.temp.sundy.async.HandlerConceptActivity;

public class SubHandlerThread extends HandlerThread {
    private static final String TAG = SubHandlerThread.class.getSimpleName();

    private HandlerConceptActivity mHandlerConceptActivity;

    public SubHandlerThread(HandlerConceptActivity handlerConceptActivity, String name) {
        super(name);
        mHandlerConceptActivity = handlerConceptActivity;
    }

    @Override
    public void onLooperPrepared() {
        long result = new MockHeavyWork().sum();

        LogHelper.logThreadInfo(TAG, "onLooperPrepared()", String.valueOf(result));

        Looper lp = this.getLooper();
        new Handler(lp) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                LogHelper.logThreadInfo(TAG, "onLooperPrepared()->handleMessage()", String.valueOf(result));
            }

        }.sendMessage(mHandlerConceptActivity.defineNewMessage("Lab2.2=" + result));
    }
}
