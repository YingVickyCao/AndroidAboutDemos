package com.hades.example.android.app_component._activity._start_close;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hades.example.android.R;

public class AActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_a);
        findViewById(R.id.pageB).setOnClickListener(v -> pageB());
    }

    private void pageB() {
        Intent intent = new Intent(AActivity.this, BActivity.class);
        startActivity(intent);
    }
}