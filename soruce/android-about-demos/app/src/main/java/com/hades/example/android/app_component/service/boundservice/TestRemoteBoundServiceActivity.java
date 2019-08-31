package com.hades.example.android.app_component.service.boundservice;

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
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.hades.example.android.BConstant;
import com.hades.example.android.R;

public class TestRemoteBoundServiceActivity extends Activity implements IResponse {
    private static final String TAG = TestRemoteBoundServiceActivity.class.getSimpleName();

    static final int MSG_REQUEST = 1;
    static final int MSG_RESPONSE = 2;

    /**
     * Messenger for communicating with the service.
     */
    Messenger mBoundServiceMessenger = null;
    Messenger mActivityMessenger;

    private ServiceConnection mConnection;
    /**
     * Flag indicating whether we have called bind on the service.
     */
    boolean bound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_bounded_service_test);
        ((TextView) findViewById(R.id.topic)).setText("Remote BoundService");

        setServiceConnection();

        findViewById(R.id.bind).setOnClickListener(v -> bindService());
        findViewById(R.id.bindAutoCreate).setOnClickListener(v -> bindAutoCreate());
        findViewById(R.id.bindAutoCreateInThread).setOnClickListener(v -> bindAutoCreateInThread());
        findViewById(R.id.unbind).setOnClickListener(v -> unbindService());
//        findViewById(R.id.getServiceStatus).setOnClickListener(v -> getServiceStatus());

        findViewById(R.id.start).setOnClickListener(v -> startService());
        findViewById(R.id.stopRecord).setOnClickListener(v -> stopService());
        findViewById(R.id.check).setOnClickListener(v -> check());
    }

    public void check() {
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

    private Intent buildIntent() {
//        Intent intent = new Intent(B_REMOTEBOUNDEDSERVICE_CLASS_ACTION); // Can not access B RemoteBoundedService
        Intent intent = new Intent(); // Can not access B RemoteBoundedService
        intent.setComponent(new ComponentName(BConstant.B_PACKAGE, BConstant.B_REMOTEBOUNDEDSERVICE_CLASS));
        return intent;
    }

    @Override
    protected void onStart() {
        super.onStart();
//        bindAutoCreate();
    }

    private void bindService() {
        bindService(buildIntent(), mConnection, 0);
    }

    private void bindAutoCreate() {
        /*
        Intent intent = new Intent();
        intent.setClassName(B_PACKAGE, "com.hades.example.android.b.CActivity");
        intent.putExtra("NUM1", 100);
        startActivity(intent);
        */
        bindService(buildIntent(), mConnection, Context.BIND_AUTO_CREATE);
    }

    private void bindAutoCreateInThread() {
        new Thread(() -> bindService(buildIntent(), mConnection, Context.BIND_AUTO_CREATE)).start();
    }

    @Override
    protected void onStop() {
        super.onStop();
//        unbindService();
    }

    private void unbindService() {
        // Unbind from the service
        if (bound) {
            unbindService(mConnection);
            bound = false;
        }
    }

    private void startService() {
        Log.d(TAG, "startService: ");
        Intent intent = buildIntent();
        startService(intent);
    }

    private void stopService() {
        Log.d(TAG, "stopService: ");
        Intent intent = buildIntent();
        stopService(intent);
    }

    private void setServiceConnection() {
        mConnection = new ServiceConnection() {
            public void onServiceConnected(ComponentName className, IBinder service) {
                // This is called when the connection with the service has been established, giving us the object we can use to interact with the service.
                // We are communicating with the service using a Messenger, so here we get a client-side representation of that from the raw IBinder object.
                mBoundServiceMessenger = new Messenger(service);
                mActivityMessenger = new Messenger(new ActivityHandler(TestRemoteBoundServiceActivity.this));
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
    }

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
