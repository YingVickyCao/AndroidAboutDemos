package com.hades.android.example.android_about_demos.app_component.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {
    protected void startActivity(Class<?> cls) {
        startActivity(new Intent(this, cls));
    }
}