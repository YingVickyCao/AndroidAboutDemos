package com.example.hades.androidpo._2_memory_op;

import android.Manifest;
import android.os.Bundle;

import com.example.hades.androidpo.BaseActivity;
import com.example.hades.androidpo.R;
import com.example.hades.androidpo._2_memory_op.memory_leak.monitor.MockLeakMemoryActivity;
import com.example.hades.androidpo._2_memory_op.memory_leak.monitor.MockLeakMemoryFragment;

public class MemoryOPActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_op_memory);

        checkPermission("Request storage permission", Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE);

        initViews();
        findViewById(R.id.monitorLeakMemory).setOnClickListener(v -> monitorLeakMemory());
        findViewById(R.id.monitorLeakMemory4Activity).setOnClickListener(v -> monitorLeakMemory4Activity());
    }

    @Override
    protected boolean isNeedCheckPermission() {
        return true;
    }

    @Override
    protected void showCurrentTest() {
        monitorLeakMemory();
    }

    private void monitorLeakMemory() {
        showFragment(new MockLeakMemoryFragment());
    }

    private void monitorLeakMemory4Activity() {
        startActivity(MockLeakMemoryActivity.class);
    }

}