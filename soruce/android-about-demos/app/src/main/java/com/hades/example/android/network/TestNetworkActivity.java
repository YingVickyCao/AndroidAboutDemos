package com.hades.example.android.network;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.BaseActivity;
import com.hades.example.android.network.tcp_ip.multi_thread_client.MultiThreadClientActivity;
import com.hades.example.android.network.tcp_ip.one_thread_client.SimpleClientActivity;
import com.hades.example.android.network.url.TestURLFragment;

public class TestNetworkActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.network_layout);

        initViews();

        findViewById(R.id.pageSimpleSocketClient).setOnClickListener(v -> pageSimpleSocketClient());
        findViewById(R.id.pageMultiThreadSocketClient).setOnClickListener(v -> pageMultiThreadSocketClient());
        findViewById(R.id.pageURL).setOnClickListener(v -> pageURL());
    }

    @Override
    protected void showCurrentTest() {
        pageURL();
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
}