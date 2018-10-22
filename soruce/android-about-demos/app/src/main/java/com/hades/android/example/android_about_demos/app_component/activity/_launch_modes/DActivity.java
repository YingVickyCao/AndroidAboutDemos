package com.hades.android.example.android_about_demos.app_component.activity._launch_modes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.hades.android.example.android_about_demos.R;

public class DActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_d_layout);

        findViewById(R.id.openActivity).setOnClickListener(v -> openActivity());
        TextView name = findViewById(R.id.name);
        name.setText("D");
    }

    private void openActivity() {
        Intent intent = new Intent(this, EActivity.class);
        startActivity(new Intent(intent));
    }
}