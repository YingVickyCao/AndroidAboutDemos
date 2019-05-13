package com.hades.example.android.app_component._intent_and_intent_filter._component;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;

import com.hades.example.android.R;


public class FirstActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intent_component_1);

        findViewById(R.id.open).setOnClickListener(arg0 -> open());
    }

    private void open() {
        ComponentName comp = new ComponentName(FirstActivity.this, SecondActivity.class);
        Intent intent = new Intent();
        intent.setComponent(comp);
        startActivity(intent);
    }
}