package com.hades.example.android.b;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class CActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.security_c);

        findViewById(R.id.back).setOnClickListener(v -> back());
    }

    private void back() {
        Intent intent = getIntent();
        if (null == intent) {
            return;
        }

        int num1 = intent.getIntExtra("NUM1", 0);
        int num2 = intent.getIntExtra("NUM2", 0);

        if (num1 != 0) {
            buildResult(1, num1, "NUM1");
            return;
        }

        if (num2 != 0) {
            buildResult(2, num2, "NUM2");
            return;
        }
    }

    private void buildResult(int plusNum, int baseNum, String label) {
        Intent intent2 = new Intent();
        intent2.setAction("com.hades.example.android.a");
        intent2.addCategory(Intent.CATEGORY_DEFAULT);
        intent2.putExtra(label, baseNum + plusNum);
        setResult(2000, intent2);
        finish();
    }
}