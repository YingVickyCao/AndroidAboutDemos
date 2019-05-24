package com.hades.example.android.app_component._intent_and_intent_filter._data_type;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hades.example.android.R;

public class A extends AppCompatActivity {
    private static final String TAG = A.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.intent_filter_data_type_a);

        findViewById(R.id.case1).setOnClickListener(v -> case1());
        findViewById(R.id.case2).setOnClickListener(v -> case2());
        findViewById(R.id.case3).setOnClickListener(v -> case3());
        findViewById(R.id.case4).setOnClickListener(v -> case4());
        findViewById(R.id.case5).setOnClickListener(v -> case5());
        findViewById(R.id.case6).setOnClickListener(v -> case6());
        findViewById(R.id.case7).setOnClickListener(v -> case7());
        findViewById(R.id.case8).setOnClickListener(v -> case8());
        findViewById(R.id.case9).setOnClickListener(v -> case9());
    }

    // Intent { dat=scheme1: }
    private void case1() {
        Intent intent = new Intent();
        Uri uri = Uri.parse("scheme1:");
        intent.setData(uri);

//        intent.addCategory(Intent.CATEGORY_DEFAULT);
//        intent.setAction("action1");

        Log.d(TAG, "case1: " + intent.toString());
        startActivity(intent);
    }

    // Intent { dat=scheme2://com.hades }
    private void case2() {
        Intent intent = new Intent();
        Uri uri = Uri.parse("scheme2://com.hades");
        intent.setData(uri);
        Log.d(TAG, "case2: " + intent.toString());
        startActivity(intent);
    }

    // ERROR:android.content.ActivityNotFoundException: No Activity found to handle Intent { act=action1 cat=[android.intent.category.DEFAULT] dat=scheme2://com.hades }
    private void case3() {
        Intent intent = new Intent();
        Uri uri = Uri.parse("scheme2://com.hades");
        intent.setData(uri);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setAction("action1");

        Log.d(TAG, "case3: " + intent.toString());
        startActivity(intent);
    }

    private void case4() {
        Intent intent = new Intent();
        Uri uri = Uri.parse("scheme2://com.hades");
        intent.setData(uri);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setAction("action2");

        Log.d(TAG, "case4: " + intent.toString());
        startActivity(intent);
    }

    private void case5() {
        Intent intent = new Intent();
        Uri uri = Uri.parse("scheme2://com.hades:1000/path2");
        intent.setData(uri);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setAction("action2");

        Log.d(TAG, "case5: " + intent.toString());
        startActivity(intent);
    }

    private void case6() {
        Intent intent = new Intent();
        Uri uri = Uri.parse("scheme2:/com.hades:1000/path2");
        intent.setData(uri);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setAction("action2");

        Log.d(TAG, "case6: " + intent.toString());
        startActivity(intent);
    }

    // ERROR:android.content.ActivityNotFoundException: No Activity found to handle Intent { dat=scheme3://com.hades/path1 }
    private void case7() {
        Intent intent = new Intent();
        Uri uri = Uri.parse("scheme3://com.hades/path1");
        intent.setType(getString(R.string.mime_type_1));
        intent.setData(uri);

        Log.d(TAG, "case7: " + intent.toString());
        startActivity(intent);
    }

    // Intent { typ=a/b }
    private void case8() {
        Intent intent = new Intent();
        Uri uri = Uri.parse("scheme3://com.hades/path1");
        intent.setData(uri);
        intent.setType(getString(R.string.mime_type_1));
        Log.d(TAG, "case8: " + intent.toString());
        startActivity(intent);
    }

    private void case9() {
        Intent intent = new Intent();
        Uri uri = Uri.parse("scheme3://com.hades/path2");
        intent.setDataAndType(uri, getString(R.string.mime_type_1));
        Log.d(TAG, "case9: " + intent.toString());
        startActivity(intent);
    }
}