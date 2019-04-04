package com.example.hades.androidpo;

import android.os.Bundle;
import android.widget.Toast;

import com.example.hades.androidpo._1_render_op.DrawLayoutOPActivity;
import com.example.hades.androidpo._2_memory_op.MemoryOPActivity;
import com.example.hades.androidpo._4_stability_op.StabilityOPActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.pageDrawLayoutOP).setOnClickListener(v -> pageDrawLayoutOP());
        findViewById(R.id.pageMemoryOP).setOnClickListener(v -> pageMemoryOP());
        findViewById(R.id.pageStorageOP).setOnClickListener(v -> pageStorageOP());
        findViewById(R.id.pageStabilityOP).setOnClickListener(v -> pageStabilityOP());
        findViewById(R.id.pageAPKSizeOP).setOnClickListener(v -> pageAPKSizeOP());
    }

    private void pageDrawLayoutOP() {
        startActivity(DrawLayoutOPActivity.class);
    }

    private void pageMemoryOP() {
        startActivity(MemoryOPActivity.class);
    }

    private void pageStorageOP() {
        Toast.makeText(this, getResources().getString(R.string.page_op_storage), Toast.LENGTH_SHORT).show();
    }

    private void pageStabilityOP() {
        startActivity(StabilityOPActivity.class);
    }

    private void pageAPKSizeOP() {
        Toast.makeText(this, getResources().getString(R.string.page_op_apk_size), Toast.LENGTH_SHORT).show();
    }
}
