package com.hades.example.android.resource.adapter_screen;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hades.example.android.R;

public class TestConfigurationActivity extends AppCompatActivity {
    private static final String TAG = TestConfigurationActivity.class.getSimpleName();

    private final String INSTANCE_STATE_KEY = "INSTANCE_STATE_KEY";
    private Thread counter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTitle(R.string.activity_disable_Rotate);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.res_configuration);


        findViewById(R.id.printConfiguration).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                printConfiguration();
            }
        });


        disableRotate();

        mockDoHeavyWork();
    }

    private void printConfiguration(){
        // 获取系统的Configuration对象
        Configuration configuration = getResources().getConfiguration();

        Log.d(TAG, "printConfiguration:" +
                " @fontScale=" + configuration.fontScale
                + ",@keyboard="+configuration.keyboard
                +",@keyboardHidden"+configuration.keyboardHidden
                +",@locale"+configuration.locale.toString())
        ;

        Log.d(TAG, "onConfigurationChanged: Configuration.fontScale=" + configuration.locale.toString());
        Log.d(TAG, "onConfigurationChanged: Configuration.fontScale=" + configuration.mcc);
        Log.d(TAG, "onConfigurationChanged: Configuration.fontScale=" + configuration.mnc);
        Log.d(TAG, "onConfigurationChanged: Configuration.fontScale=" + configuration.navigation);
        Log.d(TAG, "onConfigurationChanged: Configuration.fontScale=" + configuration.navigationHidden);
        Log.d(TAG, "onConfigurationChanged: Configuration.fontScale=" + configuration.orientation);
        Log.d(TAG, "onConfigurationChanged: Configuration.fontScale=" + configuration.touchscreen);

        Log.d(TAG, "onConfigurationChanged: " + configuration.orientation);
    }

    private void disableRotate() {
        // 禁止横竖屏转换，设置屏幕方向为竖屏
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // 禁止横竖屏转换，设置屏幕方向为横屏
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        // 手机时，竖屏； 平板时，允许转屏，允许重新创建Activity
        boolean isTablet = isTablet();
        Log.d(TAG, "onCreate: isTablet=" + isTablet);
        setRequestedOrientation(isTablet ? ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED : ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    private boolean isTablet() {
        String screen = getString(R.string.screen);
        Log.d(TAG, "isTablet: screen=" + screen);
        return ("xlarge-land".equalsIgnoreCase(screen) || "xlarge".equalsIgnoreCase(screen));
    }

    private void mockDoHeavyWork() {
        if (null != counter) {
            return;
        }

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        counter = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
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

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        switch (newConfig.orientation) {
            case Configuration.ORIENTATION_LANDSCAPE:
                Log.d(TAG, "onConfigurationChanged: ORIENTATION_LANDSCAPE");
                break;

            case Configuration.ORIENTATION_PORTRAIT:
                Log.d(TAG, "onConfigurationChanged: ORIENTATION_PORTRAIT");
                break;

            case Configuration.ORIENTATION_UNDEFINED:
                Log.d(TAG, "onConfigurationChanged: ORIENTATION_UNDEFINED");
                break;
        }
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
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d(TAG, "onSaveInstanceState");
        outState.putString(INSTANCE_STATE_KEY, "test");

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop");
        super.onStop();
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
}