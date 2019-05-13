package com.hades.example.android.app_component._intent_and_intent_filter._component;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.hades.example.android.R;


public class FirstActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intent_component_1);

        findViewById(R.id.open1).setOnClickListener(arg0 -> open());
        findViewById(R.id.open2).setOnClickListener(arg0 -> open2());
        findViewById(R.id.open3).setOnClickListener(arg0 -> open3());
        findViewById(R.id.open4).setOnClickListener(arg0 -> open4());
        findViewById(R.id.open5).setOnClickListener(arg0 -> open5());
        findViewById(R.id.open6).setOnClickListener(arg0 -> open6());
        findViewById(R.id.open7).setOnClickListener(arg0 -> open7());
    }

    /*
        ComponentName(Context pkg, @Class<?> cls) - Context = Activity
        PackageName：com.hades.example.android
        ClassName：com.hades.example.android.app_component._intent_and_intent_filter._component.SecondActivity
     */
    private void open() {
        ComponentName comp = new ComponentName(FirstActivity.this, SecondActivity.class);

        Intent intent = new Intent();
        intent.setComponent(comp);
        startActivity(intent);
    }

    /*
        ComponentName(Context pkg, @Class<?> cls) - Context = Application
        PackageName：com.hades.example.android
        ClassName：com.hades.example.android.app_component._intent_and_intent_filter._component.SecondActivity
     */
    private void open2() {
        ComponentName comp = new ComponentName(getApplicationContext(), SecondActivity.class);

        Intent intent = new Intent();
        intent.setComponent(comp);
        startActivity(intent);
    }

    private void open3() {
        ComponentName comp = new ComponentName(getPackageName(), SecondActivity.class.getName());

        Intent intent = new Intent();
        intent.setComponent(comp);
        startActivity(intent);
    }

    private void open4() {
        ComponentName comp = new ComponentName(this, SecondActivity.class.getName());

        Intent intent = new Intent();
        intent.setComponent(comp);
        startActivity(intent);
    }

    private void open5() {
        Intent intent = new Intent();
        intent.setClass(this, SecondActivity.class);
        startActivity(intent);
    }

    private void open6() {
        Intent intent = new Intent();
        intent.setClassName(getPackageName(), SecondActivity.class.getName());
        startActivity(intent);
    }

    private void open7() {
        Intent intent = new Intent();
        intent.setClassName(this, SecondActivity.class.getName());
        startActivity(intent);
    }
}