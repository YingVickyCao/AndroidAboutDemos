package com.hades.android.example.android_about_demos.security.a;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class AActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.security_a);
        findViewById(R.id.jumpB).setOnClickListener(v -> jumpB());
        findViewById(R.id.jumpC).setOnClickListener(v -> jumpC());
    }

    private void jumpB() {
        ComponentName componentName = new ComponentName("com.hades.android.example.android_about_demos.security.b",
                "com.hades.android.example.android_about_demos.security.b.BActivity");
        Intent intent = new Intent();
        intent.setComponent(componentName);
        intent.setType("text/plain");
        intent.putExtra("NUM", 100);
        startActivity(intent);
    }

    private void jumpC() {
        ComponentName componentName = new ComponentName("com.hades.android.example.android_about_demos.security.b",
                "com.hades.android.example.android_about_demos.security.b.CActivity");
        Intent intent = new Intent();
        intent.setComponent(componentName);
        intent.setType("text/plain");
        intent.putExtra("NUM", 100);
        startActivity(intent);
    }
}
