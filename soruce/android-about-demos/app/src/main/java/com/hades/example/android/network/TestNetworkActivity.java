package com.hades.example.android.network;

import android.Manifest;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.PermissionActivity;
import com.hades.example.android.network.tcp_ip.multi_thread_client.MultiThreadClientActivity;
import com.hades.example.android.network.tcp_ip.one_thread_client.SimpleClientActivity;
import com.hades.example.android.network.url.http_url_connection.MultiThreadDownFragment;
import com.hades.example.android.network.url.url.TestURLFragment;
import com.hades.example.android.network.url.url_connection.TestURLConnectionFragment;

public class TestNetworkActivity extends PermissionActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.network_layout);

        initViews();

        findViewById(R.id.pageSimpleSocketClient).setOnClickListener(v -> pageSimpleSocketClient());
        findViewById(R.id.pageMultiThreadSocketClient).setOnClickListener(v -> pageMultiThreadSocketClient());
        findViewById(R.id.pageURL).setOnClickListener(v -> pageURL());
        findViewById(R.id.pageURLConnection).setOnClickListener(v -> pageURLConnection());
        findViewById(R.id.pageHttpURLConnection).setOnClickListener(v -> pageHttpURLConnection());
    }

    @Override
    protected void requestPermission() {
        // FIXED_ERROR:java.io.FileNotFoundException: /sdcard/bg004.JPG: open failed: EACCES (Permission denied)
        checkPermission("Request permission for Storage", Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE);
    }

    @Override
    protected void showCurrentTest() {
        pageHttpURLConnection();
    }

    @Override
    protected boolean isNeedCheckPermission() {
        return true;
    }

    @Override
    protected void requestPermission(String... permissions) {
        super.requestPermission(permissions);
    }

    private void pageSimpleSocketClient() {
        showActivity(SimpleClientActivity.class);
    }

    private void pageMultiThreadSocketClient() {
        showActivity(MultiThreadClientActivity.class);
    }

    private void pageURL() {
        showFragment(new TestURLFragment());
    }

    private void pageURLConnection() {
        showFragment(new TestURLConnectionFragment());
    }

    private void pageHttpURLConnection() {
        showFragment(new MultiThreadDownFragment());
    }
}