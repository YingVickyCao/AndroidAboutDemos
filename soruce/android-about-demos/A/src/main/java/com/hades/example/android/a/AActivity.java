package com.hades.example.android.a;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

public class AActivity extends AppCompatActivity {
    private static final String TAG = AActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manifest_security_a_layout);
        findViewById(R.id.jumpB).setOnClickListener(v -> jumpB());
        findViewById(R.id.jumpC4ImplicitIntent).setOnClickListener(v -> jumpC4ImplicitIntent());
        findViewById(R.id.jumpC4ExplicitIntent).setOnClickListener(v -> jumpC4ExplicitIntent());
    }

    private void jumpB() {
        ComponentName componentName = new ComponentName("com.hades.example.android.b",
                "com.hades.example.android.b.BActivity");
        Intent intent = new Intent();
        intent.setComponent(componentName);
        intent.putExtra("NUM", 100);
        startActivity(intent);
    }

    private void jumpC4ExplicitIntent() {
        Intent intent = new Intent();
        // setComponent
        ComponentName componentName = new ComponentName("com.hades.example.android.b",
                "com.hades.example.android.b.CActivity");
//        intent.setComponent(componentName);

        // setClassName
        intent.setClassName("com.hades.example.android.b",
                "com.hades.example.android.b.CActivity");
        intent.putExtra("NUM1", 100);
//        startActivity(intent);
        startActivityForResult(intent, 1000);
    }

    /*
    In B App, C :
    android:exported="true", and no <intent-filter>
     ERROR: android.content.ActivityNotFoundException: No Activity found to handle Intent { act=com.hades.example.android.b.c cat=[android.intent.category.DEFAULT] (has extras) }
     */
    private void jumpC4ImplicitIntent() {
        Intent intent = new Intent();
        intent.setAction("com.hades.example.android.b.c");
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.putExtra("NUM2", 200);
//        startActivity(intent);
        startActivityForResult(intent, 2000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "onActivityResult: intent=" + data.toString());
        if (1000 == requestCode) {
            Log.d(TAG, "onActivityResult:num1=" + data.getStringExtra("NUM1"));
            Toast.makeText(this, data.getIntExtra("NUM1", 0), Toast.LENGTH_SHORT).show();
        } else if (2000 == requestCode) {
            Log.d(TAG, "onActivityResult:num2=" + data.getStringExtra("NUM2"));
            Toast.makeText(this, data.getIntExtra("NUM2", 0), Toast.LENGTH_SHORT).show();
        }
    }
}
