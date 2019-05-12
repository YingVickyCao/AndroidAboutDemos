package com.hades.example.android.app_component._activity._start_close;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hades.example.android.R;

public class BActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_b);

        findViewById(R.id.pageA).setOnClickListener(v -> pageA());
        findViewById(R.id.close).setOnClickListener(v -> close());
    }

    private void pageA() {
        Intent intent = new Intent(this, AActivity.class);
        startActivity(intent);
    }

    private void close() {
        pageA();
        finish();
    }
}