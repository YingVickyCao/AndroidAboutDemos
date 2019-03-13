package com.hades.example.android.b.bound_service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Chronometer;

import com.hades.example.android.b.LogHelper;

import java.lang.ref.WeakReference;

public class RemoteBoundedService extends Service {
    private static final String TAG = RemoteBoundedService.class.getSimpleName();
    /**
     * Command to the service to display a message
     */
    static final int MSG_REQUEST = 1;
    static final int MSG_RESPONSE = 2;
    private Chronometer mChronometer;
    /**
     * Target we publish for clients to send messages to IncomingHandler.
     */
    Messenger mMessenger;


    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate: " + LogHelper.getThreadInfo());
        super.onCreate();

        mChronometer = new Chronometer(this);
        mChronometer.setBase(SystemClock.elapsedRealtime());
        mChronometer.start();
    }

    /**
     * When binding to the service, we return an interface to our messenger
     * for sending messages to the service.
     */
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: " + LogHelper.getThreadInfo());
        mMessenger = new Messenger(new IncomingHandler(this));
        return mMessenger.getBinder();
    }

    @Override
    public void onRebind(Intent intent) {
        Log.d(TAG, "onRebind: " + LogHelper.getThreadInfo());
        super.onRebind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind: " + LogHelper.getThreadInfo());
        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mChronometer.stop();
        Log.d(TAG, "onDestroy: " + LogHelper.getThreadInfo());
    }

    /**
     * Handler of incoming messages from clients.
     */
    static class IncomingHandler extends Handler {
        private final WeakReference<RemoteBoundedService> mService;

        public IncomingHandler(RemoteBoundedService service) {
            mService = new WeakReference<>(service);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_REQUEST:
                    Log.d(TAG, "handleMessage: " + LogHelper.getThreadInfo());
                    Messenger activityMessenger = msg.replyTo;
                    response(activityMessenger);
                    break;

                default:
                    super.handleMessage(msg);
            }
        }

        private void response(Messenger activityMessenger) {
            new Thread(() -> {
                Bundle b = new Bundle();
                b.putString("TIME_STAMP", buildResponse(mService.get().mChronometer));
                Message replyMsg = Message.obtain(null, MSG_RESPONSE);
                replyMsg.what = MSG_RESPONSE;
                replyMsg.setData(b);
                try {
                    activityMessenger.send(replyMsg);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    final public static String buildResponse(Chronometer chronometer) {
        long elapsedMillis = SystemClock.elapsedRealtime() - chronometer.getBase();
        int hours = (int) (elapsedMillis / 3600000);
        int minutes = (int) (elapsedMillis - hours * 3600000) / 60000;
        int seconds = (int) (elapsedMillis - hours * 3600000 - minutes * 60000) / 1000;
        int millis = (int) (elapsedMillis - hours * 3600000 - minutes * 60000 - seconds * 1000);
        return hours + ":" + minutes + ":" + seconds + ":" + millis;
    }
}
