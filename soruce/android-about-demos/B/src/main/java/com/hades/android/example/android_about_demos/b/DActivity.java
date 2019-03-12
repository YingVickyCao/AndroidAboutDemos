package com.hades.android.example.android_about_demos.b;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

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