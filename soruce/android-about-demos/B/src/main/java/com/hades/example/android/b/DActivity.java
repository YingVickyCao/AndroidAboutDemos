package com.hades.example.android.b;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class DActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_permission);

        findViewById(R.id.back).setOnClickListener(v -> back());
    }

    private void back() {
        onBackPressed();
    }
}