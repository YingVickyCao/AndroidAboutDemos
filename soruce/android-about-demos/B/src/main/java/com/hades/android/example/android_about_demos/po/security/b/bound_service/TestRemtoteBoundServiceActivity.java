package com.hades.android.example.android_about_demos.po.security.b.bound_service;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.widget.Toast;

import com.hades.android.example.android_about_demos.po.security.b.R;

public class TestRemtoteBoundServiceActivity extends Activity implements IResponse {
    static final int MSG_REQUEST = 1;
    static final int MSG_RESPONSE = 2;

    /**
     * Messenger for communicating with the service.
     */
    Messenger mBoundServiceMessenger = null;
    Messenger mActivityMessenger;

    /**
     * Flag indicating whether we have called bind on the service.
     */
    boolean bound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_bounded_service_test2);

        findViewById(R.id.sayHello).setOnClickListener(v -> sayHello());
    }

    public void sayHello() {
        if (!bound) return;
        // Create and send a message to the service, using a supported 'what' value
        Message msg = Message.obtain(null, MSG_REQUEST, 0, 0);
        msg.replyTo = mActivityMessenger;
        try {
            mBoundServiceMessenger.send(msg);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Bind to the service
//        bindService(new Intent(this, RemoteBoundedService.class), mConnection, Context.BIND_AUTO_CREATE);
        new Thread(new Runnable() {
            @Override
            public void run() {
                bindService(new Intent(TestRemtoteBoundServiceActivity.this, RemoteBoundedService.class), mConnection, Context.BIND_AUTO_CREATE);
            }
        }).start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        // Unbind from the service
        if (bound) {
            unbindService(mConnection);
            bound = false;
        }
    }

    /**
     * Class for interacting with the main interface of the service.
     */
    private ServiceConnection mConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {
            // This is called when the connection with the service has been established, giving us the object we can use to interact with the service.
            // We are communicating with the service using a Messenger, so here we get a client-side representation of that from the raw IBinder object.
            mBoundServiceMessenger = new Messenger(service);
            mActivityMessenger = new Messenger(new ActivityHandler(TestRemtoteBoundServiceActivity.this));
            bound = true;
        }

        public void onServiceDisconnected(ComponentName className) {
            // This is called when the connection with the service has been
            // unexpectedly disconnected -- that is, its process crashed.
            mBoundServiceMessenger = null;
            mActivityMessenger = null;
            bound = false;
        }
    };

    @Override
    public void setData(String data) {
        Toast.makeText(this, "" + data, Toast.LENGTH_SHORT).show();
    }

    static class ActivityHandler extends Handler {
        private IResponse mIResponse;

        ActivityHandler(IResponse iResponse) {
            mIResponse = iResponse;
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_RESPONSE:
                    if (null != mIResponse) {
                        mIResponse.setData(msg.getData().getString("TIME_STAMP"));
                    }
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }
}
