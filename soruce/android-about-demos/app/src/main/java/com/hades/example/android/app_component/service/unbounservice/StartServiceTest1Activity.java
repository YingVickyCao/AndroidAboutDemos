package com.hades.example.android.app_component.service.unbounservice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.hades.example.android.R;

public class StartServiceTest1Activity extends Activity {
    private static final String TAG = StartServiceTest1Activity.class.getSimpleName();
    private int mStartCount = 0;
    public final static String KEY_COUNT = "COUNT";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_start_service_test);

        findViewById(R.id.start).setOnClickListener(v -> start());
        findViewById(R.id.stopRecord).setOnClickListener(v -> stop());
        findViewById(R.id.jump).setOnClickListener(v -> jump());
    }

    private void start() {
        final Intent intent = new Intent(this, FirstService.class);
        Log.d(TAG, "start: startService");
        mStartCount++;
        intent.putExtra(KEY_COUNT, mStartCount);
        startService(intent);
    }

    private void stop() {
        final Intent intent = new Intent(this, FirstService.class);
        Log.d(TAG, "stop: stopService, context = StartServiceTest1Activity");
        //  When stopService, Intent can be a new one. No need to keep intent when startService();
        stopService(intent);
    }

    //    private void start() {
//        final Intent intent = new Intent(this.getApplicationContext(), FirstService.class);
//        Log.d(TAG, "start: startService, context = getApplicationContext()");
//        startService(intent);
//    }

//    private void stop() {
//        final Intent intent = new Intent(this.getApplicationContext(), FirstService.class);
//        Log.d(TAG, "stop: stopService");
//        //  When stopService, Intent can be a new one. No need to keep intent when startService();
//        stopService(intent);
//    }

    private void jump() {
//        final Intent intent = new Intent(this, StartServiceTest2Activity.class);
        final Intent intent = new Intent(this.getApplicationContext(), FirstService.class);
        Log.d(TAG, "jump: startActivity");
        startActivity(intent);
    }
}