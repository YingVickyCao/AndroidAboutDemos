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
        findViewById(R.id.jumpC4ImplicitIntent).setOnClickListener(v -> jumpC4ImplicitIntent());
        findViewById(R.id.jumpC).setOnClickListener(v -> jumpC4ExplicitIntent());
    }

    private void jumpB() {
        ComponentName componentName = new ComponentName("com.hades.android.example.android_about_demos.security.b",
                "com.hades.android.example.android_about_demos.security.b.BActivity");
        Intent intent = new Intent();
        intent.setComponent(componentName);
        intent.putExtra("NUM", 100);
        startActivity(intent);
    }

    private void jumpC4ExplicitIntent() {
        Intent intent = new Intent();
        // setComponent
        ComponentName componentName = new ComponentName("com.hades.android.example.android_about_demos.security.b",
                "com.hades.android.example.android_about_demos.security.b.CActivity");
//        intent.setComponent(componentName);

        // setClassName
        intent.setClassName("com.hades.android.example.android_about_demos.security.b",
                "com.hades.android.example.android_about_demos.security.b.CActivity");
        intent.putExtra("NUM", 100);
        startActivity(intent);
    }

    /*
    In B App, C :
    android:exported="true", and no <intent-filter>
     ERROR: android.content.ActivityNotFoundException: No Activity found to handle Intent { act=com.hades.android.example.android_about_demos.security.b.c cat=[android.intent.category.DEFAULT] (has extras) }
     */
    private void jumpC4ImplicitIntent() {
        Intent intent = new Intent();
        intent.setAction("com.hades.android.example.android_about_demos.security.b.c");
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.putExtra("NUM", 100);
        startActivity(intent);
    }
}
