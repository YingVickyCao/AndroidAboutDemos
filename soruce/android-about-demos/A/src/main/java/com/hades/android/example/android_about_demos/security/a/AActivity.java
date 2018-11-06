package com.hades.android.example.android_about_demos.security.a;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class AActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.security_a);
        findViewById(R.id.jumpB).setOnClickListener(v -> jumpB());
    }

    private void jumpB() {
//        Intent intent = new Intent();
//        intent.setClassName("com.hades.android.example.android_about_demos.security.b"
//                , "com.hades.android.example.android_about_demos.security.b.BActivity");
//        intent.setType("text/plain");
//        intent.putExtra("NUM", 100);
//        startActivity(intent);
    }
}
