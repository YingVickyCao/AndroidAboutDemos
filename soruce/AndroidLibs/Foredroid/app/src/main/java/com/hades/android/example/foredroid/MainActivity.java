package com.hades.android.example.foredroid;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.sjl.foreground.Foreground;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements Foreground.Listener, ScreenStatusListener {
    protected static final String TAG = MainActivity.class.getSimpleName();
    TextView text;
    private ScreenStatusBroadcastReceiver broadcastReceiver;
    private IntentFilter intentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Foreground.init(getApplication()).addListener(this);

        text = findViewById(R.id.text);
        findViewById(R.id.startA2).setVisibility(View.VISIBLE);

        findViewById(R.id.checkForegroundBackground).setOnClickListener(v -> checkForegroundBackground());
        findViewById(R.id.startA2).setOnClickListener(v -> startA());

        intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_SCREEN_ON);
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
        intentFilter.addAction(Intent.ACTION_USER_PRESENT);
        broadcastReceiver = new ScreenStatusBroadcastReceiver(this);
        registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        broadcastReceiver.setScreenStatusListener(null);
        unregisterReceiver(broadcastReceiver);
        Foreground.get().removeListener(this);
    }

    protected void checkForegroundBackground() {
        Log.d(TAG, "checkForegroundBackground," + TAG + "@" + "Foreground.get().isForeground()=" + Foreground.get().isForeground());
    }

    private void startA() {
        startActivity(new Intent(this, AActivity.class));
    }

    @Override
    public void onBecameForeground() {
        Log.d(TAG, "onBecameForeground," + TAG + "@" + hashCode() + ",Foreground.get().isForeground()=" + Foreground.get().isForeground());
    }

    @Override
    public void onBecameBackground() {
        Log.d(TAG, "onBecameBackground," + TAG + "@" + hashCode() + ",Foreground.get().isForeground()=" + Foreground.get().isForeground());
    }

    @Override
    public void actionScreenOn() {
        Log.d(TAG, "actionScreenOn");
    }

    @Override
    public void actionScreenOff() {
        Log.d(TAG, "actionScreenOff");

    }

    @Override
    public void actionUserPresent() {
        Log.d(TAG, "actionUserPresent");

    }
}