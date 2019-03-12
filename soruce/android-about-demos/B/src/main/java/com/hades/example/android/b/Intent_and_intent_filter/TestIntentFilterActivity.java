package com.hades.example.android.b.Intent_and_intent_filter;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hades.example.android.b.R;

public class TestIntentFilterActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intent_filter);
    }
}