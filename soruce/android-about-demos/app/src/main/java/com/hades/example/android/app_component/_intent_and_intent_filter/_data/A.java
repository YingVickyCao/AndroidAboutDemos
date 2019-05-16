package com.hades.example.android.app_component._intent_and_intent_filter._data;

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
    }

    private void case1() {
        Intent intent = new Intent();
        Uri uri = Uri.parse("scheme1:");
        intent.setData(uri);

//        intent.addCategory(Intent.CATEGORY_DEFAULT);
//        intent.setAction("action1");

        Log.d(TAG, "scheme_category: " + uri.toString());
        startActivity(intent);
    }

    private void case2() {
        Intent intent = new Intent();
        Uri uri = Uri.parse("scheme2://com.hades");
        intent.setData(uri);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setAction("action1");

        Log.d(TAG, "scheme_category: " + uri.toString());
        startActivity(intent);
    }

    private void case3() {
        Intent intent = new Intent();
        Uri uri = Uri.parse("scheme2://com.hades");
        intent.setData(uri);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setAction("action2");

        Log.d(TAG, "scheme_category: " + uri.toString());
        startActivity(intent);
    }

    private void case4() {
        Intent intent = new Intent();
        Uri uri = Uri.parse("scheme2://com.hades:1000/path2");
        intent.setData(uri);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setAction("action2");

        Log.d(TAG, "scheme_category: " + uri.toString());
        startActivity(intent);
    }

    private void case5() {
        Intent intent = new Intent();
        Uri uri = Uri.parse("scheme2:/com.hades:1000/path2");
        intent.setData(uri);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setAction("action2");

        Log.d(TAG, "scheme_category: " + uri.toString());
        startActivity(intent);
    }
}