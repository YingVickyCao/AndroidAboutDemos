package com.hades.example.android.app_component.cp.system.sms;


import android.Manifest;
import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.hades.example.android.R;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class SMSActivity extends Activity {
    private static final String TAG = SMSActivity.class.getSimpleName();

    private RxPermissions rxPermissions;
    private View mRoot;
    private SmsContentObserver mSmsContentObserver;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        setContentView(R.layout.cp_sms);

        rxPermissions = new RxPermissions(this);
        rxPermissions.setLogging(true);
        checkPermission();
        mRoot = findViewById(R.id.root);

        mSmsContentObserver = new SmsContentObserver(this, new Handler());
        // 为content://sms的数据改变注册监听器
        getContentResolver().registerContentObserver(Uri.parse("content://sms"), true, mSmsContentObserver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (null != mSmsContentObserver) {
            getContentResolver().unregisterContentObserver(mSmsContentObserver);
            mSmsContentObserver = null;
        }
    }

    private void checkPermission() {
        if (!rxPermissions.isGranted(Manifest.permission.READ_SMS)) {
            askUser2GrantPermissions();
        }
    }

    private void requestPermission() {
        rxPermissions.request(Manifest.permission.READ_SMS).subscribe(new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(Boolean granted) {
                if (granted) {
                    Toast.makeText(SMSActivity.this, "permission available", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SMSActivity.this, "permission not granted", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    private void askUser2GrantPermissions() {
        rxPermissions.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_SMS)
                .subscribe(shouldShowRequestPermissionRationale -> {
                    if (shouldShowRequestPermissionRationale) {
                        Snackbar.make(mRoot, "request read sms permission",
                                Snackbar.LENGTH_INDEFINITE)
                                .setAction(R.string.ok, view -> requestPermission())
                                .show();
                    } else {
                        requestPermission();
                    }
                });
    }

}