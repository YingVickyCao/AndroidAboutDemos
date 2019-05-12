package com.hades.example.android.app_component._activity._life_cycle;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.hades.example.android.R;

public class A extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = A.class.getSimpleName();
    private Thread counter;
    private final String INSTANCE_STATE_KEY = "INSTANCE_STATE_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");

        setContentView(R.layout.activity_lifecycle_a_layout);
        if (savedInstanceState != null) {
            String test = savedInstanceState.getString(INSTANCE_STATE_KEY);
            Log.d(TAG, "[onCreate]restore extra_test:" + test);
        }
        findViewById(R.id.openB).setOnClickListener(this);
        findViewById(R.id.openTranslucentB).setOnClickListener(this);
        findViewById(R.id.openFloatB).setOnClickListener(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(TAG, "onNewIntent, time=" + intent.getLongExtra("time", 0));
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: ");
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "onStart");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause");
        super.onPause();
        //testIfInOnPauseCanDoHeavyWork();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState");
        outState.putString(INSTANCE_STATE_KEY, "test");

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop");
        // 无论super.onStop() 前后调用testIfInOnStopCanDoHeavyWork，log都是一样的。
        // testIfInOnStopCanDoHeavyWork();
        super.onStop();
        // testIfInOnStopCanDoHeavyWork();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // 2 == ORIENTATION_LANDSCAPE
        // 1 == ORIENTATION_PORTRAIT
        // 0 == ORIENTATION_UNDEFINED
        Log.d(TAG, "onConfigurationChanged, newOrientation:" + newConfig.orientation);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String test = savedInstanceState.getString(INSTANCE_STATE_KEY);
        Log.d(TAG, "[onRestoreInstanceState]restore extra_test:" + test);
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.openB:
                openBActivity();
                break;

            case R.id.openTranslucentB:
                openTranslucentBActivity();
                break;

            case R.id.openFloatB:
                openFloatBActivity();
                break;
        }
    }

    private void openBActivity() {
        Intent intent = new Intent(this, B.class);
        startActivity(intent);
    }

    private void openTranslucentBActivity() {
        Intent intent = new Intent(this, TranslucentB.class);
        startActivity(intent);
    }

    private void openFloatBActivity() {
        Intent intent = new Intent(this, FloatB.class);
        startActivity(intent);
    }

    private void testIfInOnPauseCanDoHeavyWork() {
        testIfInOnStopCanDoHeavyWork();
    }

    private void testIfInOnStopCanDoHeavyWork() {
        mockDoHeavyWork();
    }

    private void mockDoHeavyWork() {
        if (null != counter) {
            return;
        }
        counter = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    Log.d(TAG, "run: i=" + i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        counter.start();
    }
}
